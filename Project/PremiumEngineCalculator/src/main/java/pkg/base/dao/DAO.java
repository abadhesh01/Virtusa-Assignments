package pkg.base.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;

public interface DAO {

	HibernateTemplate getHibernateTemplate();
	
	void setHibernateTemplate(HibernateTemplate hibernateTemplate);

	String toString();
	
}
