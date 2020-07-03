package com.psl.lhcs.covid.model.validate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.psl.lhcs.covid.repo.validate.CountryRepository;
import com.psl.lhcs.covid.repo.validate.DateFormatRepository;
import com.psl.lhcs.covid.repo.validate.DistrictRepository;
import com.psl.lhcs.covid.repo.validate.GenderRepository;
import com.psl.lhcs.covid.repo.validate.HospitalizationStatusRepository;
import com.psl.lhcs.covid.repo.validate.MigrationRepository;
import com.psl.lhcs.covid.repo.validate.SampleCollectedRepository;
import com.psl.lhcs.covid.repo.validate.SampleResultRepository;
import com.psl.lhcs.covid.repo.validate.SourceOfInformationRepository;
import com.psl.lhcs.covid.repo.validate.StateRepository;
import com.psl.lhcs.covid.repo.validate.StatusRepository;
import com.psl.lhcs.covid.repo.validate.SurveillanceRepository;
import com.psl.lhcs.covid.repo.validate.TestingLabsRepository;
import com.psl.lhcs.covid.repo.validate.TraceableRepository;
import com.psl.lhcs.covid.repo.validate.WardListRepository;

@Service
public class ValidationClass {


//-----------regex expressions---------

	@Value("${valid.contactRegex}")
	private String contactRegex;

	@Value("${valid.alphaNumericRegex}")
	private String alphaNumericRegex;

	@Value("${valid.numericRegex}")
	private String numericRegex;

	@Value("${valid.addressRegex}")
	private String addressRegex;

	@Value("${valid.stringRegex}")
	private String stringRegex;

	@Value("${valid.hospitalNameRegex}")
	private String hospitalNameRegex;

//-----------------error messages------------	

	@Value("${message.isBlank}")
	private String isBlank;

	@Value("${message.invalid_data_format}")
	private String invalid_data_format;

	@Value("${message.isInvalid_Date}")
	private String isInvalid_Date;

	@Value("${message.isInvalid_Date_of_FirstSampleS}")
	private String isInvalid_Date_of_FirstSampleS;

	@Value("${message.isInvalid_Date_of_FirstSampleR}")
	private String isInvalid_Date_of_FirstSampleR;

	@Value("${message.isInvalid_Date_of_SecondSample}")
	private String isInvalid_Date_of_SecondSample;

	@Value("${message.isInvalid_Date_of_SecondSampleR}")
	private String isInvalid_Date_of_SecondSampleR;

	@Value("${message.isInvalid_Date_of_ArrivalS}")
	private String isInvalid_Date_of_ArrivalS;

	@Value("${message.isNull}")
	private String isNull;

	@Value("${message.isInvalid_Date_of_ObservationDI}")
	private String isInvalid_Date_of_ObservationDI;

	// -----------DB Repositories--------

	@Autowired
	GenderRepository genderRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	TestingLabsRepository testingLabsRepository;

	@Autowired
	SourceOfInformationRepository sourceOfInformationRepository;

	@Autowired
	WardListRepository wardListRepository;

	@Autowired
	HospitalizationStatusRepository hospitalizationStatusRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	SampleResultRepository sampleResultRepository;

	@Autowired
	TraceableRepository traceableRepository;

	@Autowired
	SampleCollectedRepository sampleCollectedRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	MigrationRepository migrationRepository;

	@Autowired
	SurveillanceRepository surveillanceRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	DateFormatRepository dateFormatRepository;

	// ----------DB List------------

	private List<SampleCollectedMaster> sampleCollectedList;
	private List<TraceableMaster> traceableList;
	private List<WardListMaster> wardListMaster;
	private List<HospitalizationStatusMaster> hospStatusList;
	private List<StatusMaster> statusList;
	private List<TestingLabsMaster> labList;
	private List<GenderMaster> genderList;
	private List<SampleResultMaster> sampleResultList;
	private List<SurveillanceMaster> surveillanceList;
	private List<MigrationMaster> migrationList;
	private List<StateMaster> stateList;
	private List<SourceOfInformationMaster> sourceOfInformationList;
	private List<CountryMaster> countryList;
	private List<DistrictMaster> districtList;
	
	public String splitData(String data, String del) {
		String[] arr = data.split(del);

		String finalString = "";
		for (String str : arr) {
			if (!str.equals(""))
				finalString = finalString + str.trim() + del;
		}

		if(finalString.length()!=0)
		finalString = finalString.substring(0, finalString.length() - 1);

		return finalString;

	}

	public String isSRNoValid(String srNo) {
		if (!isNullOrEmpty(srNo)) {
			srNo = srNo.trim();
			int no;
			try {
				no = Integer.parseInt(srNo);
			} catch (NumberFormatException e) {
				return invalid_data_format;
			}
			if (no > 0) {
				return null;
			} else
				return invalid_data_format;
		}
		return null;
	}

	// mandory
	public String isDistrictValid(String district) {
		
		if (!isNullOrEmpty(district)) {
			district = district.trim();
			if (districtList == null) {
				districtList = districtRepository.findAll();
			}
			for (DistrictMaster districtValue : districtList) {
				if (districtValue.getDistrictName().equalsIgnoreCase(district))
					return null;
			}
			return invalid_data_format;
		}
		return isNull;

	}

	public String isNameValid(String name) {

		if (name.trim().length() < 3)
			return invalid_data_format;

		if (name.trim().equalsIgnoreCase("null"))
			return isNull;

		if (!name.trim().matches(stringRegex)) {
			return invalid_data_format;
		}
		return null;
	}

	public String isAgeValid(String age) {

		if (age.contains(".") || age.contains("-"))
			return invalid_data_format;

		if (!age.trim().matches(numericRegex))
			return invalid_data_format;

		int ageInt = Integer.parseInt(age.trim());

		if (!(0 <= ageInt && ageInt <= 110))
			return invalid_data_format;
		return null;
	}

	public String isContactValid(String contact) {

		if (!contact.trim().matches(contactRegex))
			return invalid_data_format;
		return null;
	}

	public String isAddressValid(String address) {

		if (address.trim().equalsIgnoreCase("null"))
			return isNull;

		if (!address.trim().matches(addressRegex))
			return invalid_data_format;

		return null;
	}

	public String isgenderValid(String gender) {

		if (!isNullOrEmpty(gender)) {
			gender = gender.trim();
			if (genderList == null) {
				genderList = genderRepository.findAll();
			}
			if (gender.contains(" ")) {
				gender = splitData(gender, " ");
			}
			for (GenderMaster genderValue : genderList) {
				if (genderValue.getgender().equalsIgnoreCase(gender))
					return null;
			}
			return invalid_data_format;
		}
		return isNull;
	}

	public String isZoneValid(String zone) {

		if (zone.trim().equalsIgnoreCase(null)) {
			return isNull;
		}
		if (!zone.trim().matches(alphaNumericRegex))
			return invalid_data_format;

		return null;
	}

	public String isNameOfHospitalValid(String nameOfHospital) {
		if (!nameOfHospital.trim().matches(hospitalNameRegex))
			return invalid_data_format;

		return null;

	}

	public String isSampleCollectedValid(String sample) {
		if (!isNullOrEmpty(sample)) {
			sample = sample.trim();
			if (sampleCollectedList == null) {
				sampleCollectedList = sampleCollectedRepository.findAll();
			}
			for (SampleCollectedMaster sampleCollectedMaster : sampleCollectedList) {
				if (sampleCollectedMaster.getSampleCollected().equalsIgnoreCase(sample))
					return null;
			}
			return invalid_data_format;
		}
		return isNull;
	}

	public String isTraceableValid(String traceable) {
		if (!isNullOrEmpty(traceable)) {
			traceable = traceable.trim();
			if (traceableList == null) {
				traceableList = traceableRepository.findAll();
			}
			for (TraceableMaster traceableValue : traceableList) {
				if (traceableValue.getTraceStatus().equalsIgnoreCase(traceable))
					return null;
			}
			return invalid_data_format;
		}
		return null;
	}

	public boolean isNullOrEmpty(String value) {
		if (value == null || value.equalsIgnoreCase("null") || value.isEmpty() || value.trim().isEmpty())
			return true;
		return false;
	}

public boolean isDateValid(String date) {
		
		date = date.trim();
		Date d = new Date();
		
		List<DateFormatMaster> formats = dateFormatRepository.findAll();

		
		for (DateFormatMaster dataformat : formats) {
			
			String regex=dataformat.getDateFormatRegex();
			 String format = dataformat.getDateFormatType();
			if(date.trim().matches(regex))
			{
				try {
					 
					 SimpleDateFormat sdf=new SimpleDateFormat(format); sdf.setLenient(false);
					 d=sdf.parse(date); if (d != null) { return true; } } catch (Exception e) { }
			}
		}
		return false;
	}

	public Date parseDate(String date) {
		
		if(date!=null)
		{
			date=date.trim();
			Date d = new Date();
		
		
			List<DateFormatMaster> formats = dateFormatRepository.findAll();

			for (DateFormatMaster dataformat : formats) {
				String format = dataformat.getDateFormatType();
				try {
					d = new SimpleDateFormat(format).parse(date);
					
					if (d != null)
						return d;
				} catch (Exception e) {
				}
		}
		
			
		return null;
		}else {
			return null;
		}

	}

	public String isWardValid(String ward) {
		if (!isNullOrEmpty(ward)) {
			ward = ward.trim();
			if (wardListMaster == null) {
				wardListMaster = wardListRepository.findAll();
			}
			if (ward.contains("-")) {
				ward = splitData(ward, "-");

			} else if (ward.contains(" ")) {
				ward = splitData(ward, " ");
			}
			for (WardListMaster wardName : wardListMaster) {
				if (wardName.getWardName().contains("-")) {
					wardName.setWardName(splitData(wardName.getWardName(), "-"));
				}

				if (wardName.getWardName().equalsIgnoreCase(ward)) {
					return null;
				}

			}

			return invalid_data_format;
		}
		return isNull;
	}

	public String isHospitalizationStatusValid(String hospStatus) {
		if (!isNullOrEmpty(hospStatus)) {
			hospStatus = hospStatus.trim();
			if (hospStatusList == null) {
				hospStatusList = hospitalizationStatusRepository.findAll();
			}
			for (HospitalizationStatusMaster status : hospStatusList) {
				if (status.getStatus().equalsIgnoreCase(hospStatus))
					return null;
			}
			return invalid_data_format;
		}
		return null;
	}

	public String isCurrentStatusValid(String currentStatus) {

		if (!isNullOrEmpty(currentStatus)) {
			currentStatus = currentStatus.trim();
			if (statusList == null) {
				statusList = statusRepository.findAll();
			}
			if (currentStatus.contains(" ")) {
				currentStatus = splitData(currentStatus, " ");
			}
			for (StatusMaster statusMaster : statusList) {
				if (statusMaster.getStatus().equalsIgnoreCase(currentStatus))
					return null;
			}
			return invalid_data_format;
		}
		return isNull;
	}

	public String isLabValid(String lab) {
		if (!isNullOrEmpty(lab)) {
			lab = lab.trim();
			if (labList == null) {
				labList = testingLabsRepository.findAll();
			}
			if (lab.contains(" ")) {
				lab = splitData(lab, " ");
				
				for (TestingLabsMaster testingLabsMaster : labList) {
					if (testingLabsMaster.getTestingLab().equalsIgnoreCase(lab)) {
						return null;
					}
				}

			} else {
				for (TestingLabsMaster testingLabsMaster : labList) {
				
					List<String> testingLabsArr = new ArrayList<>();
					if (testingLabsMaster.getTestingLab().contains(" ")) {
						testingLabsArr = Arrays.asList(testingLabsMaster.getTestingLab().split(" "));
						String a=testingLabsMaster.getTestingLab().split(" ")[0];
						testingLabsArr = Arrays.asList(a);
					} else {
						testingLabsArr.add(testingLabsMaster.getTestingLab());
					}

					for (String k : testingLabsArr) {
						if (k.equalsIgnoreCase(lab)) {
							return null;
						}
					}

				}

			}

			return invalid_data_format;
		}
		return isNull;
		
	}

	public String isResultValid(String sampleResult) {
		if (!isNullOrEmpty(sampleResult)) {
			sampleResult = sampleResult.trim();
			if (sampleResultList == null) {
				sampleResultList = sampleResultRepository.findAll();
			}
			for (SampleResultMaster result : sampleResultList) {
				if (result.getsResult().equalsIgnoreCase(sampleResult))
					return null;
			}
			return invalid_data_format;
		}
		return isNull;
	}

	public String isNivValid(String NIVno) {
		if (!isNullOrEmpty(NIVno)) {
			// No validations.
		}
		return null;
	}

	public String isCountryValid(String country) {
		if (!isNullOrEmpty(country)) {
			country = country.trim();
			if (countryList == null) {
				countryList = countryRepository.findAll();
			}
			for (CountryMaster countryValue : countryList) {
				if (countryValue.getCountry().equalsIgnoreCase(country))
					return null;
			}
			return invalid_data_format;
		}
		return null;
	}

	public String isSourceOfInfoValid(String source) {

		if (!isNullOrEmpty(source)) {
			source = source.trim();
			if (sourceOfInformationList == null) {
				sourceOfInformationList = sourceOfInformationRepository.findAll();
			}
			if (source.contains(":")) {
				source = splitData(source, ":");

			} else if (source.contains(" ")) {
				source = splitData(source, " ");
			}

			for (SourceOfInformationMaster sourceOfInformationMaster : sourceOfInformationList) {
				if (sourceOfInformationMaster.getSourceOfInformation().contains(":")) {
					sourceOfInformationMaster
							.setSourceOfInformation(splitData(sourceOfInformationMaster.getSourceOfInformation(), ":"));
				}

				if (sourceOfInformationMaster.getSourceOfInformation().equalsIgnoreCase(source))
					return null;
			}

			return invalid_data_format;
		}
		return isNull;
	}

	public String isOtherStateNameValid(String stateName) {                                       
		if (!isNullOrEmpty(stateName)) {
			stateName = stateName.trim();
			if (stateList == null) {
				stateList = stateRepository.findAll();
			}
			if(stateName.contains(" "))                                              
			{                                                                        
				stateName=splitData(stateName," ");                                  
			}                                                                        
			
			for (StateMaster state : stateList)
			{	                                                                      
				if (state.getState().trim().equalsIgnoreCase(stateName))                
					return null;
			}                                                                        
			return invalid_data_format;
		}
		return null;
	}

	public String isIfMigratedValid(String migrationStatus) {

		if (!isNullOrEmpty(migrationStatus)) {
			migrationStatus = migrationStatus.trim();
			if (migrationList == null) {
				migrationList = migrationRepository.findAll();
			}
			if (migrationStatus.contains(" ")) {
				migrationStatus = splitData(migrationStatus, " ");
			}

			for (MigrationMaster migrationMaster : migrationList) {
				if (migrationMaster.getMigrationPlace().equalsIgnoreCase(migrationStatus))
					return null;
			}
			return invalid_data_format;
		} else
			return isNull;
	}

	public String isUnderSurveillance_Migrated_SurveillanceCompletedValid(String surveillanceStatus) {

		if (!isNullOrEmpty(surveillanceStatus)) {
			surveillanceStatus = surveillanceStatus.trim();
			if (surveillanceList == null) {
				surveillanceList = surveillanceRepository.findAll();
			}
			if (surveillanceStatus.contains("-")) {
				surveillanceStatus = splitData(surveillanceStatus, "-");

			} else if (surveillanceStatus.contains(" ")) {
				surveillanceStatus = splitData(surveillanceStatus, " ");
			}

			for (SurveillanceMaster surveillanceMaster : surveillanceList) {

				if (surveillanceMaster.getSurveillanceStatus().contains("-")) {
					surveillanceMaster.setSurveillanceStatus(splitData(surveillanceMaster.getSurveillanceStatus(), "-"));
				}

				if (surveillanceMaster.getSurveillanceStatus().equalsIgnoreCase(surveillanceStatus))
					return null;
			}
			return invalid_data_format;
		}
		return isNull;
	}

	public String isFirstSampleCollectionDateValid(RowDetailsObj obj) {
		if (obj.getSample_collection_date() != null) {
			String s1 = obj.getSample_collection_date();
			String s2 = obj.getFirst_sample_collection_date();

			Date d1 = new Date();
			Date d2 = new Date();

			d1 = parseDate(s1);
			d2 = parseDate(s2);

			if(d1!=null  && d2!=null)
			if (d2.compareTo(d1) == 0) {
				return null;
			} else {

				return isInvalid_Date_of_FirstSampleS;
			}
		} 
			return null;

	}

	public String isObservationDateValid(RowDetailsObj obj) {
		if (obj.getDate_of_observation() != null ) {
			
			String s1 = obj.getDate_of_info();
			String s2 = obj.getDate_of_observation();

			Date d1 = new Date();
			Date d2 = new Date();

			
			d1 = parseDate(s1);
			d2 = parseDate(s2);

			if(d1!=null  && d2!=null)
				if (d2.compareTo(d1) >= 0) 
				{
					return null;
				} else 
				{
					return isInvalid_Date_of_ObservationDI;
				}
		} 
			return null;
	}

	public String isSecondSampleCollectionDateValid(RowDetailsObj obj) {
		if (obj.getFirst_sample_collection_date() != null) {
			String s1 = obj.getFirst_sample_collection_date();
			String s2 = obj.getSecond_sample_collection_date();

			Date d1 = new Date();
			Date d2 = new Date();

			d1 = parseDate(s1);
			d2 = parseDate(s2);

			if(d1!=null  && d2!=null)
				if (d2.compareTo(d1) > 0) {
				return null;
			} else {

				return isInvalid_Date_of_SecondSample;
			}
		} 
			return null;

	}

	public String isFirstSampleResultDateValid(RowDetailsObj obj) {
		if (obj.getFirst_sample_collection_date() != null) {
			String s1 = obj.getFirst_sample_collection_date();
			String s2 = obj.getFirst_sample_result_date();

			Date d1 = new Date();
			Date d2 = new Date();

			d1 = parseDate(s1);
			d2 = parseDate(s2);

			if(d1!=null  && d2!=null)
			if (d2.compareTo(d1) >= 0) {
				return null;
			} else {
				return isInvalid_Date_of_FirstSampleR;
			}
		} 
			return null;

	}

	public String isSecondSampleResultDateValid(RowDetailsObj obj) {
		if (obj.getSecond_sample_collection_date() != null) {
			String s1 = obj.getSecond_sample_collection_date();
			String s2 = obj.getSecond_sample_result_date();

			Date d1 = new Date();
			Date d2 = new Date();

			d1 = parseDate(s1);
			d2 = parseDate(s2);

			if(d1!=null  && d2!=null)
				if (d2.compareTo(d1) >= 0) {
				return null;
			} else {

				return isInvalid_Date_of_SecondSampleR;
			}
		} 
			return null;

	}

	public String isDate_arriving_countryValid(RowDetailsObj obj) {
		if (obj.getDate_leaving_country() != null) {
			String s1 = obj.getDate_leaving_country();
			String s2 = obj.getDate_arriving_country();

			Date d1 = new Date();
			Date d2 = new Date();

			d1 = parseDate(s1);
			d2 = parseDate(s2);

			if(d1!=null  && d2!=null)
			if (d2.compareTo(d1) >= 0) {
				return null;
			} else {

				return isInvalid_Date_of_ArrivalS;
			}
		} 
			return null;

	}

}
