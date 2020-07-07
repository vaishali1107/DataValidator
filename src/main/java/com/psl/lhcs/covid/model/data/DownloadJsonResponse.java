package com.psl.lhcs.covid.model.data;

import java.util.ArrayList;
import java.util.List;

public class DownloadJsonResponse {
	
	
	private  String  owner;
   List<DownloadResponse> response=new ArrayList<>();

   
	public List<DownloadResponse> getResponse() {
		return response;
	}

	public void setResponse(List<DownloadResponse> response) {
		this.response = response;
	}

	public DownloadJsonResponse(List<DownloadResponse> response) {
		super();
		this.response = response;
	}

	public DownloadJsonResponse( String owner,List<DownloadResponse> response) {
		super();
		this.response = response;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "DownloadJsonResponse [owner=" + owner + ", response=" + response + "]";
	}

	

	

	
	
	
	
	
	
	
}
