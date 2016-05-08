package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.DnevnoStanjeRacuna;
import com.poslovna.informatika.repository.DnevnoStanjeRacunaRepository;

@Service
public class DnevnoStanjeRacunaService {
	
	@Autowired
	DnevnoStanjeRacunaRepository dnevnoStanjeRacunaRepository;
	
	public DnevnoStanjeRacuna findOne(Integer id){
		return dnevnoStanjeRacunaRepository.findOne(id);
	}
	
	public List<DnevnoStanjeRacuna> findAll(){
		return dnevnoStanjeRacunaRepository.findAll();
	}
	
	public DnevnoStanjeRacuna save(DnevnoStanjeRacuna dnevnoStanjeRacuna){
		return dnevnoStanjeRacunaRepository.save(dnevnoStanjeRacuna);
	}
	
	public void remove(Integer id){
		dnevnoStanjeRacunaRepository.delete(id);
	}
	
}
