package com.spsm.decon.rightway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.Decon;
import com.spsm.decon.rightway.repository.DeconRepository;
import com.spsm.decon.rightway.security.CustomSecurityDecon;

@Service
public class DeconDetailServiceImpl implements UserDetailsService {
	@Autowired
	DeconRepository deconRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Decon decon = deconRepo.findByUsername(username);		
		return new CustomSecurityDecon(decon); 
	}

}
