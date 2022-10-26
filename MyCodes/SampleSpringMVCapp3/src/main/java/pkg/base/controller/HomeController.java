package pkg.base.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pkg.base.model.Admin;
import pkg.base.model.User;

@Controller
@RequestMapping("/app")
public class HomeController {

	private Logger logger;
	private String baseURL = "http://localhost:9090/SampleSpringMVCapp3/app/";

	@RequestMapping(path = "/user/login", method = RequestMethod.GET)
	public ModelAndView userLogin(@ModelAttribute("login") User user, ModelAndView modelAndView) {
		user.setUsername("User");
		user.setPassword("Pass@user");
		modelAndView.addObject("moods", Arrays.asList("Happy", "Normal", "Sad"));
		modelAndView.addObject("title", "User Login");
		modelAndView.addObject("heading", "User Login");
		modelAndView.addObject("headingColor", "orange");
		modelAndView.addObject("action", baseURL + "user/post");
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(path = "/admin/login", method = RequestMethod.GET)
	public ModelAndView adminLogin(@ModelAttribute("login") Admin admin, ModelAndView modelAndView) {
		admin.setUsername("Admin");
		admin.setPassword("Pass@admin");
		modelAndView.addObject("moods", Arrays.asList("Happy", "Normal", "Sad"));
		modelAndView.addObject("title", "Admin Login");
		modelAndView.addObject("heading", "Admin Login");
		modelAndView.addObject("headingColor", "red");
		modelAndView.addObject("action", baseURL + "admin/post");
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(path = "/user/post", method = RequestMethod.POST, params = { "username", "password" })
	@ResponseBody
	public String userLoginStatus(@Valid @ModelAttribute("login") User user, Errors errors) {
		String text1 = "<center><strong style=\"font-size: 50px; color: green;\">";
		String text2 = "</strong></center>";
		if (errors.hasErrors()) {
			return text1 + "Error: " + errors.toString() + text2;
		}
		if (!user.getUsername().equals("User")) {
			return text1 + "User login denied! :(<br><br>[Cause: User does not exist.]" + text2;
		}
		if (!user.getPassword().equals("Pass@user")) {
			return text1 + "User login denied! :(<br><br>[Cause: Wrong password.]" + text2;
		}
		return text1 + "User login successful. :)<br><br>" + user.toString() + text2;
	}

	@RequestMapping(path = "/admin/post", method = RequestMethod.POST, params = { "username", "password" })
	@ResponseBody
	public String adminLoginStatus(@Valid @ModelAttribute("login") Admin admin, Errors errors) {
		String text1 = "<center><strong style=\"font-size: 50px; color: purple;\">";
		String text2 = "</strong></center>";
		if (errors.hasErrors()) {
			return text1 + "Error: " + errors.toString() + text2;
		}
		if (!admin.getUsername().equals("Admin")) {
			return text1 + "Admin login denied! :(<br><br>[Cause: Admin does not exist.]" + text2;
		}
		if (!admin.getPassword().equals("Pass@admin")) {
			return text1 + "Admin login denied! :(<br><br>[Cause: Wrong password.]" + text2;
		}
		return text1 + "Admin login successful. :)<br><br>" + admin.toString() + text2;
	}

	public HomeController() {
		super();
		// Configuring logger.
		logger = Logger.getLogger(HomeController.class);
		BasicConfigurator.configure();
		logger.setLevel(Level.ALL);
		// Tracing HomeController instance.
		logger.trace("\n\n\n\nInstance of class \"HomeController\" has been created successfully. :)\n\n\n");
	}

}
