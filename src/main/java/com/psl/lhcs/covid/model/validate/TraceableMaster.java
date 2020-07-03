package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traceable")
public class TraceableMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "trace_id")
	private int traceId;
	
	@Column(name = "trace_status")
	private String traceStatus;

	public int getTraceId() {
		return traceId;
	}

	public void setTraceId(int traceId) {
		this.traceId = traceId;
	}

	public String getTraceStatus() {
		return traceStatus;
	}

	public void setTraceStatus(String traceStatus) {
		this.traceStatus = traceStatus;
	}

	@Override
	public String toString() {
		return "Traceable [traceId=" + traceId + ", traceStatus=" + traceStatus + "]";
	}
	
	

}
