package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospitalization_status")
public class HospitalizationStatusMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "hosp_status_id")
	private int hospStatusId;
	
	@Column(name = "hosp_status")
	private String status;

	public int getHospStatusId() {
		return hospStatusId;
	}

	public void setHospStatusId(int hospStatusId) {
		this.hospStatusId = hospStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HospitalizationStatus [hospStatusId=" + hospStatusId + ", status=" + status + "]";
	}
	
	
	
}
