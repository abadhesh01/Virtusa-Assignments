import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import service.Service;
import service.ServiceImpl;

public class Main {

	public static void main(String[] args) throws Exception {

		// creating service object.
		Service service = new ServiceImpl();

		List<Integer> sampleArrayList = null;
		List<Integer> sampleLinkedList = null;
		Set<Integer> sampleTreeSet = null;
		Set<Integer> sampleHashSet = null;

		// Printing null collections.
		System.out.println("Printing 'null' collections:\n----------------------------");
		try {
			service.printCollectionObject(sampleArrayList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleLinkedList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleTreeSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleHashSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		// Printing even values from null collections.
		System.out.println(
				"\n\n\n\nPrinting even values from 'null' collections:\n---------------------------------------------");
		try {
			service.printEvenIntegerObjectsFromCollections(sampleArrayList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleLinkedList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleTreeSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleHashSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		// Inserting values into null collections.
		System.out.println(
				"\n\n\n\nInserting values into 'null' collections:\n-----------------------------------------");
		// Inserting 1 to 10 into ArrayList.
		try {
			service.insertIntegerObjectsIntoCollections(sampleArrayList, 1, 10);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		// Inserting 11 to 20 into LinkedList.
		try {
			service.insertIntegerObjectsIntoCollections(sampleLinkedList, 11, 20);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		// Inserting 21 to 30 into TreeSet.
		try {
			service.insertIntegerObjectsIntoCollections(sampleTreeSet, 21, 30);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		// Inserting 31 to 40 into HashSet.
		try {
			service.insertIntegerObjectsIntoCollections(sampleHashSet, 31, 40);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		// Empty collection objects assigned to collection references.
		System.out.println("\n\n\n\nEmpty collection objects assigned to collection references.");
		sampleArrayList = new ArrayList<Integer>();
		sampleLinkedList = new LinkedList<Integer>();
		sampleTreeSet = new TreeSet<Integer>();
		sampleHashSet = new HashSet<Integer>();

		// Printing empty collections.
		System.out.println("\n\n\n\nPrinting 'empty' collections:\n-----------------------------");
		try {
			service.printCollectionObject(sampleArrayList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleLinkedList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleTreeSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleHashSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		// Printing even values from empty collections.
		System.out.println(
				"\n\n\n\nPrinting even values from 'empty' collections:\n----------------------------------------------");
		try {
			service.printEvenIntegerObjectsFromCollections(sampleArrayList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleLinkedList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleTreeSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleHashSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		// Inserting values into empty collections.
		System.out.println(
				"\n\n\n\nInserting values into 'empty' collections:\n------------------------------------------");
		// Inserting 1 to 10 into ArrayList.
		try {
			service.insertIntegerObjectsIntoCollections(sampleArrayList, 1, 10);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		// Inserting 11 to 20 into LinkedList.
		try {
			service.insertIntegerObjectsIntoCollections(sampleLinkedList, 11, 20);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		// Inserting 21 to 30 into TreeSet.
		try {
			service.insertIntegerObjectsIntoCollections(sampleTreeSet, 21, 30);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		// Insert 31 to 40 into TreeSet.
		try {
			service.insertIntegerObjectsIntoCollections(sampleHashSet, 31, 40);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		// Printing collections.
		System.out.println("\n\n\n\nPrinting collections:\n---------------------");
		try {
			service.printCollectionObject(sampleArrayList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleLinkedList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleTreeSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printCollectionObject(sampleHashSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		// Printing even values from collections.
		System.out.println("\n\n\n\nPrinting even values from collections:\n--------------------------------------");
		try {
			service.printEvenIntegerObjectsFromCollections(sampleArrayList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleLinkedList);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleTreeSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		try {
			service.printEvenIntegerObjectsFromCollections(sampleHashSet);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}
}
