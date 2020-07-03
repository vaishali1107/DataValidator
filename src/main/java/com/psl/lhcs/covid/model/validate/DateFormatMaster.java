package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "date_format")
public class DateFormatMaster {


	@Id
	@GeneratedValue
	@Column( name = "date_format_id")
	public int dateFomatId;
	
	@Column (name = "date_format_type")
	public String dateFormatType;

	@Column (length=2048,name = "date_format_regex")
	public String dateFormatRegex;
	
	public String getDateFormatRegex() {
		return dateFormatRegex;
	}

	public void setDateFormatRegex(String dateFormatRegex) {
		this.dateFormatRegex = dateFormatRegex;
	}

	public int getDateFomatId() {
		return dateFomatId;
	}

	public void setDateFomatId(int dateFomatId) {
		this.dateFomatId = dateFomatId;
	}

	public String getDateFormatType() {
		return dateFormatType;
	}

	public void setDateFormatType(String dateFormatType) {
		this.dateFormatType = dateFormatType;
	}

	@Override
	public String toString() {
		return "DateFormat [dateFomatId=" + dateFomatId + ", dateFormatType=" + dateFormatType + "]";
	}

	
	
}
