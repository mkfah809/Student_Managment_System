package com.spsm.decon.rightway.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spsm.decon.rightway.dto.Deacon;
import com.spsm.decon.rightway.service.DeaconServiceImpl;

@Controller
public class UserValidationController {
	@Autowired
	DeaconServiceImpl deaconService;

	@GetMapping("/new/register")
	public String getRegister(ModelMap model) {
		model.put("deacon", new Deacon());
		return "register";
	}

	@PostMapping("/new/register/username")
  	public ResponseEntity<Boolean> isUserExist(@RequestBody Deacon deacon) throws Exception {
		
		if (deaconService.findByUsername(deacon.getUsername()) == null) {
			Deacon savedUser = deaconService.save(deacon);
			return ResponseEntity.ok(deacon == null);
		}
		return ResponseEntity.ok(deacon != null);
	}
	
	
}
