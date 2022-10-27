package pkg.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pkg.base.model.Admin;
import pkg.base.model.User;

@Repository
public class DAOImpl implements DAO {

	private HibernateTemplate hibernateTemplate;

	@Override
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Transactional
	@Override
	public String createNewUserAccount(User user) {
		String result = null;
		if (!checkUsernameOfUserExists(user.getUsername())) {
			result = hibernateTemplate.save(user).toString();
		}
		return result;
	}

	@Transactional
	@Override
	public String createNewAdminAccount(Admin admin) {
		String result = null;
		if (!checkUsernameOfAdminExists(admin.getUsername())) {
			result = hibernateTemplate.save(admin).toString();
		}
		return result;
	}

	@Override
	public boolean checkUsernameOfUserExists(String username) {
		User user = hibernateTemplate.get(User.class, username);
		if (user == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean checkUsernameOfAdminExists(String username) {
		Admin admin = hibernateTemplate.get(Admin.class, username);
		if (admin == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<User> getAllUsers() {
		return hibernateTemplate.loadAll(User.class);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return hibernateTemplate.loadAll(Admin.class);
	}

	@Override
	public User getUserByUsername(String username) {
		return hibernateTemplate.get(User.class, username);
	}

	@Override
	public Admin getAdminByUsername(String username) {
		return hibernateTemplate.get(Admin.class, username);
	}
}
