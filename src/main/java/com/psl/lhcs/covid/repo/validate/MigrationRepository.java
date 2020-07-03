package com.psl.lhcs.covid.repo.validate;

import org.springframework.data.jpa.repository.JpaRepository;


import com.psl.lhcs.covid.model.validate.MigrationMaster;

public interface MigrationRepository extends JpaRepository<MigrationMaster, Integer>{

}