package com.spsm.decon.rightway.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spsm.decon.rightway.dto.Decon;
import com.spsm.decon.rightway.service.DeconServiceImpl;

@Controller
public class LoginController {
	@Autowired
	DeconServiceImpl deconService;
	
	@RequestMapping("/")
	public String getRedirectWelcomePage() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login(ModelMap model) {
		List<Decon> decons = deconService.findAll();
		if (!decons.isEmpty()) {
			for (Decon decon : decons) {
				model.put("decon", decon);
			}
		}
		return "login";
	}
	
	@GetMapping("/super-admin-registeration-for-first-time")
	public String getSuperAdminRegisteration(ModelMap model) {
		model.put("employee", new Decon());
		return "register";
	}

	@PostMapping("/super-admin-registeration-for-first-time")
	public String postFirstEmployeeAsAdmin(Employee emp, Authority auth) {
		emp.setTitle("Manager");
		Employee savedEmp = adminService.createOrUpdateEmployee(emp);
		Authority setAuthorityToUser = authService.setAuthorityToUser(savedEmp, auth);
		System.out.println("savedEmp for first time: " + savedEmp.getId());
		System.out.println("savedAuthority  for first time: " + setAuthorityToUser.getAuthority());

		return "redirect:/login";
	}
}
