package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.PrenosIzvoda;
import com.poslovna.informatika.repository.PrenosIzvodaRepository;

@Service
public class PrenosIzvodaService{
	
	@Autowired
	PrenosIzvodaRepository prenosIzvodaRepository;
	
	public PrenosIzvoda findOne(Integer id){
		return prenosIzvodaRepository.findOne(id);
	}
	
	public List<PrenosIzvoda> findAll(){
		return prenosIzvodaRepository.findAll();
	}
	
	public PrenosIzvoda save(PrenosIzvoda prenosIzvoda){
		return prenosIzvodaRepository.save(prenosIzvoda);
	}
	
	public void remove(Integer id){
		prenosIzvodaRepository.delete(id);
	}
}
