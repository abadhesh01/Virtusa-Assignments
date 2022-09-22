package service;

import java.util.Collection;

import error.CollectionsIsEmptyException;

public interface Service {

	public void insertIntegerObjectsIntoCollections(Collection<Integer> collections, int startValue, int endValue) throws NullPointerException, Exception;
	
	public void printCollectionObject(Collection<Integer> collections) throws NullPointerException, CollectionsIsEmptyException, Exception;
	
	public void removeEvenIntegerObjectsFromCollections(Collection<Integer> collections) throws NullPointerException, CollectionsIsEmptyException, Exception;

}
