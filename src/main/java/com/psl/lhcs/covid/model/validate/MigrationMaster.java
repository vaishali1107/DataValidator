package com.psl.lhcs.covid.model.validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "migration")
public class MigrationMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "migration_id")
	private int migrationId;
	
	@Column(name = "migration_place")
	private String migrationPlace;

	public int getMigrationId() {
		return migrationId;
	}

	public void setMigrationId(int migrationId) {
		this.migrationId = migrationId;
	}

	public String getMigrationPlace() {
		return migrationPlace;
	}

	public void setMigrationPlace(String migrationPlace) {
		this.migrationPlace = migrationPlace;
	}

	@Override
	public String toString() {
		return "Migration [migrationId=" + migrationId + ", migrationPlace=" + migrationPlace + "]";
	}

}
