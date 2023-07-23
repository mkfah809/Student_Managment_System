package com.spsm.decon.rightway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spsm.decon.rightway.dto.User;
import com.spsm.decon.rightway.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;

	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
}
