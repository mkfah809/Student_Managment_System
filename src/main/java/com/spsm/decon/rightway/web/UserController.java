package com.spsm.decon.rightway.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spsm.decon.rightway.dto.User;
import com.spsm.decon.rightway.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/view/students")
	private List<User> getAllTodoItems() {
		return userService.getAllUsers();
		
	}
	

	@PostMapping("/add/students")
	private User registerUser(@RequestBody User user) {
		return userService.createStudent(user);
	}
}
