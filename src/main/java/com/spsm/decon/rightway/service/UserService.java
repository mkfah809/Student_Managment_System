package com.spsm.decon.rightway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.User;
import com.spsm.decon.rightway.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public List<User> getAllUsers() {
		
		List<User> findAllUser = userRepo.findAll();
		List<User> AllUsers = findAllUser.stream()
				   .map(user -> new User(user.getUserId()
						   , user.getFirstName()
						   , user.getLastName()
						   , user.getEmail()))
				   .collect(Collectors.toList());
				
		return AllUsers;
	}

	public User createStudent(User user) {
		setAddressToOneExactUser(user);
		return userRepo.save(user);
	}
	
	public void setAddressToOneExactUser(User user) {
		Address address = new Address();
		address.setAddressLine(user.getAddress().getAddressLine());
		address.setZipCode(user.getAddress().getZipCode());
		address.setCity(user.getAddress().getCity());
		address.setState(user.getAddress().getState());
		address.setUserId(user.getUserId());
		address.setUser(user);
		user.setAddress(address);
	}
}
