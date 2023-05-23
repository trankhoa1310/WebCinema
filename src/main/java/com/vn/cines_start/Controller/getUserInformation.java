package com.vn.cines_start.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.vn.cines_start.Model.User;
import com.vn.cines_start.utils.SecurityUtils;

@Controller
public class getUserInformation {
	@GetMapping("/getUserInformation")
	public User getUserInformation() {
	    User result = new User();
	    result.setEmail(SecurityUtils.getPrincipal().getUsername());
	    result.setId(SecurityUtils.getPrincipal().getId());
	    result.setFirstName(SecurityUtils.getPrincipal().getFullName());
	    return result;
	}
}
