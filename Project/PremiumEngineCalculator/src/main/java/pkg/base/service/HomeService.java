package pkg.base.service;

import java.io.UnsupportedEncodingException;
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
	public String validateCustomerSignup(Signup signup) throws UnsupportedEncodingException {

		if (dao.getCustomerByUsername(signup.getUsername()) != null
				|| dao.getAdminByUsername(signup.getUsername()) != null) {
			return "Username already exists!";
		}

		signup.setConfirmPassword(Base64.getEncoder().encodeToString(signup.getConfirmPassword().getBytes("UTF-8")));
		signup.setNewPassword(signup.getConfirmPassword());

		return dao.createNewCustomerAccount(
				Customer.builder().username(signup.getUsername()).password(signup.getNewPassword()).build());
	}

	// Validating and creating new admin account.
	public String validateAdminSignup(Signup signup) throws UnsupportedEncodingException {

		if (dao.getAdminByUsername(signup.getUsername()) != null
				|| dao.getCustomerByUsername(signup.getUsername()) != null) {
			return "Username already exists!";
		}

		signup.setConfirmPassword(Base64.getEncoder().encodeToString(signup.getConfirmPassword().getBytes("UTF-8")));
		signup.setNewPassword(signup.getConfirmPassword());

		return dao.createNewAdminAccount(
				Admin.builder().username(signup.getUsername()).password(signup.getNewPassword()).build());
	}

	// Validating customer login.
	public String validateCustomerLogin(Login login) throws UnsupportedEncodingException {

		Customer customer = dao.getCustomerByUsername(login.getUsername());

		if (customer == null) {
			return "Username does not exist!";
		}

		login.setPassword(Base64.getEncoder().encodeToString(login.getPassword().getBytes("UTF-8")));

		if (!customer.getPassword().equals(login.getPassword())) {
			return "Invalid password!";
		}

		return customer.getUsername();
	}

	// Validating admin login.
	public String validateAdminLogin(Login login) throws UnsupportedEncodingException {

		Admin admin = dao.getAdminByUsername(login.getUsername());

		if (admin == null) {
			return "Username does not exist!";
		}

		login.setPassword(Base64.getEncoder().encodeToString(login.getPassword().getBytes("UTF-8")));

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

		String result[] = new String[2];
		if (dao.deletePolicyById(policyId) == true) {
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

		if (policyType.equals("Life Insurance") || policyType.equals("Medical Insurance")) {

			if (insurancePolicyModel.getPersonSmokes().equals("Yes")) {
				if (policyType.equals("Life Insurance")) {
					finalPrice += (insurancePolicyModel.getPrice() * 0.10);
				} else if (policyType.equals("Medical Insurance")) {
					finalPrice += (insurancePolicyModel.getPrice() * 0.05);
				}
			}

			if (insurancePolicyModel.getPersonDrinks().equals("Yes")) {
				if (policyType.equals("Life Insurance")) {
					finalPrice += (insurancePolicyModel.getPrice() * 0.20);
				} else if (policyType.equals("Medical Insurance")) {
					finalPrice += (insurancePolicyModel.getPrice() * 0.10);
				}
			}

			if (insurancePolicyModel.getPersonHasSeriousDisease().equals("Yes")) {
				if (policyType.equals("Life Insurance")) {
					finalPrice += (insurancePolicyModel.getPrice() * 0.30);
				} else if (policyType.equals("Medical Insurance")) {
					finalPrice += (insurancePolicyModel.getPrice() * 0.15);
				}
			}

			String personStage = insurancePolicyModel.getPersonStage();

			if (personStage.equals("Young")) {
				finalPrice -= (insurancePolicyModel.getPrice() * 0.15);
			} else if (personStage.equals("Old")) {
				finalPrice -= (insurancePolicyModel.getPrice() * 0.40);
			}

			finalPrice += insurancePolicyModel.getPrice();
			return finalPrice;
		}

		else if (policyType.equals("Vehicle Insurance")) {

			String vehicleType = insurancePolicyModel.getVehicleType();
			if (vehicleType.equals("Two Wheeler")) {
				finalPrice -= (insurancePolicyModel.getPrice() * 0.07);
			} else if (vehicleType.equals("Three Wheeler")) {
				finalPrice -= (insurancePolicyModel.getPrice() * 0.12);
			}

			int vehicleAge = insurancePolicyModel.getVehicleAge();
			if (vehicleAge >= 20) {
				finalPrice -= ((0.01 * ((vehicleAge % 10) + (vehicleAge / 10))) * insurancePolicyModel.getPrice());
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
