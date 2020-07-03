package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testing_labs")
public class TestingLabsMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "lab_id")
	private int labId;
	
	@Column(name = "testing_lab")
	private String testingLab;

	public int getLabId() {
		return labId;
	}

	public void setLabId(int labId) {
		this.labId = labId;
	}

	public String getTestingLab() {
		return testingLab;
	}

	public void setTestingLab(String testingLab) {
		this.testingLab = testingLab;
	}

	@Override
	public String toString() {
		return "TestingLabs [labId=" + labId + ", testingLab=" + testingLab + "]";
	}
	
}
