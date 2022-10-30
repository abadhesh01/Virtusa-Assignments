package pkg.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.base.dao.DAO;
import pkg.base.entity.Admin;
import pkg.base.entity.Customer;
import pkg.base.model.Login;
import pkg.base.model.Signup;

@Service
public class HomeService {

	@Autowired
	DAO dao;

	public String sampleServiceRequest() {
		return dao.getHibernateTemplate().toString();
	}

	// Validating and creating new customer account.
	public String validateCustomerSignup(Signup signup) {

		if (dao.getCustomerByUsername(signup.getUsername()) != null) {
			return "Username already exists!";
		}

		return dao.createNewCustomerAccount(
				Customer.builder().username(signup.getUsername()).password(signup.getNewPassword()).build());
	}

	// Validating and creating new admin account.
	public String validateAdminSignup(Signup signup) {

		if (dao.getAdminByUsername(signup.getUsername()) != null) {
			return "Username already exists!";
		}

		return dao.createNewAdminAccount(
				Admin.builder().username(signup.getUsername()).password(signup.getNewPassword()).build());
	}

	// Validating customer login.
	public String validateCustomerLogin(Login login) {

		Customer customer = dao.getCustomerByUsername(login.getUsername());

		if (customer == null) {
			return "Username does not exist!";
		}

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

		if (!admin.getPassword().equals(login.getPassword())) {
			return "Invalid password!";
		}

		return admin.getUsername();
	}

}
