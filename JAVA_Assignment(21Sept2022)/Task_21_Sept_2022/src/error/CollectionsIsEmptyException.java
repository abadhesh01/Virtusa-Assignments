package error;

import java.util.Collection;

@SuppressWarnings("serial")
public class CollectionsIsEmptyException extends Exception {

	public CollectionsIsEmptyException(Collection<Integer> collections) {
	    super("Error: The provided " + collections.getClass().getName() + " object is empty! :(");
	}
	
}
