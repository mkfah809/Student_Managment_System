package com.spsm.decon.rightway.interfaces;

import java.util.List;
import java.util.Set;

import com.spsm.decon.rightway.dto.Authority;
import com.spsm.decon.rightway.dto.Deacon;

public interface DeaconServiceInterface {
	Deacon save(Deacon decon);

	Deacon findByUsername(String username);

	Deacon findById(Long deconId);
	
	Deacon findByEmail(String email);

	void setAddressToOneExactUser(Deacon decon);

	void sendMailForPasswordGenerator(Deacon decon);

	List<Deacon> findAll();

	String generateTempPassword();

	void setAuthorityToOneExactUser(Deacon deacon, String privilege);

}
