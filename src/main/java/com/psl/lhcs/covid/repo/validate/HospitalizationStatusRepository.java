package com.psl.lhcs.covid.repo.validate;

import org.springframework.data.jpa.repository.JpaRepository;


import com.psl.lhcs.covid.model.validate.HospitalizationStatusMaster;

public interface HospitalizationStatusRepository extends JpaRepository<HospitalizationStatusMaster, Integer>{

}
