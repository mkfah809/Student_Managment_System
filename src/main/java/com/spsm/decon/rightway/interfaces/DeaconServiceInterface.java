package com.spsm.decon.rightway.interfaces;

import java.util.List;

import com.spsm.decon.rightway.dto.Deacon;

public interface DeaconServiceInterface {
	Deacon save(Deacon decon);

	Deacon findByUsername(String username);

	Deacon findById(Long deconId);
	
	Deacon findByEmail(String email);

	void setAddressToOneExactUser(Deacon decon);

	void setAuthorityToOneExactUser(Deacon decon);

	void sendMailForPasswordGenerator(Deacon decon);

	void sendMailForUsernameGenerator(Deacon decon);

	List<Deacon> findAll();

	String generateTempPassword();

}
