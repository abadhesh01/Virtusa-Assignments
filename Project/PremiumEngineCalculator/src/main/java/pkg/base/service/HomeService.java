package pkg.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.base.dao.DAO;
import pkg.base.model.Login;
import pkg.base.model.Signup;

@Service
public class HomeService {

	@Autowired
	DAO dao;

	public String sampleServiceRequest() {
		return dao.getHibernateTemplate().toString();
	}

	public String validateCustomerLogin(Login login) {
		String defaultUsername = "user";
		String defaultPassword = "user@2022";

		return validateUserLogin(login, defaultUsername, defaultPassword);
	}

	public String validateAdminLogin(Login login) {
		String defaultUsername = "admin";
		String defaultPassword = "admin@2022";

		return validateUserLogin(login, defaultUsername, defaultPassword);
	}

	public String validateUserLogin(Login login, String username, String password) {
		if (!login.getUsername().equals(username)) {
			return "Username does not exist!";
		}

		if (!login.getPassword().equals(password)) {
			return "Incorrect password!";
		}

		return login.getUsername();
	}

	public String validateCustomerSignup(Signup signup) {
		String defaultUsername = "user";

		if (signup.getUsername().equals(defaultUsername)) {
			return "Username already exists!";
		}

		return signup.getUsername();
	}

	public String validateAdminSignup(Signup signup) {
		String defaultUsername = "admin";

		if (signup.getUsername().equals(defaultUsername)) {
			return "Username already exists!";
		}

		return signup.getUsername();
	}

}
