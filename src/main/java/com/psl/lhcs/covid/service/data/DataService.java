package com.psl.lhcs.covid.service.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.psl.lhcs.covid.constants.ApplicationConstants;
import com.psl.lhcs.covid.constants.ValidationConstants;
import com.psl.lhcs.covid.model.excelData.DownloadJsonResponse;
import com.psl.lhcs.covid.model.excelData.FileRecords;
import com.psl.lhcs.covid.model.excelData.FileResponse;
import com.psl.lhcs.covid.model.login.User;
import com.psl.lhcs.covid.model.validate.ErrorClass;
import com.psl.lhcs.covid.model.validate.ErrorRowJson;
import com.psl.lhcs.covid.model.validate.JsonResponse;
import com.psl.lhcs.covid.model.validate.RowDetailsObj;
import com.psl.lhcs.covid.model.validate.ValidationClass;
import com.psl.lhcs.covid.repo.data.DataRepository;
import com.psl.lhcs.covid.repo.user.UserRepo;
import com.psl.lhcs.covid.response.Response;

@Service
public class DataService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	ValidationClass validate;

	@Value("${message.isBlank}")
	private String isBlank;



	@Value("${message.isInvalid_Date}")
	private String isInvalid_Date;

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



	public boolean isCellEmpty(Cell cell) {
		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();
		String data = dataFormatter.formatCellValue(cell);

		if (data == null || data.isEmpty() || data.trim().isEmpty())
			return true;
		return false;

	}

	public JsonResponse ReadExcelFile(InputStream inputStream) {

		List<String> rowHeader = new ArrayList<>();
		List<ErrorRowJson> errors = new ArrayList<>();

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			XSSFSheet sheet = workbook.getSheetAt(0);

			// Created a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			Iterator<Row> rowIt = sheet.iterator();

			Row row1 = rowIt.next();
			int columnCount = 0;
			for (int j = row1.getFirstCellNum(); j < row1.getLastCellNum(); j++) {

				String columnHeader = userAssessmentHeaders[columnCount];
				columnCount++;
				rowHeader.add(columnHeader);

			}

			int rowNo = 0;
			while (rowIt.hasNext()) {
				Row row = rowIt.next();
				rowNo++;
				RowDetailsObj rowobj = new RowDetailsObj();
				List<ErrorClass> errList = new ArrayList<>();

				for (int j = row1.getFirstCellNum(); j < row1.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);

					switch (rowHeader.get(j)) {

					case ValidationConstants.Srno: // Column 'A' : SR No
						if (cell == null || isCellEmpty(cell)) {
							ErrorClass err = new ErrorClass(rowHeader.get(j), isBlank);
							errList.add(err);
						} else
							if (cell != null && !isCellEmpty(cell)) {
								String srno = dataFormatter.formatCellValue(cell).trim();
								String errorMsg = validate.isSRNoValid(srno);
								if (errorMsg != null) {
									errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
								}
							}
						break;

					case ValidationConstants.District:// Column 'B' : District

						if (cell == null || isCellEmpty(cell)) {

							ErrorClass err = new ErrorClass(rowHeader.get(j), isBlank);
							errList.add(err);
						} else {

							String district = dataFormatter.formatCellValue(cell).trim();
							String errString = validate.isDistrictValid(district);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}

						break;

					case ValidationConstants.Name:// Name Column 'C' : Name

						if (cell == null || isCellEmpty(cell)) {

							ErrorClass err = new ErrorClass(rowHeader.get(j), isBlank);
							errList.add(err);
						} else {

							String name = dataFormatter.formatCellValue(cell).trim();
							String errString = validate.isNameValid(name);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}

						break;

					case ValidationConstants.Age:// Column 'D' : Age

						if (cell == null || isCellEmpty(cell)) {
							ErrorClass err = new ErrorClass(rowHeader.get(j), isBlank);
							errList.add(err);
						}

						else {
							String age = dataFormatter.formatCellValue(cell).trim();
							String errString = validate.isAgeValid(age);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}
						break;

					case ValidationConstants.Gender:// Column 'E' : gender :
						if (cell == null || isCellEmpty(cell)) {
							errList.add(new ErrorClass(rowHeader.get(j), isBlank));
						} else {
							String errorMsg = validate.isgenderValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.Address:// Column 'F' : Address

						if (cell == null || isCellEmpty(cell)) {

							ErrorClass err = new ErrorClass(rowHeader.get(j), isBlank);
							errList.add(err);
						} else {

							String address = dataFormatter.formatCellValue(cell).trim();
							String errString = validate.isAddressValid(address);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}

						break;

					case ValidationConstants.Ward:// Column 'G' : Ward
						if (cell == null || isCellEmpty(cell)) {
							errList.add(new ErrorClass(rowHeader.get(j), isBlank));
						} else {
							String errorMsg = validate.isWardValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.Zone:// Column 'H' : Zone

						if (cell != null && !isCellEmpty(cell)) {

							String zone = dataFormatter.formatCellValue(cell).trim();
							String errString = validate.isZoneValid(zone);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}

						break;

					case ValidationConstants.Phnno:// Column 'G' : Contact number
						if (cell == null || isCellEmpty(cell)) {

							ErrorClass err = new ErrorClass(rowHeader.get(j), isBlank);
							errList.add(err);
						}

						else {

							String contact = dataFormatter.formatCellValue(cell).trim();
							String errString = validate.isContactValid(contact);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}

						break;

					case ValidationConstants.Traceable:// Column 'H' : Traceable

						if (cell != null && !isCellEmpty(cell)) {

							String traceable = dataFormatter.formatCellValue(cell).trim();
							String errString = validate.isTraceableValid(traceable);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}

						break;

					case ValidationConstants.ReceiptDate:// Column 'K' : Date of Receipt of info

						String a20 = dataFormatter.formatCellValue(cell).trim();
						if (a20.trim().equals("")) {
							rowobj.setDate_of_info(null); 
							ErrorClass err = new ErrorClass(rowHeader.get(j), isBlank); //
							errList.add(err);
						} else if (validate.isDateValid(a20)) {

							rowobj.setDate_of_info(a20); 

						} else {

							rowobj.setDate_of_info(null); 
							ErrorClass err = new ErrorClass(rowHeader.get(j), isInvalid_Date); //
							errList.add(err);
						}
						break;

					case ValidationConstants.ObservationDate:// Column 'L' : Observation Started from

						String a21 = dataFormatter.formatCellValue(cell).trim();
						if (!a21.trim().equals("")) {
							if (validate.isDateValid(a21)) {

								rowobj.setDate_of_observation(a21); 

								String errString = validate.isObservationDateValid(rowobj);

								if (errString != null) {
									ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
									errList.add(err);
								}

							} else {

								rowobj.setDate_of_observation(null); //
								ErrorClass err = new ErrorClass(rowHeader.get(j), isInvalid_Date); //
								errList.add(err);
							}
						}

						break;

					case ValidationConstants.HospitalName:// Column 'L' : Name of hospital

						if (cell != null && !isCellEmpty(cell)) {

							String name = dataFormatter.formatCellValue(cell).trim();
							String errString = validate.isNameOfHospitalValid(name);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}

						break;

					case ValidationConstants.SampleStatus:// Column 'N' : sample collected

						String str13 = dataFormatter.formatCellValue(cell).trim();
						str13 = str13.trim();
						if (str13 != null && !str13.isEmpty()) {
							String errString = validate.isSampleCollectedValid(str13);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						} else if (str13 == null) {

							ErrorClass err = new ErrorClass(rowHeader.get(j), isNull);
							errList.add(err);
						}
						break;

					case ValidationConstants.SampleCollectionDate:// Column 'O' : date of sample collection

						String a6 = dataFormatter.formatCellValue(cell).trim();
						if (a6.equals("")) {
							rowobj.setSample_collection_date(null); //
							ErrorClass err = new ErrorClass(rowHeader.get(j), isBlank); //
							errList.add(err);
						}

						else if (validate.isDateValid(a6)) {

							rowobj.setSample_collection_date(a6);

						} else {
							rowobj.setSample_collection_date(null);
							ErrorClass err = new ErrorClass(rowHeader.get(j), isInvalid_Date);
							errList.add(err);

						}

						break;

					case ValidationConstants.Status1:// Column 'P' : Result Of Sample
						if (cell == null || isCellEmpty(cell)) {
							errList.add(new ErrorClass(rowHeader.get(j), isBlank));
						} else {
							String errorMsg = validate.isResultValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.LabStatus:// Column 'Q' : lab where sample sent
						if (cell == null || isCellEmpty(cell)) {
							errList.add(new ErrorClass(rowHeader.get(j), isBlank));
						} else {
							String errorMsg = validate.isLabValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.CurrentStatus:// Column 'R' : Current Status
						if (cell == null || isCellEmpty(cell)) {
							errList.add(new ErrorClass(rowHeader.get(j), isBlank));
						} else {
							String errorMsg = validate.isCurrentStatusValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.HospitalizedStatus:// Column 'S' : Hospitalization Status
						if (cell != null && !isCellEmpty(cell)) {
							String errorMsg = validate
									.isHospitalizationStatusValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.MigrationStatus:// Column 'T' : under servillance/migrated/survillance

						String str19 = dataFormatter.formatCellValue(cell);
						str19 = str19.trim();
						if (!(str19.isEmpty() || str19 == null)) {

							String errString = validate.isUnderSurveillance_Migrated_SurveillanceCompletedValid(str19);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}

						break;

					case ValidationConstants.IfMigrated:// Column 'U' : if migrated

						String str20 = dataFormatter.formatCellValue(cell);
						str20 = str20.trim();
						if (!(str20.isEmpty() || str20 == null || str20.equals(""))) {

							String errString = validate.isIfMigratedValid(str20);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}
						break;

					case ValidationConstants.OtherState:// Column 'V' : other state name

						String str21 = dataFormatter.formatCellValue(cell);
						str21 = str21.trim();
						if (!(str21.isEmpty() || str21 == null || str21.equals(""))) {

							String errString = validate.isOtherStateNameValid(str21);
							if (errString != null) {
								ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
								errList.add(err);
							}

						}
						break;

					case ValidationConstants.Remark1:// Column 'W' : remarks

						break;

					case "FirstSampleCollectionDate":// Column 'X' : first sample collection date
						String a = dataFormatter.formatCellValue(cell).trim();
						if (!a.trim().equals("")) {
							if (validate.isDateValid(a)) {
								rowobj.setFirst_sample_collection_date(a);

								String errString = validate.isFirstSampleCollectionDateValid(rowobj);

								if (errString != null) {
									ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
									errList.add(err);
								}
							}

							else {

								rowobj.setFirst_sample_collection_date(null);
								ErrorClass err = new ErrorClass(rowHeader.get(j),
										isInvalid_Date);
								errList.add(err);
							}

						}
						break;

					case ValidationConstants.FirstSampleResultDate:// Column 'Y' : first sample result date

						String a2 = dataFormatter.formatCellValue(cell).trim();
						if (!a2.trim().equals("")) {
							if (validate.isDateValid(a2)) {

								rowobj.setFirst_sample_result_date(a2);

								String errString = validate.isFirstSampleResultDateValid(rowobj);

								if (errString != null) {
									ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
									errList.add(err);
								}
							}

							else {

								rowobj.setFirst_sample_result_date(null);
								ErrorClass err = new ErrorClass(rowHeader.get(j),
										isInvalid_Date);
								errList.add(err);
							}
						}
						break;

					case ValidationConstants.Status2:// Column 'Z' : Result
						if (cell != null && !isCellEmpty(cell)) {
							String errorMsg = validate.isResultValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.NIVNumber1:// Column 'AA' : NIV no
						if (cell != null && !isCellEmpty(cell)) {
							String errorMsg = validate.isNivValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.SecondSampleCollectionDate:// Column 'AB' : second sample collection date

						String a1 = dataFormatter.formatCellValue(cell).trim();
						if (!a1.trim().equals("")) {
							if (validate.isDateValid(a1)) {

								rowobj.setSecond_sample_collection_date(a1);

								String errString = validate.isSecondSampleCollectionDateValid(rowobj);

								if (errString != null) {
									ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
									errList.add(err);
								}
							} else {

								rowobj.setSecond_sample_collection_date(null);
								ErrorClass err = new ErrorClass(rowHeader.get(j),isInvalid_Date);
								errList.add(err);
							}
						}

						break;

					case ValidationConstants.SecondSampleResultDate:// Column 'AC' : second sample result date

						String a3 = dataFormatter.formatCellValue(cell).trim();
						if (!a3.trim().equals("")) {
							if (validate.isDateValid(a3)) {

								rowobj.setSecond_sample_result_date(a3);

								String errString = validate.isSecondSampleResultDateValid(rowobj);

								if (errString != null) {
									ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
									errList.add(err);
								}
							}

							else {

								rowobj.setSecond_sample_result_date(null);
								ErrorClass err = new ErrorClass(rowHeader.get(j),isInvalid_Date);
								errList.add(err);
							}
						}
						break;

					case ValidationConstants.Status3:// Column 'AD' : Result
						if (cell != null && !isCellEmpty(cell)) {
							String errorMsg = validate.isResultValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.NIVNumber2:// Column 'AE' : NIV no
						if (cell != null && !isCellEmpty(cell)) {
							String errorMsg = validate.isNivValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.Remark2:// Column 'AF' : remark

						break;

					case ValidationConstants.Country:// Column 'AG' : Country Of Visit
						if (cell != null && !isCellEmpty(cell)) {
							String errorMsg = validate.isCountryValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					case ValidationConstants.DateOfLeaving:// Column 'AH' : date of leaving affected country

						String a4 = dataFormatter.formatCellValue(cell).trim();
						if (!a4.trim().equals("")) {
							if (validate.isDateValid(a4)) {

								rowobj.setDate_leaving_country(a4);

							}

							else {

								rowobj.setDate_leaving_country(null); //
								ErrorClass err = new ErrorClass(rowHeader.get(j), isInvalid_Date); //
								errList.add(err);
							}
						}

						break;

					case ValidationConstants.DateOfArriving:// Column 'AI' : date of arrival from affected country
						String a5 = dataFormatter.formatCellValue(cell).trim();
						if (!a5.trim().equals("")) {
							if (validate.isDateValid(a5)) {

								rowobj.setDate_arriving_country(a5);

								String errString = validate.isDate_arriving_countryValid(rowobj);

								if (errString != null) {
									ErrorClass err = new ErrorClass(rowHeader.get(j), errString);
									errList.add(err);
								}
							}

							else {
								rowobj.setDate_arriving_country(null);
								ErrorClass err = new ErrorClass(rowHeader.get(j), isInvalid_Date);
								errList.add(err);
							}
						}

						break;

					case ValidationConstants.SourceOfInfo:// Column 'AJ' : Source Of Information
						if (cell != null && !isCellEmpty(cell)) {
							String errorMsg = validate.isSourceOfInfoValid(dataFormatter.formatCellValue(cell).trim());
							if (errorMsg != null) {
								errList.add(new ErrorClass(rowHeader.get(j), errorMsg));
							}
						}
						break;

					default:
						break;

					}


				}

				if (errList.size() > 0)
					errors.add(new ErrorRowJson(rowNo, errList));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new JsonResponse(rowHeader, errors);
	}

	public Response<?> uploadExcelFile(MultipartFile file,String userName) throws IOException {

		User user=userRepo.findByEmail(userName);
		Response<?> response = new Response<>();
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
		byte data[]=new byte[(int) file.getSize()];
		data=file.getBytes();
		fileRecords.setFile(data);
		System.out.println(file.getName());

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

	public  Response<?> downloadFromDB(String userId)
	{
		Response<DownloadJsonResponse> response = new Response<>();
		System.out.println(userId);
		User user=userRepo.findByEmail(userId);
		System.out.println(user);
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
			System.out.println(d.getResponse().get(0).getTimestamp());
			
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
     FileResponse file=dataRepository.getFile(timeStamp,email);   
    
     return file;
    }
}
