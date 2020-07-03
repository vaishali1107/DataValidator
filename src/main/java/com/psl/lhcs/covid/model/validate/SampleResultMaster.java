package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sample_result")
public class SampleResultMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "s_result_id")
	public int sResultId;
	
	@Column(name = "s_result")
	public String sResult;

	public int getsResultId() {
		return sResultId;
	}

	public void setsResultId(int sResultId) {
		this.sResultId = sResultId;
	}

	public String getsResult() {
		return sResult;
	}

	public void setsResult(String sResult) {
		this.sResult = sResult;
	}

	@Override
	public String toString() {
		return "SampleResult [sResultId=" + sResultId + ", sResult=" + sResult + "]";
	}

}
