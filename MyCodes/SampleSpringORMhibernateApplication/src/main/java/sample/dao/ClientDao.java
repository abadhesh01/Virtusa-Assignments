package sample.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import lombok.Data;
import sample.model.Client;

@Data
public class ClientDao {

	private HibernateTemplate hibernateTemplate;

	@Transactional
	public long addNewClient(Client client) {
		return (Long) hibernateTemplate.save(client);
	}

	@Transactional
	public Client getClient(long id) {
		return hibernateTemplate.get(Client.class, id);
	}

	@Transactional
	public List<Client> getAllClients() {
		return hibernateTemplate.loadAll(Client.class);
	}

	@Transactional
	public void updateClient(Client client) {
		hibernateTemplate.update(client);
	}

	@Transactional
	public void deleteClient(Client client) {
		hibernateTemplate.delete(client);
	}
}
