package com.spsm.decon.rightway.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spsm.decon.rightway.dto.Deacon;
import com.spsm.decon.rightway.service.DeaconServiceImpl;

@Controller
public class LoginController {
	@Autowired
	DeaconServiceImpl deconService;

	@RequestMapping("/")
	private String getRedirectWelcomePage() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	private String login(ModelMap model) {
		List<Deacon> decons = deconService.findAll();
		if (!decons.isEmpty()) {
			for (Deacon decon : decons) {
				model.put("decon", decon);
			}
		}
		return "login";
	}

	@GetMapping("/forget-password-generator")
	private String getForgetPassword(ModelMap model) {
		model.put("decon", new Deacon());
		return "password";
	}

	@PostMapping("/forget-password-generator")
	private String postPassword(Deacon decon) {
		Deacon foundDecon = deconService.findByUsername(decon.getUsername());
		deconService.sendMailForPasswordGenerator(foundDecon);
		deconService.save(foundDecon);
		return "redirect:/login";
	}

}
