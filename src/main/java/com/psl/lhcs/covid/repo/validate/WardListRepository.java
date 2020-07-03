package com.psl.lhcs.covid.repo.validate;

import org.springframework.data.jpa.repository.JpaRepository;


import com.psl.lhcs.covid.model.validate.WardListMaster;

public interface WardListRepository extends JpaRepository<WardListMaster, Integer>{

}
