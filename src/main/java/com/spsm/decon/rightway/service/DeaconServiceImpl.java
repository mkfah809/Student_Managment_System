package com.spsm.decon.rightway.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Authority;
import com.spsm.decon.rightway.dto.Deacon;
import com.spsm.decon.rightway.interfaces.DeaconServiceInterface;
import com.spsm.decon.rightway.repository.DeaconRepository;

@Service
public class DeaconServiceImpl implements DeaconServiceInterface {

	@Autowired
	DeaconRepository deaconRepo;
	@Autowired
	private JavaMailSender mailSender;
	@Value("${gmail.email.subject}")
	private String emailSubjectForPassword;

	@Override
	public Deacon findById(Long deconId) {
		return deaconRepo.findById(deconId).orElse(null);

	}

	@Override
	public Deacon save(Deacon deacon) {
		if (deacon.getDeaconId() == null) {
			deacon.setPassword(new BCryptPasswordEncoder().encode(deacon.getPassword()));
			setAddressToOneExactUser(deacon);
		}
		return deaconRepo.save(deacon);
	}

	@Override
	public List<Deacon> findAll() {
		return deaconRepo.findAll();
	}

	@Override
	public void setAddressToOneExactUser(Deacon deacon) {
		Address address = new Address();
		address.setAddressLine(deacon.getAddress().getAddressLine());
		address.setZipCode(deacon.getAddress().getZipCode());
		address.setCity(deacon.getAddress().getCity());
		address.setState(deacon.getAddress().getState());
		address.setDeaconId(deacon.getDeaconId());
		address.setDeacon(deacon);
		deacon.setAddress(address);
	}

	@Override
	public void setAuthorityToOneExactUser(Deacon deacon, String privilege) {
		Authority authority = new Authority();
		Set<Authority> authorities = new HashSet<>();
		authority.setAuthority(privilege);
		authority.setDeacon(deacon);
		authorities.add(authority);
		deacon.setAuthorities(authorities);
	}

	@Override
	public String generateTempPassword() {

		return new Random().ints(48, 123).filter(num -> (num < 91 || num > 96)).limit(10).mapToObj(c -> (char) c)
				.collect(StringBuffer::new, StringBuffer::append, StringBuffer::append).toString();
	}

	@Override
	public Deacon findByUsername(String username) {
		return deaconRepo.findByUsername(username);
	}

	@Override
	public void sendMailForPasswordGenerator(Deacon deacon) {
		final String TempPassword = setEncryptPasswordToDecon(deacon);
		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo(deacon.getEmail());
				mimeMessageHelper.setText("Temporary Password: " + TempPassword, true);
				mimeMessageHelper.setSubject(emailSubjectForPassword);
			}
		});
	}

	public String setEncryptPasswordToDecon(Deacon deacon) {
		final String TempPassword = generateTempPassword();
		deacon.setPassword(TempPassword);
		deacon.setPassword(new BCryptPasswordEncoder().encode(deacon.getPassword()));
		return TempPassword;
	}

	@Override
	public Deacon findByEmail(String email) {
		return deaconRepo.findByEmail(email);
	}



}
