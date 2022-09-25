package Run;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Meal;
import Service.Service;
import Service.ServiceImpl;

public class ExecutionArea {
	public static void main(String[] args) {

		// Scanner Object.
		java.util.Scanner scanner = new Scanner(System.in);

		// Service Object.
		Service service = new ServiceImpl();
		service.setReceiptNumber(1);

		// Adding 10 meals to menu.
		List<Meal> meals = new ArrayList<Meal>();
		meals.add(new Meal(1, "Idli with Samber and Chatni", 30));
		meals.add(new Meal(2, "Plain Dosa", 40));
		meals.add(new Meal(3, "Masala Dosa", 45));
		meals.add(new Meal(4, "Paneer Dosa", 65));
		meals.add(new Meal(5, "Vada", 20));
		meals.add(new Meal(6, "Samber Vada", 35));
		meals.add(new Meal(7, "Chole Bature", 50));
		meals.add(new Meal(8, "Samosa", 25));
		meals.add(new Meal(9, "Special Samosa", 55));
		meals.add(new Meal(10, "Upma", 30));
		try {
			service.setMeals(meals);
		} catch (Exception exception) {
			System.out.println("\n" + exception.getMessage() + "\n");
		}

		boolean flag = true;
		while (flag) {
			try {
				System.out.print(
						"\n[1] Display menu\n[2] Display cart\n[3] Add/Update a meal to cart.\n[4] Remove a meal from cart.\n[5] Buy\n[6] Cancle\n[7] Exit\nEnter your choice: ");
				int choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
				case 1:
					service.viewMenu();
					break;
				case 2:
					service.viewCart();
					break;
				case 3:
					System.out.print("Enter the meal id: ");
					long id = Long.parseLong(scanner.nextLine());
					System.out.print("Enter the number of meals you want to add: ");
					int count = Integer.parseInt(scanner.nextLine());
					service.addItem(id, count);
					break;
				case 4:
					System.out.print("Enter the meal id: ");
					id = Long.parseLong(scanner.nextLine());
					service.removeItem(id);
					break;
				case 5:
					System.out.print("Enter you cash amount: ");
					double cash = Double.parseDouble(scanner.nextLine());
					System.out.println(service.buy(cash));
					break;
				case 6:
					service.cancleOrder();
					break;
				case 7:
					System.out.println("\nBye! :)\n");
					flag = false;
					scanner.close();
					break;

				default:
					System.out.println("\nInvalid choice !!!");
				}
			} catch (Exception exception) {
				System.out.println("\n" + exception.getMessage());
			}
		}

	}
}
