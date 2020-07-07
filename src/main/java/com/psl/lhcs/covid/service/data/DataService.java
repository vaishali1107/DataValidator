package com.psl.lhcs.covid.service.data;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.lhcs.covid.constants.ApplicationConstants;
import com.psl.lhcs.covid.model.data.DownloadJsonResponse;
import com.psl.lhcs.covid.model.data.FileRecords;
import com.psl.lhcs.covid.model.data.FileResponse;
import com.psl.lhcs.covid.model.login.User;
import com.psl.lhcs.covid.repo.data.DataRepository;
import com.psl.lhcs.covid.repo.user.UserRepo;
import com.psl.lhcs.covid.response.Response;

@Service
public class DataService {

	@Autowired
	UserRepo userRepo;

	
	@Value("${message.isBlank}")
	private String isBlank;


	@Value("${message.uploadSuccess}") 
	private String uploadSuccess;

	@Value("${message.uploadFail}")
	private String uploadFail;

	@Value("${message.isNull}")
	private String isNull;

	@Autowired
	DataRepository dataRepository;

	@Value("${message.notAuthorizedUser}")
	private String notAuthorizedUser;

	@Value("${userAssessmentHeaders}")
	private String[] userAssessmentHeaders;



	public Response<String> uploadExcelFile(MultipartFile file,String userName)  {

		User user=userRepo.findByEmail(userName);
		Response<String> response = new Response<>();
		if(user == null) {
			response.setMessage(notAuthorizedUser);
			response.setCode(401);
			response.setStatus(HttpStatus.UNAUTHORIZED);
			return response;
		}
		FileRecords fileRecords = new FileRecords();


		Timestamp instant= Timestamp.from(Instant.now());


		fileRecords.setHospitalName(user.getHospitalName());
		fileRecords.setLabName(user.getLabName());
		fileRecords.setUserName(user.getEmail());
		fileRecords.setTimestamp(instant.toString());
		byte[] data=new byte[(int) file.getSize()];
		try {
			data=file.getBytes();
		} catch (IOException e) {
			response.setMessage(notAuthorizedUser);
			response.setCode(401);
			response.setStatus(HttpStatus.UNAUTHORIZED);
			return response;
			
		}
		fileRecords.setFile(data);

		fileRecords.setFileName(file.getOriginalFilename());
		FileRecords res =dataRepository.save(fileRecords);
		if(res.equals(fileRecords)) {
			response.setMessage(uploadSuccess);
			response.setStatus(HttpStatus.OK);
			response.setCode(200);
			return response;
		}
		else {
			response.setMessage(uploadFail);
			response.setCode(500);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}


	}

	public  Response<DownloadJsonResponse> downloadFromDB(String userId)
	{
		Response<DownloadJsonResponse> response = new Response<>();
		
		User user=userRepo.findByEmail(userId);
		
		if(user == null)
		{
			response.setCode(403);
			response.setStatus(HttpStatus.FORBIDDEN);
			return response;
		}
			
		String owner = user.getOwner();
		
		if(owner.equalsIgnoreCase(ApplicationConstants.ADMIN))
		{
			DownloadJsonResponse d=new DownloadJsonResponse(ApplicationConstants.ADMIN,dataRepository.getAllData());
			
			response.setCode(200);
			response.setStatus(HttpStatus.OK);
			response.setContent(d);
			return response;
		}

		else if(owner.equalsIgnoreCase(ApplicationConstants.HOSPITAL)) {
			response.setCode(200);
			response.setContent(new DownloadJsonResponse(user.getHospitalName(),dataRepository.getDataForHospital(user.getHospitalName())));
			response.setStatus(HttpStatus.OK);
			return response;
		}
		else if(owner.equalsIgnoreCase(ApplicationConstants.LAB)) {
			response.setCode(200);
			response.setContent(new DownloadJsonResponse(user.getLabName(),dataRepository.getDataForLab(user.getLabName())));
			response.setStatus(HttpStatus.OK);
			return response;
			
		}
		return null;

	}
	public FileResponse sendFile(String timeStamp,String email)
    {
        User user=userRepo.findByEmail(email);
         if(user == null)
             return null;
    return dataRepository.getFile(timeStamp,email);   
    
    }
}
