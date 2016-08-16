package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.Valuta;
import com.poslovna.informatika.repository.ValutaRepository;

@Service("valutaService")
public class ValutaService {
	
	@Autowired
	ValutaRepository valutaRepository;
	
	public Valuta findOne(Integer id){
		return valutaRepository.findOne(id);
	}
	
	public List<Valuta> findAll(){
		return valutaRepository.findAll();
	}
	
	public Valuta save(Valuta valuta){
		return valutaRepository.save(valuta);
	}
	
	public void remove(Integer id){
		valutaRepository.delete(id);
	}
}
