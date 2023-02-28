package com.spsm.decon.rightway.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Decon;
import com.spsm.decon.rightway.service.DeconService;

@Controller
public class DeconController {
	@Autowired
	DeconService deconService;

	@GetMapping("/welcome")
	private String getWelcome() {
		return "welcome";
	}

	@GetMapping("/register-new-decon")
	private String getRegister(ModelMap model) {
		model.put("decon", new Decon());
		return "register";
	}

	@PostMapping("/register-new-decon")
	private String postRegister(Decon decon, Address address) {
		Decon savedDecon = deconService.save(decon);
		return "welcome";
	}
}
