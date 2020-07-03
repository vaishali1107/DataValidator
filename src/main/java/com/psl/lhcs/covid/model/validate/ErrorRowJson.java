
package com.psl.lhcs.covid.model.validate;

import java.util.List;

public class ErrorRowJson {

	public int rowSrNo;
	public List<ErrorClass> errors;

	public ErrorRowJson() {
	
	}

	public ErrorRowJson(int rowSrNo, List<ErrorClass> errors) {
		super();
		this.rowSrNo = rowSrNo;
		this.errors = errors;
	}

}