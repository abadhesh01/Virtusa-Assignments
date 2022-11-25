package pkg.base.dao;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pkg.base.entity.Admin;
import pkg.base.entity.Calculation;
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
	@Override
	public String createNewCustomerAccount(Customer newCustomer) {
		return hibernateTemplate.save(newCustomer).toString();
	}

	// Update customer's data.
	@Transactional
	@Override
	public void updateCustomersData(Customer customer) {
		hibernateTemplate.saveOrUpdate(customer);
	}

	// Finding a "Customer" by "username".
	@Override
	public Customer getCustomerByUsername(String username) {
		return hibernateTemplate.get(Customer.class, username);
	}

	// Creating new "Admin" account.
	@Transactional
	@Override
	public String createNewAdminAccount(Admin newAdmin) {
		return hibernateTemplate.save(newAdmin).toString();
	}

	// Finding a "Admin" by "username".
	@Override
	public Admin getAdminByUsername(String username) {
		return hibernateTemplate.get(Admin.class, username);
	}

	// Add or update a policy.
	@Transactional
	@Override
	public void addOrUpdateInsurancePolicy(InsurancePolicy insurancePolicy) {
		hibernateTemplate.saveOrUpdate(insurancePolicy);
	}

	// Delete an existing policy.
	@Transactional
	@Override
	public boolean deletePolicyById(UUID policyId) {
		InsurancePolicy policy = hibernateTemplate.get(InsurancePolicy.class, policyId);
		if (policy != null) {
			hibernateTemplate.delete(policy);
			return true;
		}
		return false;
	}

	// Get policy by policy id.
	@Override
	public InsurancePolicy getInsurancePolicyById(UUID policyId) {
		return hibernateTemplate.get(InsurancePolicy.class, policyId);
	}

	// Get policy by policy name.
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<InsurancePolicy> getInsurancePolicyByPolicyName(String policyName) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(InsurancePolicy.class);
		criteria.add(Restrictions.eq("policyName", policyName));
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		return criteria.list();
	}

	// Get all policies.
	@Override
	public List<InsurancePolicy> getAllInsurancePolicies() {
		return hibernateTemplate.loadAll(InsurancePolicy.class);
	}

	@Override
	// Get calculation by id.
	public Calculation getCalculationById(UUID calculationId) {
		return hibernateTemplate.get(Calculation.class, calculationId);
	}

	@Transactional
	@Override
	// Delete calculation.
	public void deleteCalculation(Calculation calculation) {
		hibernateTemplate.delete(calculation);
	}

	// Delete all calculations of provided list.
	@Transactional
	@Override
	public void deleteAllCalculations(List<Calculation> calculations) {
		hibernateTemplate.deleteAll(calculations);
	}

}
