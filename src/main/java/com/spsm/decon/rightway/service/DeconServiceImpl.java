package com.spsm.decon.rightway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Decon;
import com.spsm.decon.rightway.repository.DeconRepository;
import com.spsm.decon.rightway.repository.DeconServiceRepoistory;

@Service
public class DeconServiceImpl implements DeconServiceRepoistory {

	@Autowired
	DeconRepository deconRepo;

	@Override
	public Decon findById(Long deconId) {
		return deconRepo.findById(deconId).orElse(null);

	}

	@Override
	public Decon save(Decon decon) {
		Address address = new Address();
		address.setAddressLine(decon.getAddress().getAddressLine());
		address.setZipCode(decon.getAddress().getZipCode());
		address.setDeconId(decon.getDeconId());
		address.setDecon(decon);
		decon.setAddress(address);
		return deconRepo.save(decon);
	}

	@Override
	public List<Decon> findAll() {
		return deconRepo.findAll();

	}

}
