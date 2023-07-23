//package com.spsm.decon.rightway.service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.spsm.decon.rightway.dto.Address;
//import com.spsm.decon.rightway.dto.Authority;
//import com.spsm.decon.rightway.dto.User;
//import com.spsm.decon.rightway.interfaces.DeaconServiceInterface;
//import com.spsm.decon.rightway.repository.UserRepository;
//
//@Service
//public class DeaconServiceImpl implements DeaconServiceInterface {
//
//	@Autowired
//	UserRepository deaconRepo;
//	@Autowired
//	private JavaMailSender mailSender;
//	@Value("${gmail.email.subject}")
//	private String emailSubjectForPassword;
//
//	@Override
//	public User findById(Long deconId) {
//		return deaconRepo.findById(deconId).orElse(null);
//
//	}
//
//	@Override
//	public User save(User deacon) {
//		if (deacon.getUserId() == null) {
//			deacon.setPassword(new BCryptPasswordEncoder().encode(deacon.getPassword()));
//			setAddressToOneExactUser(deacon);
//		}
//		return deaconRepo.save(deacon);
//	}
//
//	@Override
//	public List<User> findAll() {
//		return deaconRepo.findAll();
//	}
//
//	@Override
//	public void setAddressToOneExactUser(User user) {
//		Address address = new Address();
//		address.setAddressLine(user.getAddress().getAddressLine());
//		address.setZipCode(user.getAddress().getZipCode());
//		address.setCity(user.getAddress().getCity());
//		address.setState(user.getAddress().getState());
//		address.setUserId(user.getUserId());
//		address.setUser(user);
//		user.setAddress(address);
//	}
//
//	@Override
//	public void setAuthorityToOneExactUser(User user, String privilege) {
//		Authority authority = new Authority();
//		Set<Authority> authorities = new HashSet<>();
//		authority.setAuthority(privilege);
//		authority.setUser(user);
//		authorities.add(authority);
//		user.setAuthorities(authorities);
//	}
//
//	@Override
//	public String generateTempPassword() {
//
//		return new Random().ints(48, 123).filter(num -> (num < 91 || num > 96)).limit(10).mapToObj(c -> (char) c)
//				.collect(StringBuffer::new, StringBuffer::append, StringBuffer::append).toString();
//	}
//
//	@Override
//	public User findByUsername(String username) {
//		return deaconRepo.findByUsername(username);
//	}
//
//	@Override
//	public void sendMailForPasswordGenerator(User deacon) {
//		final String TempPassword = setEncryptPasswordToDecon(deacon);
//		mailSender.send(new MimeMessagePreparator() {
//
//			@Override
//			public void prepare(MimeMessage mimeMessage) throws Exception {
//				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//				mimeMessageHelper.setTo(deacon.getEmail());
//				mimeMessageHelper.setText("Temporary Password: " + TempPassword, true);
//				mimeMessageHelper.setSubject(emailSubjectForPassword);
//			}
//		});
//	}
//
//	public String setEncryptPasswordToDecon(User deacon) {
//		final String TempPassword = generateTempPassword();
//		deacon.setPassword(TempPassword);
//		deacon.setPassword(new BCryptPasswordEncoder().encode(deacon.getPassword()));
//		return TempPassword;
//	}
//
//	@Override
//	public User findByEmail(String email) {
//		return deaconRepo.findByEmail(email);
//	}
//
//
//
//}
