package pkg.base.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pkg.base.entity.Admin;
import pkg.base.entity.Customer;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DAOImpl implements DAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	// Creating new "Customer" account.
	@Transactional
	public String createNewCustomerAccount(Customer newCustomer)
	{
		return hibernateTemplate.save(newCustomer).toString();
	}
	
	// Finding a "Customer" by "username".
	public Customer getCustomerByUsername(String username)
	{
		return hibernateTemplate.get(Customer.class, username);
	}
	
	// Creating new "Admin" account.
	@Transactional
	public String createNewAdminAccount(Admin newAdmin)
	{
		return hibernateTemplate.save(newAdmin).toString();
	}
	
	// Finding a "Admin" by "username".
	public Admin getAdminByUsername(String username)
	{
		return hibernateTemplate.get(Admin.class, username);
	}
}
