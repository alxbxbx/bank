package com.poslovna.informatika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.repository.DrzavaRepository;

@Service
public class DrzavaService {
		
	@Autowired
	DrzavaRepository drzavaRepository;
}
