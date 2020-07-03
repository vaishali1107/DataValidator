package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "surveillance_status")
public class SurveillanceMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="surveillance_id")
	private int surveillanceId;
	
	@Column(name="surveillance_status")
	private String surveillanceStatus;

	public int getSurveillanceId() {
		return surveillanceId;
	}

	public void setSurveillanceId(int surveillanceId) {
		this.surveillanceId = surveillanceId;
	}

	public String getSurveillanceStatus() {
		return surveillanceStatus;
	}

	public void setSurveillanceStatus(String surveillanceStatus) {
		this.surveillanceStatus = surveillanceStatus;
	}

	@Override
	public String toString() {
		return "Surveillance [surveillanceId=" + surveillanceId + ", surveillanceStatus=" + surveillanceStatus + "]";
	}
	
	
}
