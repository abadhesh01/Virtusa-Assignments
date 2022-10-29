package pkg.base.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pkg.base.model.Login;
import pkg.base.model.Signup;
import pkg.base.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService homeService;
	private String customerBaseURL = "http://localhost:9090/PremiumEngineCalculator/home/customer/";
	private String adminBaseURL = "http://localhost:9090/PremiumEngineCalculator/home/admin/";

	@GetMapping(path = "/sample")
	@ResponseBody
	public String sampleRequest() {
		return "<strong>Execution successful! :)<br>[" + homeService.sampleServiceRequest() + "]</strong>";
	}

	@GetMapping(path = "/customer/login")
	public String customerLogin(@ModelAttribute("login") Login login, Model model) {
		customerLoginModel(model);
		return "login";
	}

	@GetMapping(path = "/admin/login")
	public String adminLogin(@ModelAttribute("login") Login login, Model model) {
		adminLoginModel(model);
		return "login";
	}

	@GetMapping(path = "/customer/logout")
	public String customerLogout(@ModelAttribute("login") Login login, Model model, HttpSession session) {
		customerLoginModel(model);
		model.addAttribute("message", "You have been successfully logged out.");
		return "login";
	}

	@GetMapping(path = "/admin/logout")
	public String adminLogout(@ModelAttribute("login") Login login, Model model, HttpSession session) {
		adminLoginModel(model);
		model.addAttribute("message", "You have been successfully logged out.");
		return "login";
	}

	@GetMapping(path = "/customer/signup/page")
	public String customerSignupPage(@ModelAttribute("signup") Signup signup, Model model) {
		customerSignupModel(model);
		return "signup";
	}

	@GetMapping(path = "/admin/signup/page")
	public String adminSignupPage(@ModelAttribute("signup") Signup signup, Model model) {
		adminSignupModel(model);
		return "signup";
	}

	@PostMapping(path = "/customer/signup")
	public String customerSignup(@ModelAttribute("signup") @Valid Signup signup, BindingResult errors,
			@ModelAttribute("login") Login login, Model model, HttpSession session) {

		if (errors.hasErrors()) {
			customerSignupModel(model);
			return "signup";
		}

		if (!signup.getNewPassword().equals(signup.getConfirmPassword())) {
			customerSignupModel(model);
			model.addAttribute("errorMessage", "The new password and confirm password field did not match!");
			return "signup";
		}

		String result = homeService.validateCustomerSignup(signup);
		if (!result.equals(signup.getUsername())) {
			customerSignupModel(model);
			model.addAttribute("errorMessage", result);
			return "signup";
		}

		customerLoginModel(model);
		model.addAttribute("message", "Your account has been created successfully.");
		return "login";
	}

	@PostMapping(path = "/admin/signup")
	public String adminSignup(@ModelAttribute("signup") @Valid Signup signup, BindingResult errors,
			@ModelAttribute("login") Login login, Model model, HttpSession session) {

		if (errors.hasErrors()) {
			adminSignupModel(model);
			return "signup";
		}

		if (!signup.getNewPassword().equals(signup.getConfirmPassword())) {
			adminSignupModel(model);
			model.addAttribute("errorMessage", "The new password and confirm password field did not match!");
			return "signup";
		}

		String result = homeService.validateAdminSignup(signup);
		if (!result.equals(signup.getUsername())) {
			adminSignupModel(model);
			model.addAttribute("errorMessage", result);
			return "signup";
		}

		adminLoginModel(model);
		model.addAttribute("message", "Your account has been created successfully.");
		return "login";
	}

	@PostMapping(path = "/customer/dashboard")
	public String customerDashboard(@ModelAttribute("login") @Valid Login login, BindingResult errors, Model model,
			HttpSession session) {

		if (errors.hasErrors()) {
			customerLoginModel(model);
			return "login";
		}

		String result = homeService.validateCustomerLogin(login);

		if (!result.equals(login.getUsername())) {
			customerLoginModel(model);
			model.addAttribute("errorMessage", result);
			return "login";
		}

		customerDashboardModel(model);
		return "dashboard";
	}

	@PostMapping(path = "/admin/dashboard")
	public String adminDashboard(@ModelAttribute("login") @Valid Login login, BindingResult errors, Model model,
			HttpSession session) {

		if (errors.hasErrors()) {
			adminLoginModel(model);
			return "login";
		}

		String result = homeService.validateAdminLogin(login);

		if (!result.equals(login.getUsername())) {
			adminLoginModel(model);
			model.addAttribute("errorMessage", result);
			return "login";
		}

		adminDashboardModel(model);
		return "dashboard";
	}

	public void customerLoginModel(Model model) {
		loginModelConfiguration(model, "Customer", "#05AB23", customerBaseURL + "/dashboard",
				customerBaseURL + "/signup/page", "post");
	}

	public void adminLoginModel(Model model) {
		loginModelConfiguration(model, "Admin", "#3429EA", adminBaseURL + "/dashboard", adminBaseURL + "/signup/page",
				"post");
	}

	public void customerSignupModel(Model model) {
		signupModelConfiguration(model, "Customer", "#05AB23", customerBaseURL + "/signup", customerBaseURL + "/login",
				"post");
	}

	public void adminSignupModel(Model model) {
		signupModelConfiguration(model, "Admin", "#3429EA", adminBaseURL + "/signup", adminBaseURL + "/login", "post");
	}

	public void customerDashboardModel(Model model) {
		dashboardModelConfiguration(model, "Customer", "#05AB23", customerBaseURL + "logout");
	}

	public void adminDashboardModel(Model model) {
		dashboardModelConfiguration(model, "Admin", "#3429EA", adminBaseURL + "logout");
	}

	public void loginModelConfiguration(Model model, String userType, String headingColor, String onLogin,
			String onSignup, String requestType) {
		model.addAttribute("userType", userType);
		model.addAttribute("headingColor", headingColor);
		model.addAttribute("onLogin", onLogin);
		model.addAttribute("onSignup", onSignup);
		model.addAttribute("requestType", requestType);
		model.addAttribute("message", "");
		model.addAttribute("errorMessage", "");
	}

	public void signupModelConfiguration(Model model, String userType, String headingColor, String onSignup,
			String onLogin, String requestType) {
		model.addAttribute("userType", userType);
		model.addAttribute("headingColor", headingColor);
		model.addAttribute("onSignup", onSignup);
		model.addAttribute("onLogin", onLogin);
		model.addAttribute("requestType", requestType);
		model.addAttribute("message", "");
		model.addAttribute("errorMessage", "");
	}

	public void dashboardModelConfiguration(Model model, String userType, String headingColor, String onLogout) {
		model.addAttribute("userType", userType);
		model.addAttribute("headingColor", headingColor);
		model.addAttribute("userName", "Default " + userType);
		model.addAttribute("onLogout", onLogout);
	}

	public HomeController() {
		super();
	}
}
