package pkg.base.service;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import pkg.base.model.Admin;
import pkg.base.model.User;
import pkg.dao.DAO;
import pkg.dao.DAOImpl;

@Service
public class HomeService {

	private DAO dao;
	private Logger logger;

	public String findUser(String username, String password) {
		User user = dao.getUserByUsername(username);

		if (user == null) {
			return "User with provided username does not exists! :(";
		}

		if (!user.getPassword().equals(password)) {
			return "Incorrect password! :(";
		}

		return user.getUsername();
	}

	public String findAdmin(String username, String password) {
		Admin admin = dao.getAdminByUsername(username);

		if (admin == null) {
			return "Admin with provided username does not exists! :(";
		}

		if (!admin.getPassword().equals(password)) {
			return "Incorrect password! :(";
		}

		return admin.getUsername();
	}

	@SuppressWarnings("resource")
	public HomeService() {
		super();
		// Configuring logger.
		logger = Logger.getLogger(HomeService.class);
		BasicConfigurator.configure();
		logger.setLevel(Level.ALL);
		// Configuring DAO.
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dao.cfg.xml");
		dao = context.getBean("daoImpl", DAOImpl.class);
		// context.close();
	}

}
