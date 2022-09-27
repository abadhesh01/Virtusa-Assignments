package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Main {
	
	// 1. Add the product to Collection
	// 2. Display all products.
	// 3. Get a product by Id
	// 4. Get the product between 1000 - 1500 price
	// 5. Get the product belongs to same company in another list
	// 6. Get the products by date
	// 7. Get the product by name
	// 8. Count number of product
	// 9. find max and min product price.

	public static void main(String[] args) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<Product> products = new ArrayList<Product>();

		//[1] Adding 10 sample products to product list.
		for (int i = 2; i <= 10; i += 2) {
			products.add(new Product(i, "Name" + i, dateFormat.parse(i + "/09/2022"), ((double) i / 2.0d) * 500.0d,
					"Company" + i));
		}

		for (int i = 1; i <= 9; i += 2) {
			products.add(new Product(i, "Name" + i, dateFormat.parse(i + "/09/2022"), ((double) i / 2.0d) * 500.0d,
					"Company" + i));
		}

		//[2] Printing all the product details.
		System.out.println("\n\nSample list of products =>");
		for (Product product : products) {
			System.out.println(product);
		}

		//[3] Getting products with id=3 or id=6 or id=9.
		System.out.println("\n\nGetting products by id for id=3 or id=6 or id=9 =>");
		for (Product product : products) {
			int id = product.getProductId();
			if (id == 3 || id == 6 || id == 9) {
				System.out.println(product.toString());
			}
		}

		//[4] Getting products having price between 1000-1500.
		System.out.println("\n\nGetting products having price between 1000-1500 =>");
		for (Product product : products) {
			double price = product.getPrice();
			if (price >= 1000.0 && price <= 1500.0) {
				System.out.println(product.toString());
			}
		}

		//[5] Getting products having date=03/09/2022 or date=06/09/2022 or
		// date=09/09/2022.
		System.out.println("\n\nGetting products having date=03/09/2022 or date=06/09/2022 or date=09/09/2022 =>");
		for (Product product : products) {
			Date date = product.getDate();
			if (date.equals(dateFormat.parse("03/09/2022")) || date.equals(dateFormat.parse("06/09/2022"))
					|| date.equals(dateFormat.parse("09/09/2022"))) {
				System.out.println(product);
			}
		}

		//[6] Getting products having name "Name2" or "Name6" or "Name7".
		System.out.println("\n\nGetting products having name \"Name2\" or \"Name6\" or \"Name7\" =>");
		for (Product product : products) {
			String name = product.getProductName();
			if (name.equals("Name2") || name.equals("Name6") || name.equals("Name8")) {
				System.out.println(product);
			}
		}

		//[7] Counting the number of products.
		System.out.println("\n\nTotal number of products: " + products.size());

		//[8] Getting product with maximum price and minimum price.
		System.out.println("\n\nGetting product with maximum price and minimum price =>");
		Product maxPriceProduct = products.get(0);
		Product minPriceProduct = products.get(1);
		for (Product product : products) {
			// Getting the product with maximum price.
			if (product.getPrice() > maxPriceProduct.getPrice()) {
				maxPriceProduct = product;
			}

			// Getting the product with minimum price.
			if (product.getPrice() < minPriceProduct.getPrice()) {
				minPriceProduct = product;
			}
		}
		System.out.println("Product with max price:\n" + maxPriceProduct);
		System.out.println("Product with min price:\n" + minPriceProduct);
		
		//[9] Creating a new sample list with 10 objects.
		List<Product> products2 = new ArrayList<Product>(products);
		for(int i=0; i<5; i++)
		{
			int j = (int) ((Math.random() * 10.0d)) % 5;
			products2.remove(j);
		}
		for(int i=30; i<=35; i++)
		{
			products2.add(new Product(i, "Name" + i, dateFormat.parse(i + "/09/2022"), ((double) i / 2.0d) * 500.0d,
					"Company" + i));
		}
		Collections.shuffle(products2);
		
		//[10] Printing sample list of products from new list.
		System.out.println("\n\nSample list of products from new list =>");
		for (Product product : products2) {
			System.out.println(product);
		}
		
		//[11] Getting products having same company from both old and new list.
		System.out.println("\n\nGetting products having same company from both old and new list =>");
		for (Product product1 : products) {
			for (Product product2 : products2) {
				if(product1.getProductName().equals(product2.getProductName())) {
					System.out.println(product2);
					continue;
				}
			}
		}
	}

}
