package com.psl.lhcs.covid.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psl.lhcs.covid.model.login.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	User findByEmail(String email);   

	User findByEmailAndPassword(String email, String password);

	User findByPassword(String password);
	
	User findByContact(String contact);

}
