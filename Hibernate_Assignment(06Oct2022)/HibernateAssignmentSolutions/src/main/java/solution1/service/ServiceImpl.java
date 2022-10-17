package solution1.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import solution1.model.Address;
import solution1.model.Customer;

public class ServiceImpl implements Service {

	private Configuration configuration;
	private SessionFactory sessionFactory;
	private Session session;

	public void setConnectionToDB() {
		session = sessionFactory.openSession();
	}

	public void closeAllConnections() {
		session.close();
		sessionFactory.close();
		configuration = null;
	}

	public void addCustomer(Customer customer) {
		if (customer == null) {
			return;
		}
		Transaction transaction = session.beginTransaction();
		session.save(customer);
		transaction.commit();
	}

	public void updateCustomer(Customer customer) {
		if (customer == null) {
			return;
		}
		Transaction transaction = session.beginTransaction();
		session.update(customer);
		transaction.commit();
	}

	public void deleteCustomer(Customer customer) {
		if (customer == null) {
			return;
		}
		Transaction transaction = session.beginTransaction();
		session.delete(customer);
		transaction.commit();
	}

	public Customer getCustomerById(long id) {
		return (Customer) session.get(Customer.class, id);
	}

	public Address getAddressById(long id) {
		return (Address) session.get(Address.class, id);
	}

	@SuppressWarnings("deprecation")
	public ServiceImpl() {
		super();
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
	}

}
