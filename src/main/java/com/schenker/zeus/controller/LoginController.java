package com.schenker.zeus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schenker.zeus.model.User;
import com.schenker.zeus.service.UserValidation;

/*
 * 
 */
@Controller
@RequestMapping("/login.do")
public class LoginController {

	// Standard JSR250 Injection
	@Resource(name = "userVal")
	private UserValidation userValidation;

	@RequestMapping(params = "weblogin")
	public String login(@ModelAttribute("user") User user,
			HttpServletRequest req, HttpServletResponse response) {
		System.out.println("用户信息: " + user);
		// 传递对象于下一页面
		// TODO check database to get user, for now just use 'admin' 'admin' for
		// mock
		if (user.getUserId() != null && user.getUserPassword() != null) {
			if (userValidation.userValidation(user.getUserId(),
					user.getUserPassword())) {
				req.getSession().setAttribute("userInSession", user);
				return "grid-demo";
			}
		}
		return "login";
	}

	// Get Login user session
	public static User getLoginUser(HttpServletRequest req) {
		return (User) req.getSession().getAttribute("userInSession");
	}
}
