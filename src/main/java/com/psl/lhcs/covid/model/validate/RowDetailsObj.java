package com.psl.lhcs.covid.model.validate;


public class RowDetailsObj {


	
    private String sample_collection_date="";
	private String first_sample_collection_date="";
	private String second_sample_collection_date="";
	private String first_sample_result_date="";
	private String second_sample_result_date="";
	private String date_leaving_country="";
	private String date_arriving_country="";
	private String date_of_info="";
	private String date_of_observation="";
	
	public String getSample_collection_date() {
		return sample_collection_date;
	}


	
	public String getDate_of_info() {
		return date_of_info;
	}



	public void setDate_of_info(String date_of_info) {
		this.date_of_info = date_of_info;
	}



	public String getDate_of_observation() {
		return date_of_observation;
	}



	public void setDate_of_observation(String date_of_observation) {
		this.date_of_observation = date_of_observation;
	}



	public void setSample_collection_date(String sample_collection_date) {
		this.sample_collection_date = sample_collection_date;
	}

	
	public String getFirst_sample_collection_date() {
		return first_sample_collection_date;
	}


	public void setFirst_sample_collection_date(String first_sample_collection_date) {
		this.first_sample_collection_date = first_sample_collection_date;
	}


	public String getSecond_sample_collection_date() {
		return second_sample_collection_date;
	}


	public void setSecond_sample_collection_date(String second_sample_collection_date) {
		this.second_sample_collection_date = second_sample_collection_date;
	}


	public String getFirst_sample_result_date() {
		return first_sample_result_date;
	}


	public void setFirst_sample_result_date(String first_sample_result_date) {
		this.first_sample_result_date = first_sample_result_date;
	}


	public String getSecond_sample_result_date() {
		return second_sample_result_date;
	}


	public void setSecond_sample_result_date(String second_sample_result_date) {
		this.second_sample_result_date = second_sample_result_date;
	}


	public String getDate_leaving_country() {
		return date_leaving_country;
	}


	public void setDate_leaving_country(String date_leaving_country) {
		this.date_leaving_country = date_leaving_country;
	}


	public String getDate_arriving_country() {
		return date_arriving_country;
	}


	public void setDate_arriving_country(String date_arriving_country) {
		this.date_arriving_country = date_arriving_country;
	}


	
	
}
