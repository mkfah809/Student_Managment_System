package com.spsm.decon.rightway.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spsm.decon.rightway.dto.Hymn;
import com.spsm.decon.rightway.service.HymnService;

@Controller
public class HymnController {
	@Autowired
	HymnService hymnService;

	@GetMapping("/register-new-hymn")
	private String getRegisterHymn(ModelMap model) {
		model.put("hymn", new Hymn());
		return "hymn";
	}

	@PostMapping("/register-new-hymn")
	private String postRegister(Hymn hymn) {
		Hymn savedHymn = hymnService.save(hymn);
		return "welcome";
	}
}
