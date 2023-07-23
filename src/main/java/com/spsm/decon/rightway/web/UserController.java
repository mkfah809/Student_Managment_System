package com.spsm.decon.rightway.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spsm.decon.rightway.dto.User;
import com.spsm.decon.rightway.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	private ResponseEntity<List<User>> getAllTodoItems() {
		List<User> findAllUsers = userService.getAllUsers();
		return ResponseEntity.ok(findAllUsers);
	}
}
