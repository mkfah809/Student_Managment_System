package com.spsm.decon.rightway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Hymn;
import com.spsm.decon.rightway.repository.HymnRepository;
import com.spsm.decon.rightway.repository.HymnServiceRepoistory;

@Service
public class HymnServiceImpl implements HymnServiceRepoistory {
	@Autowired
	HymnRepository hymnRepo;

	@Override
	public Hymn save(Hymn hymn) {
		if (hymn.getHymnId() != null) {
			System.out.println("HYMN EXIST");
		} else {
			System.out.println("NEW HYMN");
		}
		return hymnRepo.save(hymn);
	}

}
