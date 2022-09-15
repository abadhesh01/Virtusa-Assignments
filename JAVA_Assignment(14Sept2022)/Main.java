
class Product
{
	// Fields
	private String brand;
	private String model;
	private String colour;
	
	// Setters
	public void setBrand(String brand)
	{
		this.brand = brand;
	}
	
	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void setColour(String colour)
	{
		this.colour = colour;
	}
	
	// Getters
	public String getBrand()
	{
		return brand;
	}
	
	public String getModel()
	{
		return model;
	}
	
	public String getColour()
	{
		return colour;
	}
	
	@Override
	public String toString()
	{
		return getClass().getName() + "@" + hashCode() + ":\n{brand='" + getBrand() + "',\nmodel='" + getModel() + "',\ncolour='" + getColour() + "'}";
	}
	
}

class Vehicle extends Product
{
	// Fields
	private String type;
	private String fuelType;
	
	
	// Setters
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setFuelType(String fuelType)
	{
		this.fuelType = fuelType;
	}
	
	// Getters
	public String getType()
	{
		return type;
	}
	
	public String getFuelType()
	{
		return fuelType;
	}
	
	// toString()
	@Override
	public String toString()
	{
		return getClass().getName() + "@" + hashCode() + ":\n{brand='" + getBrand() + "',\nmodel='" + getModel() + "',\ncolour='" + getColour() + "',\ntype='" + getType() + "',\nfuelType='" + getFuelType() + "'}";
	}
}

class Truck extends Vehicle
{
	// Field
	private double heightLimitWithCargoInMeters;
	
	// Setter
	public void setHeightLimitWithCargoInMeters(double limit)
	{
		heightLimitWithCargoInMeters = limit;
	}
	
	// Getter
	public double getHeightLimitWithCargoInMeters()
	{
		return heightLimitWithCargoInMeters;
	}
	
    // toString()
	@Override
	public String toString()
	{
		return getClass().getName() + "@" + hashCode() + ":\n{brand='" + getBrand() + "',\nmodel='" + getModel() + "',\ncolour='" + getColour() + "',\ntype='" + getType() + "',\nfuelType='" + getFuelType() + "',\nheightLimitWithCargoInMeters='" + getHeightLimitWithCargoInMeters() + "'}";
	}
}

class Car extends Vehicle
{
	// Field
	private int numberOfSeats;
	
	// Setter
	public void setNumberOfSeats(int number)
	{
		numberOfSeats = number;
	}
	
	// Getters
	public int getNumberOfSeats()
	{
		return numberOfSeats;
	}
	
	// toString()
	@Override
	public String toString()
	{
		return getClass().getName() + "@" + hashCode() + ":\n{brand='" + getBrand() + "',\nmodel='" + getModel() + "',\ncolour='" + getColour() + "',\ntype='" + getType() + "',\nfuelType='" + getFuelType() + "',\nnumberOfSeats='" + getNumberOfSeats() + "'}";
	}
}

public class Main
{
	public static void main(String []args)
	{
		
		Product product = new Product();
		product.setBrand("ProductBrand");
		product.setModel("ProductModel");
		product.setColour("ProductColour");
		System.out.println("\n" + product.toString());
		
	    Vehicle vehicle = new Vehicle();
		vehicle.setBrand("VehicleBrand");
		vehicle.setModel("VehicleModel");
		vehicle.setColour("VehicleColour");
		vehicle.setType("VehicleType");
		vehicle.setFuelType("VehicleFuelType");
		System.out.println("\n" + vehicle.toString());
		
		Truck mahindraCamper = new Truck();
		mahindraCamper.setBrand("Mahindra");
		mahindraCamper.setModel("Camper");
		mahindraCamper.setColour("White");
		mahindraCamper.setType("Pickup Truck");
		mahindraCamper.setFuelType("Diesel");
		mahindraCamper.setHeightLimitWithCargoInMeters(4.75);
		System.out.println("\n" + mahindraCamper.toString());
		
		Car marutiSuzuki800 = new Car();
		marutiSuzuki800.setBrand("Maruti Suzuki");
		marutiSuzuki800.setModel("800");
		marutiSuzuki800.setColour("Red");
		marutiSuzuki800.setType("Hatchback");
		marutiSuzuki800.setFuelType("Petrol");
		marutiSuzuki800.setNumberOfSeats(4);
		System.out.println("\n" + marutiSuzuki800.toString());
		
		Product sampleVehicle = new Vehicle();
		sampleVehicle.setBrand("SampleVehicleBrand");
		sampleVehicle.setModel("SampleVehicleModel");
		sampleVehicle.setColour("SampleVehicleColour");
		//sampleVehicle.setType("SampleVehicleType"); // This will throw compile time error.
		//sampleVehicle.setFuelType("SampleVehicleFuelType"); // This will throw compile time error.
		System.out.println("\n" + sampleVehicle.toString());
		
		Product tataAceMagic = new Truck();
		tataAceMagic.setBrand("TATA Motors");
		tataAceMagic.setModel("Ace Magic");
		tataAceMagic.setColour("Blue");
		//tataAceMagic.setType("Pickup Truck"); // This will throw compile time error.
		//tataAceMagic.setFuelType("Diesel"); // This will throw compile time error.
		//tataAceMagic.setHeightLimitWithCargoInMeters(4.75); // This will throw compile time error.
		System.out.println("\n" + tataAceMagic.toString());
		
		Product toyotaCorollaAltis = new Car();
		toyotaCorollaAltis.setBrand("Toyota");
		toyotaCorollaAltis.setModel("Corolla Altis");
		toyotaCorollaAltis.setColour("Black");
		//toyotaCorollaAltis.setType("Sedan");  // This will throw compile time error.
		//toyotaCorollaAltis.setFuelType("Petrol"); // This will throw compile time error.
		//toyotaCorollaAltis.setNumberOfSeats(5); // This will throw compile time error.
       	System.out.println("\n" + toyotaCorollaAltis.toString() + "\n");
		
		//Car toyotaFortuner =  (Car) (new Product()); // Successfully compiles but, throws java.lang.ClassCastException.
		
	}
}