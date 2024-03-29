package com.spsm.decon.rightway.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spsm.decon.rightway.dto.Address;
import com.spsm.decon.rightway.dto.Deacon;
import com.spsm.decon.rightway.service.DeaconServiceImpl;

@Controller
public class UserController {
	@Autowired
	DeaconServiceImpl deconService;

	@GetMapping("/register-new-decon")
	private String getRegister(ModelMap model) {
		model.put("decon", new Deacon());
		return "register";
	}

	@PostMapping("/register-new-decon")
	private String postRegister(Deacon decon, Address address) {
		Deacon savedDecon = deconService.save(decon);
		return "dashboard";
	}

	@GetMapping("/get-all-decons")
	private String getAllDecons(ModelMap model) {
		List<Deacon> foundAllDecons = deconService.findAll();
		if (!foundAllDecons.isEmpty()) {
			model.put("decons", foundAllDecons);
		}

		return "decons";
	}

	@GetMapping("/get-decon-{deconId}")
	private String getExactDeconFromListOfDeacons(@PathVariable Long deconId, ModelMap model) {
		Deacon decon = deconService.findById(deconId);
		if (decon.getDeconId() != null) {
			model.put("decon", decon);
		}
		return "profile";
	}

	@PostMapping("/update-decon-{deconId}")
	private String updateDeconFromList(Deacon decon) {
		if (decon.getDeconId() != null) {
			Deacon updatedDecon = deconService.save(decon);
		}
		return "redirect:/get-all-decons";
	}

	@GetMapping("/profile/{deconId}")
	private String getExactLoggedInDecon(@PathVariable Long deconId, ModelMap model) {
		model.put("decon", deconService.findById(deconId));
		return "profile";
	}
	
	@PostMapping("/profile/{deconId}/update")
	private String updateExactLoggedInDecon(Deacon decon) {
		if (decon.getDeconId() != null) {
			Deacon updatedDecon = deconService.save(decon);
		}
		return "redirect:/dashboard";
	}

}
