package com.spsm.decon.rightway.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Decon;
import com.spsm.decon.rightway.service.DeconServiceImpl;

@Controller
public class DeconController {
	@Autowired
	DeconServiceImpl deconService;

	@GetMapping("/register-new-decon")
	private String getRegister(ModelMap model) {
		model.put("decon", new Decon());
		return "register";
	}

	@PostMapping("/register-new-decon")
	private String postRegister(Decon decon, Address address) {
		Decon savedDecon = deconService.save(decon);
		return "dashboard";
	}

	@GetMapping("/get-all-decons")
	private String getAllDecons(ModelMap model) {
		List<Decon> foundAllDecons = deconService.findAll();
		if (!foundAllDecons.isEmpty()) {
			model.put("decons", foundAllDecons);
		}

		return "decons";
	}

	@GetMapping("/get-decon-{deconId}")
	private String getExactDecon(@PathVariable Long deconId, ModelMap model) {
		Decon decon = deconService.findById(deconId);
		if (decon.getDeconId() != null) {
			model.put("decon", decon);
		}
		return "profile";
	}

	@PostMapping("/update-decon-{deconId}")
	private String updateExactDecon(Decon decon) {
		Decon updatedDecon = deconService.save(decon);
		return "redirect:/get-all-decons";
	}

}
