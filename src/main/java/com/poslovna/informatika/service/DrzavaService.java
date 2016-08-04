package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.Drzava;
import com.poslovna.informatika.repository.DrzavaRepository;

@Service("drzavaService")
public class DrzavaService {
		
	@Autowired
	DrzavaRepository drzavaRepository;
	
	public Drzava findOne(Integer id){
		return drzavaRepository.findOne(id);
	}
	
	public List<Drzava> findAll(){
		return drzavaRepository.findAll();
	}
	
	public Drzava save(Drzava drzava){
		return drzavaRepository.save(drzava);
	}
	
	public void remove(Integer id){
		drzavaRepository.delete(id);
	}
	
	public Drzava findByNazivDrzave(String nazivDrzave){
		return drzavaRepository.findByNazivDrzave(nazivDrzave);
	}
}
