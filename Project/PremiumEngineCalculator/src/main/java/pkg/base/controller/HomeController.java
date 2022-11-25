package pkg.base.controller;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pkg.base.attributes.SessionAttribute;
import pkg.base.entity.Calculation;
import pkg.base.entity.InsurancePolicy;
import pkg.base.model.InsurancePolicyModel;
import pkg.base.model.Login;
import pkg.base.model.Signup;
import pkg.base.service.HomeService;
import pkg.base.text.Text;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService homeService;
	private Logger logger;

	// Constants
	protected static final String CUSTOMER_BASE_URL = "/PremiumEngineCalculator/home/customer/";
	protected static final String ADMIN_BASE_URL = "/PremiumEngineCalculator/home/admin/";
	protected static final String LOGGED_CUSTOMER = "loggedCustomer";
	protected static final String LOGGED_ADMIN = "loggedAdmin";

	// Customer Login.
	@GetMapping(path = "/customer/login")
	public String customerLogin(@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) != null) {
			customerDashboardModel(model, ((Login) session.getAttribute(LOGGED_CUSTOMER)).getUsername());
			session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.CUSTOMER_DASHBOARD);
			session.setAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
			session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
			return SessionAttribute.DASHBOARD_PAGE;
		}

		customerLoginModel(model);
		return SessionAttribute.LOGIN_PAGE;
	}

	// Admin Login.
	@GetMapping(path = "/admin/login")
	public String adminLogin(@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) != null) {
			adminDashboardModel(model, ((Login) session.getAttribute(LOGGED_ADMIN)).getUsername());
			session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
			session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
			session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
			return SessionAttribute.DASHBOARD_PAGE;
		}

		adminLoginModel(model);
		return SessionAttribute.LOGIN_PAGE;
	}

	// Customer Logout.
	@PostMapping(path = "/customer/logout")
	public String customerLogout(@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ "\nLogged Admin: " + session.getAttribute(LOGGED_ADMIN) + Text.SESSION_CREATION_TIME
				+ session.getCreationTime() + Text.EXTRA_NEW_LINE);

		customerLoginModel(model);
		model.addAttribute(SessionAttribute.MESSAGE, "You have been successfully logged out.");

		if (((Login) session.getAttribute(LOGGED_ADMIN)) == null) {
			session.invalidate();
		} else {
			session.removeAttribute(LOGGED_CUSTOMER);
		}

		return SessionAttribute.LOGIN_PAGE;
	}

	// Admin Logout.
	@PostMapping(path = "/admin/logout")
	public String adminLogout(@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ "\nLogged Customer: " + session.getAttribute(LOGGED_CUSTOMER) + Text.SESSION_CREATION_TIME
				+ session.getCreationTime() + Text.EXTRA_NEW_LINE);

		adminLoginModel(model);
		model.addAttribute(SessionAttribute.MESSAGE, "You have been successfully logged out.");

		if (((Login) session.getAttribute(LOGGED_CUSTOMER)) == null) {
			session.invalidate();
		} else {
			session.removeAttribute(LOGGED_ADMIN);
		}

		return SessionAttribute.LOGIN_PAGE;
	}

	// Customer sign up page.
	@GetMapping(path = "/customer/signup/page")
	public String customerSignupPage(@ModelAttribute(SessionAttribute.SIGNUP_PAGE) Signup signup, Model model,
			HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		customerSignupModel(model, session);
		return SessionAttribute.SIGNUP_PAGE;
	}

	// Admin sign up page.
	@GetMapping(path = "/admin/signup/page")
	public String adminSignupPage(@ModelAttribute(SessionAttribute.SIGNUP_PAGE) Signup signup,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) == null) {
			adminLoginModel(model);
			model.addAttribute(SessionAttribute.MESSAGE, "You need to login first to register a new admin.");
			return SessionAttribute.LOGIN_PAGE;
		}

		adminSignupModel(model, session);
		return SessionAttribute.SIGNUP_PAGE;
	}

	// Customer Sign up.
	@PostMapping(path = "/customer/signup")
	public String customerSignup(@ModelAttribute(SessionAttribute.SIGNUP_PAGE) @Valid Signup signup,
			BindingResult errors, @ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model,
			HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (errors.hasErrors()) {
			customerSignupModel(model, session);
			return SessionAttribute.SIGNUP_PAGE;
		}

		if (!signup.getNewPassword().equals(signup.getConfirmPassword())) {
			customerSignupModel(model, session);
			model.addAttribute(SessionAttribute.ERROR_MESSAGE,
					"The new password and confirm password field did not match!");
			return SessionAttribute.SIGNUP_PAGE;
		}

		signup.setConfirmPassword(
				Base64.getEncoder().encodeToString(signup.getConfirmPassword().getBytes(StandardCharsets.UTF_8)));
		signup.setNewPassword(signup.getConfirmPassword());
		String result = homeService.validateCustomerSignup(signup);
		if (!result.equals(signup.getUsername())) {
			customerSignupModel(model, session);
			model.addAttribute(SessionAttribute.ERROR_MESSAGE, result);
			return SessionAttribute.SIGNUP_PAGE;
		}

		customerLoginModel(model);
		model.addAttribute(SessionAttribute.MESSAGE, "Your account has been created successfully.");
		return SessionAttribute.LOGIN_PAGE;
	}

	// Admin sign up.
	@PostMapping(path = "/admin/signup")
	public String adminSignup(@ModelAttribute(SessionAttribute.SIGNUP_PAGE) @Valid Signup signup, BindingResult errors,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login,
			@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) == null) {
			adminLoginModel(model);
			model.addAttribute(SessionAttribute.MESSAGE, "You need to login first to register a new admin.");
			return SessionAttribute.LOGIN_PAGE;
		}

		if (errors.hasErrors()) {
			adminSignupModel(model, session);
			return SessionAttribute.SIGNUP_PAGE;
		}

		if (!signup.getNewPassword().equals(signup.getConfirmPassword())) {
			adminSignupModel(model, session);
			model.addAttribute(SessionAttribute.ERROR_MESSAGE,
					"The new password and confirm password field did not match!");
			return SessionAttribute.SIGNUP_PAGE;
		}

		signup.setConfirmPassword(
				Base64.getEncoder().encodeToString(signup.getConfirmPassword().getBytes(StandardCharsets.UTF_8)));
		signup.setNewPassword(signup.getConfirmPassword());
		String result = homeService.validateAdminSignup(signup);
		if (!result.equals(signup.getUsername())) {
			adminSignupModel(model, session);
			model.addAttribute(SessionAttribute.ERROR_MESSAGE, result);
			return SessionAttribute.SIGNUP_PAGE;
		}

		adminDashboardModel(model, ((Login) (session.getAttribute(LOGGED_ADMIN))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
		session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, SessionAttribute.DEFAULT_MESSAGE_COLOR);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR,
				"Admin with username \"" + signup.getUsername() + "\" has been created successfully.");
		session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Display customer's dashboard.
	@PostMapping(path = "/customer/dashboard")
	public String customerDashboard(@ModelAttribute(SessionAttribute.LOGIN_PAGE) @Valid Login login,
			BindingResult errors, @ModelAttribute("policy") InsurancePolicyModel policyModel, Model model,
			HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (errors.hasErrors()) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		login.setPassword(Base64.getEncoder().encodeToString(login.getPassword().getBytes(StandardCharsets.UTF_8)));
		String result = homeService.validateCustomerLogin(login);

		if (!result.equals(login.getUsername())) {
			customerLoginModel(model);
			model.addAttribute(SessionAttribute.ERROR_MESSAGE, result);
			return SessionAttribute.LOGIN_PAGE;
		}

		if (((Login) (session.getAttribute(LOGGED_CUSTOMER))) == null) {
			login.setPassword("Not Available.");
			login.setLoginTime(new Date().toString());
			session.setAttribute(LOGGED_CUSTOMER, login);
		}

		customerDashboardModel(model, ((Login) (session.getAttribute(LOGGED_CUSTOMER))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.CUSTOMER_DASHBOARD);
		session.setAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
		if (session.getAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW) == null) {
			session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
		}
		session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Display admin's dashboard.
	@PostMapping(path = "/admin/dashboard")
	public String adminDashboard(@ModelAttribute(SessionAttribute.LOGIN_PAGE) @Valid Login login, BindingResult errors,
			@ModelAttribute("policy") InsurancePolicyModel policyModel, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (errors.hasErrors()) {
			adminLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		login.setPassword(Base64.getEncoder().encodeToString(login.getPassword().getBytes(StandardCharsets.UTF_8)));
		String result = homeService.validateAdminLogin(login);

		if (!result.equals(login.getUsername())) {
			adminLoginModel(model);
			model.addAttribute(SessionAttribute.ERROR_MESSAGE, result);
			return SessionAttribute.LOGIN_PAGE;
		}

		if (((Login) (session.getAttribute(LOGGED_ADMIN))) == null) {
			login.setPassword("Not Available.");
			login.setLoginTime(new Date().toString());
			session.setAttribute(LOGGED_ADMIN, login);
		}

		adminDashboardModel(model, ((Login) (session.getAttribute(LOGGED_ADMIN))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
		session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
		if (session.getAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW) == null) {
			session.setAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
		}
		session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Adding a new insurance policy.
	@GetMapping("/admin/addNewPolicy")
	public String addNewPolicy(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) == null) {
			adminLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}
		adminDashboardModel(model, ((Login) (session.getAttribute(LOGGED_ADMIN))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
		session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, "addPolicy");
		model.addAttribute(SessionAttribute.MESSAGE, SessionAttribute.EMPTY_VALUE);
		model.addAttribute(SessionAttribute.MESSAGE_COLOR, SessionAttribute.DEFAULT_MESSAGE_COLOR);
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Updating an existing policy.
	@GetMapping("/admin/updatePolicy/{policyId}")
	public String updatePolicy(@PathVariable("policyId") UUID policyId,
			@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) == null) {
			adminLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		adminDashboardModel(model, ((Login) (session.getAttribute(LOGGED_ADMIN))).getUsername());

		InsurancePolicy insurancePolicy = homeService.getInsurancePolicyById(policyId);
		if (insurancePolicy == null) {
			session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
			session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
			session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR,
					Text.POLICY_WITH_ID + policyId + Text.WAS_NOT_FOUND
							+ "<br>The policy might have been removed by anathor admin or does not exist.");
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, SessionAttribute.ERROR_MESSAGE_COLOR);
			return SessionAttribute.DASHBOARD_PAGE;
		}

		insurancePolicyModel.setPolicyId(insurancePolicy.getPolicyId());
		insurancePolicyModel.setPolicyName(insurancePolicy.getPolicyName());
		insurancePolicyModel.setPolicyType(insurancePolicy.getPolicyType());
		insurancePolicyModel.setPeriodOfCoverage(insurancePolicy.getPeriodOfCoverage());
		insurancePolicyModel.setPremiumAmount(insurancePolicy.getPremiumAmount());
		insurancePolicyModel.setPrice(insurancePolicy.getPrice());

		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
		session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, "updatePolicy");
		model.addAttribute(SessionAttribute.MESSAGE, SessionAttribute.EMPTY_VALUE);
		model.addAttribute(SessionAttribute.MESSAGE_COLOR, SessionAttribute.DEFAULT_MESSAGE_COLOR);
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Deleting an insurance policy.
	@GetMapping("/admin/deletePolicy/{policyId}")
	public String deletePolicy(@PathVariable("policyId") UUID policyId,
			@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) == null) {
			adminLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		String[] result = homeService.deleteInsurancePolicyById(policyId);
		adminDashboardModel(model, ((Login) (session.getAttribute(LOGGED_ADMIN))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
		session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, result[0]);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR, result[1]);
		session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Save new insurance policy.
	@PostMapping("/admin/savePolicy")
	public String savePolicy(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) == null) {
			adminLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		adminDashboardModel(model, ((Login) (session.getAttribute(LOGGED_ADMIN))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
		session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, "addPolicy");
		String result = homeService.addOrUpdatePolicy(InsurancePolicy.builder()
				.policyName(insurancePolicyModel.getPolicyName()).policyType(insurancePolicyModel.getPolicyType())
				.periodOfCoverage(insurancePolicyModel.getPeriodOfCoverage())
				.premiumAmount(insurancePolicyModel.getPremiumAmount()).price(insurancePolicyModel.getPrice()).build());
		if (result.equals("Operation Successful")) {
			model.addAttribute(SessionAttribute.MESSAGE, "Policy has been added successfully.");
			model.addAttribute(SessionAttribute.MESSAGE_COLOR, SessionAttribute.DEFAULT_MESSAGE_COLOR);
			return SessionAttribute.DASHBOARD_PAGE;
		}
		model.addAttribute(SessionAttribute.MESSAGE, result);
		model.addAttribute(SessionAttribute.MESSAGE_COLOR, SessionAttribute.ERROR_MESSAGE_COLOR);
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Save updated insurance policy.
	@PostMapping("/admin/updatePolicy/savePolicyUpdate")
	public String savePolicyUpdate(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) == null) {
			adminLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}
		adminDashboardModel(model, ((Login) (session.getAttribute(LOGGED_ADMIN))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);

		InsurancePolicy insurancePolicy = homeService.getInsurancePolicyById(insurancePolicyModel.getPolicyId());
		if (insurancePolicy == null) {
			session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
			session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
			session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR,
					Text.POLICY_WITH_ID + insurancePolicyModel.getPolicyId() + Text.WAS_NOT_FOUND
							+ "<br>The policy might have been removed by anathor admin or does not exist.");
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, SessionAttribute.ERROR_MESSAGE_COLOR);
			return SessionAttribute.DASHBOARD_PAGE;
		}

		insurancePolicy.setPolicyName(insurancePolicyModel.getPolicyName());
		insurancePolicy.setPolicyType(insurancePolicyModel.getPolicyType());
		insurancePolicy.setPeriodOfCoverage(insurancePolicyModel.getPeriodOfCoverage());
		insurancePolicy.setPremiumAmount(insurancePolicyModel.getPremiumAmount());
		insurancePolicy.setPrice(insurancePolicyModel.getPrice());

		String result = homeService.addOrUpdatePolicy(insurancePolicy);
		if (result.equals("Operation Successful")) {
			session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
			session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR, Text.POLICY_WITH_ID
					+ insurancePolicyModel.getPolicyId() + "\" " + "has been updated successfully.");
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, SessionAttribute.DEFAULT_MESSAGE_COLOR);
			return SessionAttribute.DASHBOARD_PAGE;
		}

		session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, "updatePolicy");
		model.addAttribute(SessionAttribute.MESSAGE, result);
		model.addAttribute(SessionAttribute.MESSAGE_COLOR, SessionAttribute.ERROR_MESSAGE_COLOR);
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Display/Refresh all policies in customer's dashboard.
	@GetMapping("/customer/showAllPolicies")
	public String showAllPoliciesToCustomer(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) == null) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}
		customerDashboardModel(model, ((Login) (session.getAttribute(LOGGED_CUSTOMER))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.CUSTOMER_DASHBOARD);
		session.setAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
		session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Display/Refresh all policies in admin's dashboard.
	@GetMapping("/admin/showAllPolicies")
	public String showAllPoliciesToAdmin(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_ADMIN) == null) {
			adminLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}
		adminDashboardModel(model, ((Login) (session.getAttribute(LOGGED_ADMIN))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.ADMIN_DASHBOARD);
		session.setAttribute(SessionAttribute.ADMIN_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
		session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Launch Premium Engine Calculator Engine.
	@GetMapping("/customer/calculatePremium/{policyId}")
	public String premiumCalculator(@PathVariable("policyId") UUID policyId,
			@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) == null) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		return calculatorModelConfig(policyId, insurancePolicyModel, model, session);
	}

	// Calculate premium cost for different scenario.
	@PostMapping(path = "/customer/calculatePremium/calculateOrSaveCalculation", params = { "calculate" })
	public String calculatePremium(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) == null) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		insurancePolicyModel.setFinalPrice(homeService.calculatePremiumCost(insurancePolicyModel));

		return calculatorModelConfig(insurancePolicyModel.getPolicyId(), insurancePolicyModel, model, session);
	}

	// Save calculation to customer's account.
	@PostMapping(path = "/customer/calculatePremium/calculateOrSaveCalculation", params = { "save" })
	public String saveCalculation(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) == null) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		String dashboard = calculatorModelConfig(insurancePolicyModel.getPolicyId(), insurancePolicyModel, model,
				session);
		if (session.getAttribute(SessionAttribute.CALCULATION_STATUS).equals(SessionAttribute.FALSE_VALUE)) {
			return dashboard;
		}

		if (insurancePolicyModel.getFinalPrice() != homeService.calculatePremiumCost(insurancePolicyModel)) {
			model.addAttribute(SessionAttribute.MESSAGE_COLOR, SessionAttribute.ERROR_MESSAGE_COLOR);
			model.addAttribute(SessionAttribute.MESSAGE,
					"Please calculate the final cost to save the data to your account!");
			return dashboard;
		}

		homeService.saveCalculation(((Login) session.getAttribute(LOGGED_CUSTOMER)).getUsername(),
				new Calculation(insurancePolicyModel));
		model.addAttribute(SessionAttribute.MESSAGE_COLOR, SessionAttribute.DEFAULT_MESSAGE_COLOR);
		model.addAttribute(SessionAttribute.MESSAGE, "Your calculaion has been saved successfully.");
		return dashboard;
	}

	// Show all calculations of logged in customer.
	@GetMapping("/customer/showAllCalculations")
	public String showAllCalculations(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) == null) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		return calculationListConfig(model, session);
	}

	// Print specific calculation.
	@GetMapping("/customer/printCalculation/{serialNumber}/{calculationId}")
	public String printCalculation(@PathVariable("serialNumber") int serialNumber,
			@PathVariable("calculationId") UUID calculationId,
			@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) == null) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		Calculation calculation = homeService.getCalculationById(calculationId);

		if (calculation == null) {
			String dashboard = calculationListConfig(model, session);
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR,
					Text.CALCULATION_WITH_ID + calculationId + Text.WAS_NOT_FOUND);
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, SessionAttribute.ERROR_MESSAGE_COLOR);
			return dashboard;
		}

		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.CUSTOMER_DASHBOARD);
		session.setAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW, "printCalculation");
		session.setAttribute("calculation", calculation);
		model.addAttribute("serialNumber", serialNumber);
		customerDashboardModel(model, ((Login) session.getAttribute(LOGGED_CUSTOMER)).getUsername());
		return SessionAttribute.DASHBOARD_PAGE;
	}

	// Delete a customer's calculation by it's id.
	@GetMapping("/customer/deleteCalculation/{calculationId}")
	public String deleteCalculation(@PathVariable("calculationId") UUID calculationId,
			@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) == null) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		boolean result = homeService.deleteCalculationById(calculationId);
		String dashboard = null;

		if (result) {
			dashboard = calculationListConfig(model, session);
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR,
					Text.CALCULATION_WITH_ID + calculationId + "\" has been removed from your account successfully.");
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, SessionAttribute.DEFAULT_MESSAGE_COLOR);
			return dashboard;
		}

		dashboard = calculationListConfig(model, session);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR,
				Text.CALCULATION_WITH_ID + calculationId + Text.WAS_NOT_FOUND);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, SessionAttribute.ERROR_MESSAGE_COLOR);
		return dashboard;
	}

	// Show all calculations of logged in customer.
	@GetMapping("/customer/deleteAllCalculations")
	public String deleteAllCalculations(@ModelAttribute("policy") InsurancePolicyModel insurancePolicyModel,
			@ModelAttribute(SessionAttribute.LOGIN_PAGE) Login login, Model model, HttpSession session) {

		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		if (session.getAttribute(LOGGED_CUSTOMER) == null) {
			customerLoginModel(model);
			return SessionAttribute.LOGIN_PAGE;
		}

		homeService.deleteAllCalculationsByUserName(((Login) session.getAttribute(LOGGED_CUSTOMER)).getUsername());
		return calculationListConfig(model, session);
	}

	// Global exception handler
	@ExceptionHandler(value = Exception.class)
	public String globalExceptionHandler(Model model, HttpSession session, Exception exception) {
		logger.trace(Text.SESSION_ID + new Throwable().getStackTrace()[0].getMethodName() + "]: " + session.getId()
				+ Text.SESSION_CREATION_TIME + session.getCreationTime() + Text.EXTRA_NEW_LINE);

		model.addAttribute(SessionAttribute.ERROR_MESSAGE, exception.getMessage());
		return "error";
	}

	public String calculationListConfig(Model model, HttpSession session) {
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.CUSTOMER_DASHBOARD);
		session.setAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW, "showAllCalculations");
		session.setAttribute("calculations", homeService
				.getAllCalculationsByUsername(((Login) session.getAttribute(LOGGED_CUSTOMER)).getUsername()));
		customerDashboardModel(model, ((Login) session.getAttribute(LOGGED_CUSTOMER)).getUsername());
		return SessionAttribute.DASHBOARD_PAGE;
	}

	public String calculatorModelConfig(UUID policyId, InsurancePolicyModel insurancePolicyModel, Model model,
			HttpSession session) {

		customerDashboardModel(model, ((Login) (session.getAttribute(LOGGED_CUSTOMER))).getUsername());
		session.setAttribute(SessionAttribute.DASHBOARD_REQUEST, SessionAttribute.CUSTOMER_DASHBOARD);
		InsurancePolicy insurancePolicy = homeService.getInsurancePolicyById(policyId);

		if (insurancePolicy == null) {

			session.setAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW, SessionAttribute.DEFAULT_DASHBOARD_VIEW);
			session.setAttribute(SessionAttribute.CALCULATION_STATUS, SessionAttribute.FALSE_VALUE);
			session.setAttribute(SessionAttribute.INSURANCE_POLICIES, homeService.getAllInsurancePolicies());
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR, Text.POLICY_WITH_ID + policyId
					+ Text.WAS_NOT_FOUND + "<br>The policy might have been removed by admin or does not exist.");
			model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, SessionAttribute.ERROR_MESSAGE_COLOR);

		} else {

			insurancePolicyModel.setPolicyId(insurancePolicy.getPolicyId());
			insurancePolicyModel.setPolicyName(insurancePolicy.getPolicyName());
			insurancePolicyModel.setPolicyType(insurancePolicy.getPolicyType());
			insurancePolicyModel.setPeriodOfCoverage(insurancePolicy.getPeriodOfCoverage());
			insurancePolicyModel.setPremiumAmount(insurancePolicy.getPremiumAmount());
			insurancePolicyModel.setPrice(insurancePolicy.getPrice());

			session.setAttribute(SessionAttribute.CUSTOMER_DASHBOARD_VIEW, "calculatePremium");
			session.setAttribute("policyType", insurancePolicy.getPolicyType());
			session.setAttribute(SessionAttribute.CALCULATION_STATUS, SessionAttribute.TRUE_VALUE);
			if (insurancePolicy.getPolicyType().equals("Life Insurance")
					|| insurancePolicy.getPolicyType().equals("Medical Insurance")) {
				model.addAttribute("choice", Arrays.asList("Yes", "No"));
			} else if (insurancePolicy.getPolicyType().equals("Vehicle Insurance")) {
				model.addAttribute("choice", Arrays.asList("Two Wheeler", "Three Wheeler", "Four Wheeler"));
			}
			model.addAttribute(SessionAttribute.MESSAGE, SessionAttribute.EMPTY_VALUE);
			model.addAttribute(SessionAttribute.MESSAGE_COLOR, SessionAttribute.DEFAULT_MESSAGE_COLOR);
		}
		return SessionAttribute.DASHBOARD_PAGE;
	}

	public void customerLoginModel(Model model) {
		loginModelConfiguration(model, SessionAttribute.CUSTOMER_HEADING, SessionAttribute.CUSTOMER_HEADING_COLOR,
				CUSTOMER_BASE_URL + "dashboard", CUSTOMER_BASE_URL + "signup/page", "post");
	}

	public void adminLoginModel(Model model) {
		loginModelConfiguration(model, SessionAttribute.ADMIN_HEADING, SessionAttribute.ADMIN_HEADING_COLOR,
				ADMIN_BASE_URL + "dashboard", ADMIN_BASE_URL + "signup/page", "post");
	}

	public void customerSignupModel(Model model, HttpSession session) {
		signupModelConfiguration(model, SessionAttribute.CUSTOMER_HEADING, SessionAttribute.CUSTOMER_HEADING_COLOR,
				CUSTOMER_BASE_URL + "signup", CUSTOMER_BASE_URL + "login", "post");
		session.setAttribute("disableLogin", SessionAttribute.FALSE_VALUE);
	}

	public void adminSignupModel(Model model, HttpSession session) {
		signupModelConfiguration(model, SessionAttribute.ADMIN_HEADING, SessionAttribute.ADMIN_HEADING_COLOR,
				ADMIN_BASE_URL + "signup", SessionAttribute.EMPTY_VALUE, "post");
		session.setAttribute("disableLogin", SessionAttribute.TRUE_VALUE);
	}

	public void customerDashboardModel(Model model, String username) {
		dashboardModelConfiguration(model, SessionAttribute.CUSTOMER_HEADING, SessionAttribute.CUSTOMER_HEADING_COLOR,
				CUSTOMER_BASE_URL + "logout", username);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR, SessionAttribute.EMPTY_VALUE);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, "White");
	}

	public void adminDashboardModel(Model model, String username) {
		dashboardModelConfiguration(model, SessionAttribute.ADMIN_HEADING, SessionAttribute.ADMIN_HEADING_COLOR,
				ADMIN_BASE_URL + "logout", username);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR, SessionAttribute.EMPTY_VALUE);
		model.addAttribute(SessionAttribute.DASHBOARD_MESSAGE_BAR_COLOR, "White");

	}

	public void loginModelConfiguration(Model model, String userType, String headingColor, String onLogin,
			String onSignup, String requestType) {
		model.addAttribute(SessionAttribute.USER_TYPE, userType);
		model.addAttribute(SessionAttribute.HEADING_COLOR, headingColor);
		model.addAttribute("onLogin", onLogin);
		model.addAttribute("onSignup", onSignup);
		model.addAttribute("requestType", requestType);
		model.addAttribute(SessionAttribute.MESSAGE, SessionAttribute.EMPTY_VALUE);
		model.addAttribute(SessionAttribute.ERROR_MESSAGE, SessionAttribute.EMPTY_VALUE);
	}

	public void signupModelConfiguration(Model model, String userType, String headingColor, String onSignup,
			String onLogin, String requestType) {
		model.addAttribute(SessionAttribute.USER_TYPE, userType);
		model.addAttribute(SessionAttribute.HEADING_COLOR, headingColor);
		model.addAttribute("onSignup", onSignup);
		model.addAttribute("onLogin", onLogin);
		model.addAttribute("requestType", requestType);
		model.addAttribute(SessionAttribute.MESSAGE, SessionAttribute.EMPTY_VALUE);
		model.addAttribute(SessionAttribute.ERROR_MESSAGE, SessionAttribute.EMPTY_VALUE);
	}

	public void dashboardModelConfiguration(Model model, String userType, String headingColor, String onLogout,
			String username) {
		model.addAttribute(SessionAttribute.USER_TYPE, userType);
		model.addAttribute(SessionAttribute.HEADING_COLOR, headingColor);
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
