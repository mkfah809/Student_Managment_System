package com.spsm.decon.rightway.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Authority;
import com.spsm.decon.rightway.dto.Decon;
import com.spsm.decon.rightway.dto.Hymn;
import com.spsm.decon.rightway.dto.Log;

public class CustomSecurityDecon extends Decon implements UserDetails {

	private static final long serialVersionUID = 1L;

	public CustomSecurityDecon() {} 
	
	public CustomSecurityDecon(Decon decon) {
		this.setAuthorities(decon.getAuthorities());
		this.setDeconId(getDeconId());
		this.setPassword(decon.getPassword());
		this.setUsername(decon.getUsername());
		this.setAddress(decon.getAddress());
		this.setHymns(decon.getHymns());
		this.setLogs(decon.getLogs());
		this.setFirstName(decon.getFirstName());
		this.setFirstName(decon.getLastName());
		this.setDateOfBirth(decon.getDateOfBirth());
		this.setEmail(decon.getEmail());
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}





}
