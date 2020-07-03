
package com.psl.lhcs.covid.model.validate;

public class ErrorClass {
	private  String errorColumn;
	private String errorMsg;
	
	public ErrorClass() {

	}

	public String getErrorColumn() {
		return errorColumn;
	}

	public void setErrorColumn(String errorColumn) {
		this.errorColumn = errorColumn;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ErrorClass(String errorColumn, String errorMsg) {
		super();
		this.errorColumn = errorColumn;
		this.errorMsg = errorMsg;
	}
	
}
