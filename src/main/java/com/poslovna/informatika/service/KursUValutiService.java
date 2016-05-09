package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.KursUValuti;
import com.poslovna.informatika.repository.KursUValutiRepository;

@Service
public class KursUValutiService {
	
	@Autowired
	KursUValutiRepository kursUValutiRepository;
	
	public KursUValuti findOne(Integer id){
		return kursUValutiRepository.findOne(id);
	}
	
	public List<KursUValuti> findAll(){
		return kursUValutiRepository.findAll();
	}
	
	public KursUValuti save(KursUValuti kursUValuti){
		return kursUValutiRepository.save(kursUValuti);
	}
	
	public void remove(Integer id){
		kursUValutiRepository.delete(id);
	}
}
