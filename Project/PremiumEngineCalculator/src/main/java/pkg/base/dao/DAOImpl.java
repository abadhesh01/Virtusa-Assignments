package pkg.base.dao;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pkg.base.entity.Admin;
import pkg.base.entity.Customer;
import pkg.base.entity.InsurancePolicy;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DAOImpl implements DAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	// Creating new "Customer" account.
	@Transactional
	public String createNewCustomerAccount(Customer newCustomer) {
		return hibernateTemplate.save(newCustomer).toString();
	}

	// Finding a "Customer" by "username".
	public Customer getCustomerByUsername(String username) {
		return hibernateTemplate.get(Customer.class, username);
	}

	// Creating new "Admin" account.
	@Transactional
	public String createNewAdminAccount(Admin newAdmin) {
		return hibernateTemplate.save(newAdmin).toString();
	}

	// Finding a "Admin" by "username".
	public Admin getAdminByUsername(String username) {
		return hibernateTemplate.get(Admin.class, username);
	}

	// Add a new policy.
	@Transactional
	public UUID addnewInsurancePolicy(InsurancePolicy insurancePolicy) {
		return (UUID) hibernateTemplate.save(insurancePolicy);
	}

	// Get policy by policy name.
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<InsurancePolicy> getInsurancePolicyByPolicyName(String policyName) {
		Criteria criteria = hibernateTemplate.getSessionFactory().openSession().createCriteria(InsurancePolicy.class);
		criteria.add(Restrictions.eq("policyName", policyName));
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		return criteria.list();
	}

	// Get all policies.
	public List<InsurancePolicy> getAllInsurancePolicies() {
		return hibernateTemplate.loadAll(InsurancePolicy.class);
	}
}
