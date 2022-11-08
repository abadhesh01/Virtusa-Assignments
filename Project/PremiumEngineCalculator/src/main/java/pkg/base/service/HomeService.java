package pkg.base.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.base.dao.DAO;
import pkg.base.entity.Admin;
import pkg.base.entity.Calculation;
import pkg.base.entity.Customer;
import pkg.base.entity.InsurancePolicy;
import pkg.base.model.InsurancePolicyModel;
import pkg.base.model.Login;
import pkg.base.model.Signup;

@Service
public class HomeService {

	@Autowired
	private DAO dao;

	// Validating and creating new customer account.
	public String validateCustomerSignup(Signup signup) {

		if (dao.getCustomerByUsername(signup.getUsername()) != null
				|| dao.getAdminByUsername(signup.getUsername()) != null) {
			return "Username already exists!";
		}

		signup.setConfirmPassword(
				Base64.getEncoder().encodeToString(signup.getConfirmPassword().getBytes(StandardCharsets.UTF_8)));
		signup.setNewPassword(signup.getConfirmPassword());

		return dao.createNewCustomerAccount(
				Customer.builder().username(signup.getUsername()).password(signup.getNewPassword()).build());
	}

	// Validating and creating new admin account.
	public String validateAdminSignup(Signup signup) {

		if (dao.getAdminByUsername(signup.getUsername()) != null
				|| dao.getCustomerByUsername(signup.getUsername()) != null) {
			return "Username already exists!";
		}

		signup.setConfirmPassword(
				Base64.getEncoder().encodeToString(signup.getConfirmPassword().getBytes(StandardCharsets.UTF_8)));
		signup.setNewPassword(signup.getConfirmPassword());

		return dao.createNewAdminAccount(
				Admin.builder().username(signup.getUsername()).password(signup.getNewPassword()).build());
	}

	// Validating customer login.
	public String validateCustomerLogin(Login login) {

		Customer customer = dao.getCustomerByUsername(login.getUsername());

		if (customer == null) {
			return "Username does not exist!";
		}

		login.setPassword(Base64.getEncoder().encodeToString(login.getPassword().getBytes(StandardCharsets.UTF_8)));

		if (!customer.getPassword().equals(login.getPassword())) {
			return "Invalid password!";
		}

		return customer.getUsername();
	}

	// Validating admin login.
	public String validateAdminLogin(Login login) {

		Admin admin = dao.getAdminByUsername(login.getUsername());

		if (admin == null) {
			return "Username does not exist!";
		}

		login.setPassword(Base64.getEncoder().encodeToString(login.getPassword().getBytes(StandardCharsets.UTF_8)));

		if (!admin.getPassword().equals(login.getPassword())) {
			return "Invalid password!";
		}

		return admin.getUsername();
	}

	// Adding or updating a policy to database.
	public String addOrUpdatePolicy(InsurancePolicy insurancePolicy) {

		insurancePolicy.setPolicyName(insurancePolicy.getPolicyName().replaceAll("\\s+", " ").trim());

		List<InsurancePolicy> insurancePolicies = dao.getInsurancePolicyByPolicyName(insurancePolicy.getPolicyName());

		if (!insurancePolicies.isEmpty()
				&& insurancePolicies.get(0).getPolicyName().equals(insurancePolicy.getPolicyName())
				&& !insurancePolicies.get(0).getPolicyId().equals(insurancePolicy.getPolicyId())) {
			return "A policy with the same name exists!<br>Please provide some different name.";
		}

		dao.addOrUpdateInsurancePolicy(insurancePolicy);

		return "Operation Successful";
	}

	// Deleting an existing insurance policy.
	public String[] deleteInsurancePolicyById(UUID policyId) {

		String[] result = new String[2];
		if (dao.deletePolicyById(policyId)) {
			result[0] = "#0B8DDD";
			result[1] = "The policy with id=\"" + policyId + "\" has been deleted successfully.";
			return result;
		}

		result[0] = "red";
		result[1] = "The policy with id=\"" + policyId + "\" was not found!<br>"
				+ "The policy might have been removed by anathor admin or does not exist.";
		return result;
	}

	// Getting a insurance policy by id.
	public InsurancePolicy getInsurancePolicyById(UUID policyId) {
		return dao.getInsurancePolicyById(policyId);
	}

	// Getting all the insurance policies.
	public List<InsurancePolicy> getAllInsurancePolicies() {
		return dao.getAllInsurancePolicies();
	}

	// Calculating premium.
	public double calculatePremiumCost(InsurancePolicyModel insurancePolicyModel) {

		double finalPrice = 0.0;
		String policyType = insurancePolicyModel.getPolicyType();
		String personStage = insurancePolicyModel.getPersonStage();
		boolean lifeInsurance = policyType.equals("Life Insurance");
		boolean medicalInsurance = policyType.equals("Medical Insurance");
		boolean vehicleInsurance = policyType.equals("Vehicle Insurance");
		double smokerAdditionalCharges = 0.0;
		double drinkerAdditionalCharges = 0.0;
		double seriousDiseaseAdditionalCharges = 0.0;

		if (lifeInsurance) {
			smokerAdditionalCharges = 0.10;
			drinkerAdditionalCharges = 0.20;
			seriousDiseaseAdditionalCharges = 0.30;
		}

		if (medicalInsurance) {
			smokerAdditionalCharges = 0.05;
			drinkerAdditionalCharges = 0.10;
			seriousDiseaseAdditionalCharges = 0.15;
		}

		if (!vehicleInsurance) {

			if (insurancePolicyModel.getPersonSmokes().equals("Yes")) {
				finalPrice += (insurancePolicyModel.getPrice() * smokerAdditionalCharges);
			}

			if (insurancePolicyModel.getPersonDrinks().equals("Yes")) {
				finalPrice += (insurancePolicyModel.getPrice() * drinkerAdditionalCharges);
			}

			if (insurancePolicyModel.getPersonHasSeriousDisease().equals("Yes")) {
				finalPrice += (insurancePolicyModel.getPrice() * seriousDiseaseAdditionalCharges);
			}

			if (personStage.equals("Young")) {
				finalPrice -= (insurancePolicyModel.getPrice() * 0.15);
			} else if (personStage.equals("Old")) {
				finalPrice -= (insurancePolicyModel.getPrice() * 0.40);
			}

			finalPrice += insurancePolicyModel.getPrice();
			return finalPrice;
		}

		if (vehicleInsurance) {
			String vehicleType = insurancePolicyModel.getVehicleType();
			if (vehicleType.equals("Two Wheeler")) {
				finalPrice -= (insurancePolicyModel.getPrice() * 0.07);
			} else if (vehicleType.equals("Three Wheeler")) {
				finalPrice -= (insurancePolicyModel.getPrice() * 0.12);
			}

			int vehicleAge = insurancePolicyModel.getVehicleAge();
			if (vehicleAge >= 20) {
				finalPrice -= ((0.01 * ((vehicleAge % 10) + ((double) vehicleAge / 10)))
						* insurancePolicyModel.getPrice());
			}

			finalPrice += insurancePolicyModel.getPrice();
			return finalPrice;
		}

		return -1;
	}

	// Update customer's calculation(s).
	public void saveCalculation(String username, Calculation calculation) {
		Customer customer = dao.getCustomerByUsername(username);
		customer.addNewCalculation(calculation);
		calculation.setCustomer(customer);
		dao.updateCustomersData(customer);
	}

	// Get calculation by id.
	public Calculation getCalculationById(UUID calculationId) {
		return dao.getCalculationById(calculationId);
	}

	// Get all calculations by username.
	public List<Calculation> getAllCalculationsByUsername(String username) {
		return dao.getCustomerByUsername(username).getCalculations();
	}

	// Delete all calculations of a customer.
	public void deleteAllCalculationsByUserName(String username) {
		Customer customer = dao.getCustomerByUsername(username);
		List<Calculation> calculations = customer.getCalculations();
		for (Calculation calculation : calculations) {
			calculation.setCustomer(null);
		}
		dao.deleteAllCalculations(calculations);
	}

	// Delete calculation by id.
	public boolean deleteCalculationById(UUID calculationId) {
		Calculation calculation = dao.getCalculationById(calculationId);
		if (calculation == null) {
			return false;
		}

		calculation.getCustomer().getCalculations().remove(calculation);
		calculation.setCustomer(null);
		dao.deleteCalculation(calculation);
		return true;
	}
}
