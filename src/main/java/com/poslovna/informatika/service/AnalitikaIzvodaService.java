package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.AnalitikaIzvoda;
import com.poslovna.informatika.repository.AnalitikaIzvodaRepository;

@Service
public class AnalitikaIzvodaService {
	
	@Autowired
	AnalitikaIzvodaRepository analitikaIzvodaRepository;
	
	public AnalitikaIzvoda findOne(Integer id){
		return analitikaIzvodaRepository.findOne(id);
	}
	
	public List<AnalitikaIzvoda> findAll(){
		return analitikaIzvodaRepository.findAll();
	}
	
	public void remove(Integer id){
		analitikaIzvodaRepository.delete(id);
	}

}
