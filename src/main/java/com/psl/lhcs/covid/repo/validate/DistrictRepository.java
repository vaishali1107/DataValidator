package com.psl.lhcs.covid.repo.validate;

import org.springframework.data.jpa.repository.JpaRepository;


import com.psl.lhcs.covid.model.validate.DistrictMaster;

public interface DistrictRepository extends JpaRepository<DistrictMaster, Integer> {

}
