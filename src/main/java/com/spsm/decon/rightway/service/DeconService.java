package com.spsm.decon.rightway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Decon;
import com.spsm.decon.rightway.repository.DeconRepository;

@Service
public class DeconService {
	@Autowired
	DeconRepository deconRepo;

	public Decon save(Decon decon) {
		if(decon.getDeconId() == null) {
			Address address = new Address();
			address.setAddressLine(decon.getAddress().getAddressLine());
			address.setZipCode(decon.getAddress().getZipCode());
			address.setDecon(decon);
			decon.setAddress(address);
			System.out.println(address);
			
		}
		return deconRepo.save(decon);
	}
}
