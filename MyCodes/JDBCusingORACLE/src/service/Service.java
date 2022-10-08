package service;

import org.apache.log4j.Logger;

public interface Service {

	void setDatbaseConnection(Logger logger);

	void getllUsersList(Logger logger);

	void releaseResourcesAndcloseConnection(Logger logger);

}
