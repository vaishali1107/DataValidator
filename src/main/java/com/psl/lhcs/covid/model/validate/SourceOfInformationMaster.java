package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "source_info")
public class SourceOfInformationMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "source_id")
	private int sourceId;
	
	@Column(name = "source_info")
	private String sourceOfInformation;

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceOfInformation() {
		return sourceOfInformation;
	}

	public void setSourceOfInformation(String sourceOfInformation) {
		this.sourceOfInformation = sourceOfInformation;
	}

	@Override
	public String toString() {
		return "SourceOfInformation [sourceId=" + sourceId + ", sourceOfInformation=" + sourceOfInformation + "]";
	}
	
}
