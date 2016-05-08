package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.AnalitikaPreseka;
import com.poslovna.informatika.repository.AnalitikaPresekaRepository;

@Service
public class AnalitikaPresekaService {
	
	@Autowired
	AnalitikaPresekaRepository analitikaPresekaRepository;
	
	public AnalitikaPreseka findOne(Integer id){
		return analitikaPresekaRepository.findOne(id);
	}
	
	public List<AnalitikaPreseka> findAll(){
		return analitikaPresekaRepository.findAll();
	}
	
	public AnalitikaPreseka save(AnalitikaPreseka analitikaPreseka){
		return analitikaPresekaRepository.save(analitikaPreseka);
	}
	
	public void remove(Integer id){
		analitikaPresekaRepository.delete(id);
	}
}
