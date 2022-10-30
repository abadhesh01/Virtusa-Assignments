package pkg.base.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;

import pkg.base.entity.Admin;
import pkg.base.entity.Customer;

public interface DAO {

	// Getter to get "HibernateTemplate" object.
	HibernateTemplate getHibernateTemplate();
	
	// Setter to set "HibernateTemplate" object.
	void setHibernateTemplate(HibernateTemplate hibernateTemplate);

	// toString() to convert DAOImpl to String type.
	String toString();
	
	// Creating new "Customer" account.
	String createNewCustomerAccount(Customer newCustomer);
	
	// Finding a "Customer" by "username".
	Customer getCustomerByUsername(String username);
	
	// Creating new "Admin" account.
	String createNewAdminAccount(Admin newAdmin);
	
	// Finding a "Admin" by "username".
	Admin getAdminByUsername(String username);

}
