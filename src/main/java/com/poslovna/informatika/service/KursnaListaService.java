package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.KursnaLista;
import com.poslovna.informatika.repository.KursnaListaRepository;

@Service("kursnaListaService")
public class KursnaListaService {
	
	@Autowired
	KursnaListaRepository kursnaListaRepository;
	
	public KursnaLista findOne(Integer id){
		return kursnaListaRepository.findOne(id);
	}
	
	public List<KursnaLista> findAll(){
		return kursnaListaRepository.findAll();
	}
	
	public KursnaLista save(KursnaLista kursnaLista){
		return kursnaListaRepository.save(kursnaLista);
	}
	
	public void remove(Integer id){
		kursnaListaRepository.delete(id);
	}
}
