package pkg.base.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pkg.base.entity.InsurancePolicy;
import pkg.base.model.InsurancePolicyModel;
import pkg.base.model.Login;
import pkg.base.model.Signup;
import pkg.base.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService homeService;
	private Logger logger;
	private String customerBaseURL = "http://localhost:9090/PremiumEngineCalculator/home/customer/";
	private String adminBaseURL = "http://localhost:9090/PremiumEngineCalculator/home/admin/";

	@GetMapping(path = "/sample")
	@ResponseBody
	public String sampleRequest() {
		return "<strong>Execution successful! :)<br>[" + homeService.sampleServiceRequest() + "]</strong>";
	}

	// Customer Login.
	@GetMapping(path = "/customer/login")
	public String customerLogin(@ModelAttribute("login") Login login,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {
		if (session.getAttribute("loggedCustomer") != null) {
			customerDashboardModel(model, ((Login) session.getAttribute("loggedCustomer")).getUsername());
			session.setAttribute("dashboardRequest", "customer");
			return "dashboard";
		}
		customerLoginModel(model);
		return "login";
	}

	// Admin Login.
	@GetMapping(path = "/admin/login")
	public String adminLogin(@ModelAttribute("login") Login login,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {
		if (session.getAttribute("loggedAdmin") != null) {
			adminDashboardModel(model, ((Login) session.getAttribute("loggedAdmin")).getUsername());
			session.setAttribute("dashboardRequest", "admin");
			return "dashboard";
		}
		adminLoginModel(model);
		return "login";
	}

	// Customer Logout.
	@PostMapping(path = "/customer/logout")
	public String customerLogout(@ModelAttribute("login") Login login,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {
		logger.trace("\n\n\n\nSession Id[" + new Throwable().getStackTrace()[0].getMethodName() + "]: "
				+ session.getId() + "\nLogged Admin: " + session.getAttribute("loggedAdmin") + "\n\n\n");

		if (((Login) session.getAttribute("loggedAdmin")) == null) {
			session.invalidate();
		} else {
			session.removeAttribute("loggedCustomer");
		}

		customerLoginModel(model);
		model.addAttribute("message", "You have been successfully logged out.");
		return "login";
	}

	// Admin Logout.
	@PostMapping(path = "/admin/logout")
	public String adminLogout(@ModelAttribute("login") Login login,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {
		logger.trace("\n\n\n\nSession Id[" + new Throwable().getStackTrace()[0].getMethodName() + "]: "
				+ session.getId() + "\nLogged Customer: " + session.getAttribute("loggedCustomer") + "\n\n\n");

		if (((Login) session.getAttribute("loggedCustomer")) == null) {
			session.invalidate();
		} else {
			session.removeAttribute("loggedAdmin");
		}

		adminLoginModel(model);
		model.addAttribute("message", "You have been successfully logged out.");
		return "login";
	}

	// Customer sign up page.
	@GetMapping(path = "/customer/signup/page")
	public String customerSignupPage(@ModelAttribute("signup") Signup signup, Model model) {
		customerSignupModel(model);
		return "signup";
	}

	// Admin sign up page.
	@GetMapping(path = "/admin/signup/page")
	public String adminSignupPage(@ModelAttribute("signup") Signup signup, Model model) {
		adminSignupModel(model);
		return "signup";
	}

	// Customer Sign up.
	@PostMapping(path = "/customer/signup")
	public String customerSignup(@ModelAttribute("signup") @Valid Signup signup, BindingResult errors,
			@ModelAttribute("login") Login login, Model model, HttpSession session) {
		logger.trace("\n\n\n\nSession Id[" + new Throwable().getStackTrace()[0].getMethodName() + "]: "
				+ session.getId() + "\n\n\n");

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

	// Admin sign up.
	@PostMapping(path = "/admin/signup")
	public String adminSignup(@ModelAttribute("signup") @Valid Signup signup, BindingResult errors,
			@ModelAttribute("login") Login login, Model model, HttpSession session) {
		logger.trace("\n\n\n\nSession Id[" + new Throwable().getStackTrace()[0].getMethodName() + "]: "
				+ session.getId() + "\n\n\n");

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
	public String customerDashboard(@ModelAttribute("login") @Valid Login login, BindingResult errors,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {
		logger.trace("\n\n\n\nSession Id[" + new Throwable().getStackTrace()[0].getMethodName() + "]: "
				+ session.getId() + "\n\n\n");

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

		if (((Login) (session.getAttribute("loggedCustomer"))) == null) {
			login.setPassword("Not Available.");
			login.setLoginTime(new Date().toString());
			session.setAttribute("loggedCustomer", login);
		}

		customerDashboardModel(model, ((Login) (session.getAttribute("loggedCustomer"))).getUsername());
		session.setAttribute("dashboardRequest", "customer");
		return "dashboard";
	}

	@PostMapping(path = "/admin/dashboard")
	public String adminDashboard(@ModelAttribute("login") @Valid Login login, BindingResult errors,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {
		logger.trace("\n\n\n\nSession Id[" + new Throwable().getStackTrace()[0].getMethodName() + "]: "
				+ session.getId() + "\n\n\n");

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

		if (((Login) (session.getAttribute("loggedAdmin"))) == null) {
			login.setPassword("Not Available.");
			login.setLoginTime(new Date().toString());
			session.setAttribute("loggedAdmin", login);
		}

		adminDashboardModel(model, ((Login) (session.getAttribute("loggedAdmin"))).getUsername());
		session.setAttribute("dashboardRequest", "admin");
		return "dashboard";
	}

	// Adding a new insurance policy.
	@GetMapping("/admin/addNewPolicy")
	public String addNewPolicy(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute("login") Login login, Model model, HttpSession session) {
		if (session.getAttribute("loggedAdmin") == null) {
			adminLoginModel(model);
			return "login";
		}
		adminDashboardModel(model, ((Login) (session.getAttribute("loggedAdmin"))).getUsername());
		session.setAttribute("dashboardRequest", "admin");
		session.setAttribute("adminDashboardView", "addPolicy");
		model.addAttribute("message", "");
		model.addAttribute("messageColor", "#0B8DDD");
		return "dashboard";
	}

	// Adding a new insurance policy.
	@PostMapping("/admin/savePolicy")
	public String savePolicy(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute("login") Login login, Model model, HttpSession session) {
		if (session.getAttribute("loggedAdmin") == null) {
			adminLoginModel(model);
			return "login";
		}
		adminDashboardModel(model, ((Login) (session.getAttribute("loggedAdmin"))).getUsername());
		session.setAttribute("dashboardRequest", "admin");
		session.setAttribute("adminDashboardView", "addPolicy");
		String result = homeService.addNewPolicy(InsurancePolicy.builder()
				.policyName(insurancePolicyModel.getPolicyName()).policyType(insurancePolicyModel.getPolicyType())
				.periodOfCoverage(insurancePolicyModel.getPeriodOfCoverage())
				.premiumAmount(insurancePolicyModel.getPremiumAmount()).price(insurancePolicyModel.getPrice()).build());
		if (result.equals("Operation Successful")) {
			model.addAttribute("message", "Policy has been added successfully.");
			model.addAttribute("messageColor", "#0B8DDD");
			return "dashboard";
		}
		model.addAttribute("message", result);
		model.addAttribute("messageColor", "red");
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

	public void customerDashboardModel(Model model, String username) {
		dashboardModelConfiguration(model, "Customer", "#05AB23", customerBaseURL + "logout", username);
	}

	public void adminDashboardModel(Model model, String username) {
		dashboardModelConfiguration(model, "Admin", "#3429EA", adminBaseURL + "logout", username);
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

	public void dashboardModelConfiguration(Model model, String userType, String headingColor, String onLogout,
			String username) {
		model.addAttribute("userType", userType);
		model.addAttribute("headingColor", headingColor);
		model.addAttribute("userName", username);
		model.addAttribute("onLogout", onLogout);
	}

	public HomeController() {
		super();
		logger = Logger.getLogger(HomeController.class);
		BasicConfigurator.configure();
		logger.setLevel(Level.ALL);
		logger.info("\n\n\n\n" + getClass().getName() + " has been created successfully.\n\n\n");
	}
}
