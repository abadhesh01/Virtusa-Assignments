package pkg.base.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pkg.base.model.Admin;
import pkg.base.model.User;
import pkg.base.service.HomeService;

@Controller
@RequestMapping("/app")
public class HomeController {

	@Autowired
	private HomeService homeService;

	@RequestMapping(path = "/user/login", method = RequestMethod.GET)
	public String userLogin(@ModelAttribute("login") User user, Model model) {
		setUserLoginModel(model, "dashboard", "post");
		return "login";
	}

	@RequestMapping(path = "/admin/login", method = RequestMethod.GET)
	public String adminLogin(@ModelAttribute("login") Admin admin, Model model) {
		setAdminLoginModel(model, "dashboard", "post");
		return "login";
	}

	@RequestMapping(path = "/user/dashboard", method = RequestMethod.POST)
	public String userDashboard(@ModelAttribute("login") @Valid User user, BindingResult errors, Model model) {
		if (errors.hasErrors()) {
			setUserLoginModel(model, "dashboard", "post");
			return "login";
		}

		String result = homeService.findUser(user.getUsername(), user.getPassword());
		if (!result.equals(user.getUsername())) {
			setUserLoginModel(model, "dashboard", "post");
			model.addAttribute("message", result);
			return "login";
		}

		setDashboard(model, "User", "#08A8F8");
		return "dashboard";
	}

	@RequestMapping(path = "/admin/dashboard", method = RequestMethod.POST)
	public String adminDashboard(@ModelAttribute("login") @Valid Admin admin, BindingResult errors, Model model) {
		if (errors.hasErrors()) {
			setAdminLoginModel(model, "dashboard", "post");
			return "login";
		}

		String result = homeService.findAdmin(admin.getUsername(), admin.getPassword());
		if (!result.equals(admin.getUsername())) {
			setAdminLoginModel(model, "dashboard", "post");
			model.addAttribute("message", result);
			return "login";
		}

		setDashboard(model, "Admin", "#1DBC1B");
		return "dashboard";
	}

	public void setUserLoginModel(Model model, String pathName, String requestType) {
		String baseURL = "http://localhost:9090/SampleSpringLoginApp/app/user/";
		model.addAttribute("title", "User Login");
		model.addAttribute("heading", "User Login");
		model.addAttribute("headingColor", "#08A8F8");
		model.addAttribute("action", baseURL + pathName);
		model.addAttribute("requestType", requestType);
	}

	public void setAdminLoginModel(Model model, String pathName, String requestType) {
		String baseURL = "http://localhost:9090/SampleSpringLoginApp/app/admin/";
		model.addAttribute("title", "Admin Login");
		model.addAttribute("heading", "Admin Login");
		model.addAttribute("headingColor", "#1DBC1B");
		model.addAttribute("action", baseURL + pathName);
		model.addAttribute("requestType", requestType);
	}

	public void setDashboard(Model model, String userType, String color) {
		model.addAttribute("userType", userType);
		model.addAttribute("color", color);
	}
}
