
package com.psl.lhcs.covid.model.validate;

import java.util.List;


public class JsonResponse {


	public List<String> responseHeader;
	public List<ErrorRowJson> errorRows;
	public JsonResponse(List<String> responseHeader, List<ErrorRowJson> errorRows) {
		super();
		this.responseHeader = responseHeader;
		this.errorRows = errorRows;
	}
	
	public JsonResponse() {
	}	
	
	
}