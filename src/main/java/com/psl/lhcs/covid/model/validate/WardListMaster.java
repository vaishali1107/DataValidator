package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ward_list")
public class WardListMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column( name = "ward_id")
	public int wardId;
	
	@Column (name = "ward_name")
	public String wardName;

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	@Override
	public String toString() {
		return "WardList [wardId=" + wardId + ", wardName=" + wardName + "]";
	}
	
}
