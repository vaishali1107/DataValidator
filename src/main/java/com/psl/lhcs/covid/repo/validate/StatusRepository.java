package com.psl.lhcs.covid.repo.validate;

import org.springframework.data.jpa.repository.JpaRepository;


import com.psl.lhcs.covid.model.validate.StatusMaster;

public interface StatusRepository extends JpaRepository<StatusMaster, Integer>{

}
