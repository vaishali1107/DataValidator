package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sample_collected")
public class SampleCollectedMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "sample_id")
	private int sampleId;
	
	@Column(name = "sample_collected")
	private String sampleCollected;

	public int getSampleId() {
		return sampleId;
	}

	public void setSampleId(int sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleCollected() {
		return sampleCollected;
	}

	public void setSampleCollected(String sampleCollected) {
		this.sampleCollected = sampleCollected;
	}

	@Override
	public String toString() {
		return "SampleCollected [sampleId=" + sampleId + ", sampleCollected=" + sampleCollected + "]";
	}
	

}
