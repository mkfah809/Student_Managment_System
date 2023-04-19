package com.spsm.decon.rightway.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.spsm.decon.rightway.dto.Deacon;
import com.spsm.decon.rightway.service.DeaconServiceImpl;

@Controller
public class DashboardController {

	@Autowired
	DeaconServiceImpl DeconService;

	@GetMapping("/dashboard")
	public String getDashboard(@AuthenticationPrincipal Deacon decon, ModelMap model) throws Exception {
  		Deacon foundDecon = DeconService.findByUsername(decon.getUsername());
		try {
			model.put("decon", foundDecon);
			model.put("access", decon.getAuthorities().iterator().next());
		} catch (Exception e) {
			throw new Exception("You are not an admin to access this page");
		}
		return "dashboard";
	}
}
