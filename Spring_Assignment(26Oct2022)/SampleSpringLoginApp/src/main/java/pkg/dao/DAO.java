package pkg.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import pkg.base.model.Admin;
import pkg.base.model.User;

public interface DAO {

	void setHibernateTemplate(HibernateTemplate hibernateTemplate);

	HibernateTemplate getHibernateTemplate();

	String createNewUserAccount(User user);

	String createNewAdminAccount(Admin admin);

	boolean checkUsernameOfUserExists(String username);

	boolean checkUsernameOfAdminExists(String username);

	List<User> getAllUsers();

	List<Admin> getAllAdmins();

	User getUserByUsername(String username);

	Admin getAdminByUsername(String username);

}
