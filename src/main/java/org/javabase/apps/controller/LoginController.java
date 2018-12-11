package org.javabase.apps.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.javabase.apps.entity.Role;
import org.javabase.apps.entity.User;
import org.javabase.apps.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserService userservice;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
	
	@RequestMapping(value = "/loginsuccess", method = RequestMethod.GET)
	public String loginSucess(Authentication authentication) {
		
	    String username = authentication.getName();
		User user = userservice.getUserByUsername(username);
		 
		log.debug("user {}", user.getRole().getRoleName());
		session.setAttribute("user", user);
		session.setAttribute("userInfo", user.getUserInformation());
		return "redirect:/dashboard";
	}
	
	@ResponseBody
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public Map<String, Object> registration(@RequestBody User user) {
		Map<String, Object> response= new HashMap<String, Object>();
        Role role = new Role();
        role.setRoleId(1);
		user.setCreateDate(new Date());
		user.setActive(true);
		user.setAccountNonExpired(true);
		user.setCredintialNonExpired(true);
		user.setNonLocked(true);
		
		Boolean save = userservice.addUser(user);
		if (save) {
			response.put("suceess", true);
	        response.put("message", "Registration Sucess");
			return response;
		}else {
			response.put("error", true);
	        response.put("message", "Registration Failed");
			return response;
		}
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
            session.invalidate();
            response.reset();
        }
        session.invalidate();
        response.reset();
        return "redirect:/home";
    }

}
