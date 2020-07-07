package com.psl.lhcs.covid.model.data;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FileRecords")
public class FileRecords {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "userId")
	private String userName;
	
	@Column(name= "file")
	private byte[] file;
	
	@Column(name= "labName")
	private String labName;
	
	@Column(name = "timestamp")
	private String timestamp;
	
	@Column(name="fileName")
	private String fileName;

	@Column(name="hospitalName")
	private String hospitalName;
	
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "ExcelFile [id=" + id + ", userName=" + userName + ", file=" + Arrays.toString(file) + ", labName="
				+ labName + ", timestamp=" + timestamp + ", fileName=" + fileName + "]";
	}


}
