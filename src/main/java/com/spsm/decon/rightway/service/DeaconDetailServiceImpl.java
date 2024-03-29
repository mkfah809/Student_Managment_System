package com.spsm.decon.rightway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.Deacon;
import com.spsm.decon.rightway.repository.DeaconRepository;
import com.spsm.decon.rightway.security.CustomSecurityDecon;

@Service
public class DeaconDetailServiceImpl implements UserDetailsService {
	@Autowired
	DeaconRepository deconRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Deacon deacon = deconRepo.findByUsername(username);		
		return new CustomSecurityDecon(deacon); 
	}

}
