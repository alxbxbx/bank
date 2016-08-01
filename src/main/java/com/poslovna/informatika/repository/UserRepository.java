package com.poslovna.informatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.informatika.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
