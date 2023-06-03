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

	@GetMapping("/user/self/registeration")
	public String getRegisterForUserRole(ModelMap model) {
		model.put("deacon", new Deacon());
		return "register";
	}

	@PostMapping("/validate/username")
	public ResponseEntity<Boolean> validateUsername(@RequestBody Deacon deacon) throws Exception {
		Deacon foundDeacon = deaconService.findByUsername(deacon.getUsername());
		if (foundDeacon == null) {
			return ResponseEntity.ok(deacon == null);
		}
		return ResponseEntity.ok(deacon != null);
	}

	@PostMapping("/validate/email")
	public ResponseEntity<Boolean> validateEmail(@RequestBody Deacon deacon) throws Exception {
		if (deaconService.findByEmail(deacon.getEmail()) == null) {
			return ResponseEntity.ok(deacon == null);
		}
		return ResponseEntity.ok(deacon != null);
	}

	@PostMapping("/user/self/registeration")
	public ResponseEntity<String> userRegisterationForUserRole(@RequestBody Deacon deacon) throws Exception {

		deaconService.setAuthorityToOneExactUser(deacon, "ROLE_USER");
		Deacon savedDeacon = deaconService.save(deacon);
		return ResponseEntity.ok("redirect:/login");
	}

	@GetMapping("/register/admin/user")
	public String getRegisterForAdminRole(ModelMap model) {
		model.put("deacon", new Deacon());
		return "register";
	}

	@PostMapping("/register/admin/user")
	public ResponseEntity<String> userRegisterForAdminRole(@RequestBody Deacon deacon) throws Exception {
		deaconService.setAuthorityToOneExactUser(deacon, "ROLE_ADMIN");
		Deacon savedDeacon = deaconService.save(deacon);
		return ResponseEntity.ok("redirect:/dashboard");
	}
}
