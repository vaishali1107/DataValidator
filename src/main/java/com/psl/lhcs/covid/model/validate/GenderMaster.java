package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gender")
public class GenderMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "gender_id")
	private int genderId;
	
	@Column(name = "gender")
	private String gender;

	public int getgenderId() {
		return genderId;
	}

	public void setgenderId(int genderId) {
		this.genderId = genderId;
	}

	public String getgender() {
		return gender;
	}

	public void setgender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "gender [genderId=" + genderId + ", gender=" + gender + "]";
	}

}
