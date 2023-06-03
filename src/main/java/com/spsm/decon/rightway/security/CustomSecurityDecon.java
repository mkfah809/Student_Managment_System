package com.spsm.decon.rightway.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Authority;
import com.spsm.decon.rightway.dto.Deacon;
import com.spsm.decon.rightway.dto.Hymn;
import com.spsm.decon.rightway.dto.Log;

public class CustomSecurityDecon extends Deacon implements UserDetails {

	private static final long serialVersionUID = 1L;

	public CustomSecurityDecon() {} 
	
	public CustomSecurityDecon(Deacon deacon) {
		this.setAuthorities(deacon.getAuthorities());
		this.setDeaconId(getDeaconId());
		this.setPassword(deacon.getPassword());
		this.setUsername(deacon.getUsername());
		this.setAddress(deacon.getAddress());
		this.setHymns(deacon.getHymns());
		this.setLogs(deacon.getLogs());
		this.setFirstName(deacon.getFirstName());
		this.setFirstName(deacon.getLastName());
		this.setDateOfBirth(deacon.getDateOfBirth());
		this.setEmail(deacon.getEmail());
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
