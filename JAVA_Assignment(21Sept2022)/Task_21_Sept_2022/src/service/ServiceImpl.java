package service;

import java.util.Collection;
import java.util.Iterator;

import error.CollectionsIsEmptyException;

public class ServiceImpl implements Service {

	public void checkCollectionsIsNotNull(Collection<Integer> collections) throws NullPointerException, Exception {
		if (collections == null)
			throw new NullPointerException("Error: Reference pointing to null was passed! :(");
	}

	public void insertIntegerObjectsIntoCollections(Collection<Integer> collections, int startValue, int endValue)
			throws NullPointerException, Exception {

		checkCollectionsIsNotNull(collections);

		for (/* No initialization required */; startValue <= endValue; startValue++) {
			collections.add(Integer.valueOf(startValue));
		}
	}

	public void printCollectionObject(Collection<Integer> collections)
			throws NullPointerException, CollectionsIsEmptyException, Exception {

		checkCollectionsIsNotNull(collections);

		if (collections.isEmpty())
			throw new CollectionsIsEmptyException(collections);

		System.out.println("OBJECT(" + collections.getClass().getName() + ") : " + collections);
	}

	public void printEvenIntegerObjectsFromCollections(Collection<Integer> collections)
			throws NullPointerException, CollectionsIsEmptyException, Exception {
		
		checkCollectionsIsNotNull(collections);

		if (collections.isEmpty())
			throw new CollectionsIsEmptyException(collections);

		String textString = "OBJECT(" + collections.getClass().getName() + ") : [";

		Iterator<Integer> iterator = collections.iterator();
		while (iterator.hasNext()) {
			Integer value = iterator.next();
			if (value.intValue() % 2 == 0) {
				// iterator.remove(); // This will remove even values.
				textString += value + ", ";
			}
		}

		textString = textString.substring(0, (textString.length() - 2)) + "]";
		System.out.println(textString);
	}

}
