package sample.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import sample.entity.Person;

public class ServiceImpl implements Service {

	private Configuration configuration;
	private SessionFactory sessionFactory;
	private Session session;

	public void setConnectionToDatabase() {
		session = sessionFactory.openSession();
	}

	public void closeAllConnections() {
		session.close();
		sessionFactory.close();
		configuration = null;
	}

	public Person getPerson(long id) {
		return (Person) session.get(Person.class, id);
	}

	public void insertPerson(Person person) {
		Transaction transaction = session.beginTransaction();
		session.save(person);
		transaction.commit();
		transaction = null;
	}

	public void updatePerson(Person person) {
		Transaction transaction = session.beginTransaction();
		session.update(person);
		transaction.commit();
		transaction = null;
	}

	public void deletePerson(Person person) {
		Transaction transaction = session.beginTransaction();
		session.delete(person);
		transaction.commit();
		transaction = null;
	}

	@SuppressWarnings("deprecation")
	public ServiceImpl() {
		super();
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
	}

}
