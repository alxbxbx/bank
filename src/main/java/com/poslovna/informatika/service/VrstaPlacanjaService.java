package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.VrstaPlacanja;
import com.poslovna.informatika.repository.VrstaPlacanjaRepository;

@Service
public class VrstaPlacanjaService {

	@Autowired
	VrstaPlacanjaRepository vrstaPlacanjaRepository;
	
	public VrstaPlacanja findOne(Integer id){
		return vrstaPlacanjaRepository.findOne(id);
	}
	
	public List<VrstaPlacanja> findAll(){
		return vrstaPlacanjaRepository.findAll();
	}
	
	public VrstaPlacanja save(VrstaPlacanja vrstaPlacanja){
		return vrstaPlacanjaRepository.save(vrstaPlacanja);
	}
	
	public void remove(Integer id){
		vrstaPlacanjaRepository.delete(id);
	}
}
