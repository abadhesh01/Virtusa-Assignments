import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import service.Service;
import service.ServiceImpl;

public class Main {
	
	
	public static void main(String[] args) throws Exception{

		// creating service object.
	    Service service = new ServiceImpl();
		
		
		
        // Creating an ArrayList for Integers.
        List<Integer> sampleArrayList = new ArrayList<Integer>();
        // Creating an LinkedList for Integers.
        List<Integer> sampleLinkedList = new LinkedList<Integer>();
	    // Creating an TreeSet for Integers.
        Set<Integer> sampleTreeSet = new TreeSet<Integer>();

        
        
        // Printing Collections.
        System.out.println("Before inserting values:\n------------------------");
        try {service.printCollectionObject(sampleArrayList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleLinkedList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleTreeSet);} catch (Exception exception) {System.out.println(exception.getMessage());}
        
        
        
        // Inserting values into collections.
        // Insert 1 to 10 into ArrayList.
        try {service.insertIntegerObjectsIntoCollections(sampleArrayList, 1, 10);} catch (Exception exception) {System.out.println(exception.getMessage());}      
        // Insert 11 to 20 into LinkedList.
        try {service.insertIntegerObjectsIntoCollections(sampleLinkedList, 11, 20);} catch (Exception exception) {System.out.println(exception.getMessage());}   
        // Insert 21 to 30 into TreeSet.
        try {service.insertIntegerObjectsIntoCollections(sampleTreeSet, 21, 30);} catch (Exception exception) {System.out.println(exception.getMessage());}
        
        
        
        // Printing Collections.
        System.out.println("\n\n\n\nAfter inserting values:\n-----------------------");
        try {service.printCollectionObject(sampleArrayList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleLinkedList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleTreeSet);} catch (Exception exception) {System.out.println(exception.getMessage());}
        
         
        
        // Removing even values from collections.
        // Removing even values from ArrayList.
        try {service.removeEvenIntegerObjectsFromCollections(sampleArrayList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        // Removing even values from LinkedList.
        try {service.removeEvenIntegerObjectsFromCollections(sampleLinkedList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        // Removing even values from TreeSet.
        try {service.removeEvenIntegerObjectsFromCollections(sampleTreeSet);} catch (Exception exception) {System.out.println(exception.getMessage());}
        
        
        
        
        // Printing Collections.
        System.out.println("\n\n\n\nAfter removing even values:\n---------------------------");
        try {service.printCollectionObject(sampleArrayList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleLinkedList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleTreeSet);} catch (Exception exception) {System.out.println(exception.getMessage());}
        
        
        
        // Removing all elements from collections.
        sampleArrayList.clear();
        sampleLinkedList.clear();
        sampleTreeSet.clear();
        
        
        
        // Printing Collections.
        System.out.println("\n\n\n\nAfter removing all values:\n--------------------------");
        try {service.printCollectionObject(sampleArrayList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleLinkedList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleTreeSet);} catch (Exception exception) {System.out.println(exception.getMessage());}
        
        
        
        // Assigning "null" to collection references.
        sampleArrayList = null;
        sampleLinkedList = null;
        sampleTreeSet = null;
        
        
        
        // Printing Collections.
        System.out.println("\n\n\n\nAfter assigning null to collection references:\n----------------------------------------------");
        try {service.printCollectionObject(sampleArrayList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleLinkedList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        try {service.printCollectionObject(sampleTreeSet);} catch (Exception exception) {System.out.println(exception.getMessage());}
        
        
        
        // Trying to remove even values from  collections pointing towards null.
        System.out.println("\n\n\nTrying to remove even values from collections pointing towards null:\n--------------------------------------------------------------------");
        // Removing even values from ArrayList.
        try {service.removeEvenIntegerObjectsFromCollections(sampleArrayList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        // Removing even values from LinkedList.
        try {service.removeEvenIntegerObjectsFromCollections(sampleLinkedList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        // Removing even values from TreeSet.
        try {service.removeEvenIntegerObjectsFromCollections(sampleTreeSet);} catch (Exception exception) {System.out.println(exception.getMessage());}
	
	
	
        // Trying to insert values into collections pointing towards null.
        System.out.println("\n\n\nTrying to insert values into collections pointing towards null:\n---------------------------------------------------------------");
        // Insert 1 to 10 into ArrayList.
        try {service.insertIntegerObjectsIntoCollections(sampleArrayList, 1, 10);} catch (Exception exception) {System.out.println(exception.getMessage());}      
        // Insert 11 to 20 into LinkedList.
        try {service.insertIntegerObjectsIntoCollections(sampleLinkedList, 11, 20);} catch (Exception exception) {System.out.println(exception.getMessage());}   
        // Insert 21 to 30 into TreeSet.
        try {service.insertIntegerObjectsIntoCollections(sampleTreeSet, 21, 30);} catch (Exception exception) {System.out.println(exception.getMessage());}
	
        
        // Assigning empty collection object to collection references.
        sampleArrayList = new ArrayList<Integer>();
        sampleLinkedList = new LinkedList<Integer>();
        sampleTreeSet = new TreeSet<Integer>();
        
        
        // Trying to remove even values from empty collections.
        System.out.println("\n\n\nTrying to remove even values from empty collections:\n----------------------------------------------------");
        // Removing even values from ArrayList.
        try {service.removeEvenIntegerObjectsFromCollections(sampleArrayList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        // Removing even values from LinkedList.
        try {service.removeEvenIntegerObjectsFromCollections(sampleLinkedList);} catch (Exception exception) {System.out.println(exception.getMessage());}
        // Removing even values from TreeSet.
        try {service.removeEvenIntegerObjectsFromCollections(sampleTreeSet);} catch (Exception exception) {System.out.println(exception.getMessage());}
	
        
	}
}
