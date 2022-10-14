package sample.service;

import sample.entity.Person;

public interface Service {

	// Connecting to database.
	void setConnectionToDatabase();

	// Closing all connections.
	void closeAllConnections();

	// Getting Person object by id from database.
	Person getPerson(long id);

	// Inserting a Person object into database.
	void insertPerson(Person person);

	// Updating specified Person object's data into database.
	void updatePerson(Person person);

	// Deleting specified Person object from database.
	void deletePerson(Person person);

}
