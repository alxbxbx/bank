package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.Ukidanje;
import com.poslovna.informatika.repository.UkidanjeRepository;

@Service
public class UkidanjeService {
	
	@Autowired
	UkidanjeRepository ukidanjeRepository;
	
	public Ukidanje findOne(Integer id){
		return ukidanjeRepository.findOne(id);
	}
	
	public List<Ukidanje> findAll(){
		return ukidanjeRepository.findAll();
	}
	
	public Ukidanje save(Ukidanje ukidanje){
		return ukidanjeRepository.save(ukidanje);
	}
	
	public void remove(Integer id){
		ukidanjeRepository.delete(id);
	}
}
