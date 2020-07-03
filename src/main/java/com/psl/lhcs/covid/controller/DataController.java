package com.psl.lhcs.covid.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.lhcs.covid.constants.ApplicationConstants;
import com.psl.lhcs.covid.model.excelData.FileResponse;
import com.psl.lhcs.covid.model.validate.JsonResponse;
import com.psl.lhcs.covid.repo.data.DataRepository;
import com.psl.lhcs.covid.response.Response;
import com.psl.lhcs.covid.service.data.DataService;


@RestController
@CrossOrigin(origins="*")
public class DataController {
	
	
	@Autowired
	DataService service;

	@Autowired
	DataRepository dataRepository;
	
	@Value("${message.notAuthorizedUser}")
	private String notAuthorizedUser;
	


	@RequestMapping(value = "/validateFile", method = RequestMethod.POST , consumes = { "multipart/form-data" } ,produces={"application/json"})
	public @ResponseBody ResponseEntity<JsonResponse> uploadFileHandler(@RequestParam(value=ApplicationConstants.FILE,required=true) MultipartFile reapExcelDataFile) {

		JsonResponse response=null;
		
		try {
			response=service.ReadExcelFile(reapExcelDataFile.getInputStream());
		} catch (IOException e) {

			e.printStackTrace();
		}

		return ResponseEntity.ok().body(response);

	}

	@PostMapping(value ="/uploadFile")
	public Response<?> fileUpload(@RequestParam MultipartFile file,@RequestParam String userId){

		String msg = null;
		Response<?> response = null;
		try {	
			response = service.uploadExcelFile(file,userId);

		} catch (IOException e) {

			e.printStackTrace();
		}
		

		return response;

	}



	@GetMapping(value="/display",produces={"application/json"})
	public Response<?> downloadFromDB(@RequestBody Map<String,String> data)
	{
		Response<?> response = service.downloadFromDB(data.get(ApplicationConstants.USERID));
	
		
		return response;
	}



	@GetMapping("/download")
	public ResponseEntity sendFile(@RequestBody Map<String,String> data)
	{
		String timeStamp=data.get(ApplicationConstants.TIMESTAMP);
		String email=data.get(ApplicationConstants.USERID);

		FileResponse response = service.sendFile(timeStamp,email);

		if(response == null)
		{
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(notAuthorizedUser);
		}
		byte[] excel = response.getFile();
		Response<byte[]> res=new Response<>();
		res.setContent(excel);
		
		String fileName = response.getFile_Name();
		System.out.println(fileName);


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename="+fileName );
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(excel);

	}
	
	
}
