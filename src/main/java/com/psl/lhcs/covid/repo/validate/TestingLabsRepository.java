package com.psl.lhcs.covid.repo.validate;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.psl.lhcs.covid.model.validate.TestingLabsMaster;

@Repository(value = "testingLabsRepository")
public interface TestingLabsRepository extends JpaRepository<TestingLabsMaster, Integer>{

}
