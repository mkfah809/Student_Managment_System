package com.spsm.decon.rightway.repository;

import java.util.List;

import com.spsm.decon.rightway.dto.Decon;

public interface DeconServiceRepoistory {
	Decon save(Decon decon);
	List<Decon> findAll();
	Decon findById(Long deconId);
	
}
