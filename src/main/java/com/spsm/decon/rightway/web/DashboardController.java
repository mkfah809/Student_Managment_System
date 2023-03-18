package com.spsm.decon.rightway.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.spsm.decon.rightway.dto.Decon;
import com.spsm.decon.rightway.service.DeconServiceImpl;

@Controller
public class DashboardController {

	@Autowired
	DeconServiceImpl DeconService;

	@GetMapping("/dashboard")
	public String getDashboard(@AuthenticationPrincipal Decon decon, ModelMap model) throws Exception {
		Decon foundDecon = DeconService.findByUsername(decon.getUsername());
		try {
			model.put("decon", foundDecon);
			model.put("access", decon.getAuthorities().iterator().next());


		} catch (Exception e) {
			model.put("error", new Exception("You don't have a role at this point"));
			throw new Exception("You are not an admin to access this page");
		}
		return "dashboard";
	}
}
