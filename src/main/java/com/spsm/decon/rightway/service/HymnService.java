package com.spsm.decon.rightway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Hymn;
import com.spsm.decon.rightway.repository.HymnRepository;

@Service
public class HymnService {
	@Autowired
	HymnRepository hymnRepo;

	public Hymn save(Hymn hymn) {
		if(hymn.getHymnId() == null) {} 
	
			
		
		return hymnRepo.save(hymn);
	}
}
