package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poslovna.informatika.entities.User;
import com.poslovna.informatika.repository.UserRepository;

public class UserService {
	
	@Autowired
	UserRepository ur;
	
	public User findOne(Integer id){
		return ur.findOne(id);
	}
	
	public List<User> findAll(){
		return ur.findAll();
	}
	
	public void delete(Integer id){
		ur.delete(id);
	}
	
	public User save(User user){
		return ur.save(user);
	}
	
	public boolean login(User user){
		User checkUser = ur.findOne(user.getId());
		if(checkUser.getPassword().equals(user.getPassword()))
			return true;

		return false;
	}

}
