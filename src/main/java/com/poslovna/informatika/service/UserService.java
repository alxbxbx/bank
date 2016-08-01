package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poslovna.informatika.entities.User;
import com.poslovna.informatika.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
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
	
	public User login(String username, String password){
		User checkUser = ur.findByUsername(username);
		if(checkUser.getPassword().equals(password))
			return checkUser;

		return null;
	}

}
