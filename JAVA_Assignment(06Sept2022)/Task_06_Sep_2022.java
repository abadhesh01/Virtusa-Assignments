class Computer
{
   String brand;
   String model;
   String processor;
   String gpu;
   String RAM;
   String storage;
}

class Parents
{
   String childName;
   String fatherName;
   String motherName;
   boolean childIsAlive;
   boolean fatherIsAlive;
   boolean motherIsAlive;
}

class Car
{
   String brand;
   String model;
   String type;
   String colour;
   boolean isSportsVariant;
}

class Bicycle
{
   String brand;
   String model;
   String type;
   String colour;
   
   public static void main(String []args)
   {
	   System.out.println("Bicycle Templet");
   }
}

class SIM
{
  String dailingCode;
  String number;
  String provider;
  
  public static void main(String []args)
   {
	   System.out.println("SIM Templet");
   }
}

class Participant
{
  long id;
  String name;
  String email;
  String phone;
}

class Meeting
{
  long id;
  String link;
  java.util.Date date;
  String time;
  java.util.List<Participant> participants = new java.util.ArrayList<Participant>();
}

class Product 
{
  long id;
  int price;
  String name;
  java.util.Date manufacturingDate;
}

class Order
{
  long id;
  java.util.Date date;
  String time;
  java.util.List<Product> products = new java.util.ArrayList<Product>();
}

public class Task_06_Sep_2022
{
  public static void main (String []args)
  {

     Computer myComputer = new Computer();
     myComputer.brand = "Dell";
     myComputer.model = "Inspiron-3576";
     myComputer.processor = "Intel core i5 8th Gen";
     myComputer.gpu = "AMD Radeon 520";
     myComputer.RAM = "8 GB";
     myComputer.storage = "256 GB";
     System.out.println("\n\n\nMy Computer Specifications\n--------------------------\nBrand: "+myComputer.brand+"\nModel: "+myComputer.model+"\nProcessor: "+myComputer.processor+"\nGPU: "+myComputer.gpu+"\nRAM: "+myComputer.RAM+"\nStorage: "+myComputer.storage);
     
     Parents myParents = new Parents();
     myParents.childName = "Abadhesh Mishra";
     myParents.fatherName = "Biswajayee Mishra";
     myParents.motherName = "Samita Acharya";
     myParents.childIsAlive = true;
     myParents.fatherIsAlive = true;
     myParents.motherIsAlive = true;
     System.out.println("\n\nMy Parents Info\n---------------\nChild Name: "+myParents.childName+"\nFather Name: "+myParents.fatherName+"\nMother Name: "+myParents.motherName+"\nChild is alive: "+myParents.childIsAlive+"\nFather is alive: "+myParents.fatherIsAlive+"\nMother is alive: "+myParents.motherIsAlive);

     Car myFamilyCar = new Car();
     myFamilyCar.brand = "Maruti Suzuki";
     myFamilyCar.model = "WagonR LXI";
     myFamilyCar.type = "Hatch Back";
     myFamilyCar.colour = "Red";
     myFamilyCar.isSportsVariant = false;
     System.out.println("\n\nMy Family Car Specifications\n----------------------------\nBrand: "+myFamilyCar.brand+"\nModel: "+myFamilyCar.model+"\nType: "+myFamilyCar.type+"\nColour: "+myFamilyCar.colour+"\nIs a sports car: "+myFamilyCar.isSportsVariant);
    
     Bicycle myBike = new Bicycle();
     myBike.brand = "Raleigh";
     myBike.model = "Gallop 27.5";
     myBike.type = "Cross Country Mountain Bike";
     myBike.colour = "Black with Red graphics";
     System.out.println("\n\nMy Bicycle Specifications\n-------------------------\nBrand: "+myBike.brand+"\nModel: "+myBike.model+"\nType: "+myBike.type+"\nColour: "+myBike.colour);

     SIM mySIM = new SIM();
     mySIM.dailingCode = "+91";
     mySIM.number = "96681 94464";
     mySIM.provider = "Reliance Jio";
     System.out.println("\n\nMy SIM Info\n-----------\nDailing Code: "+mySIM.dailingCode+"\nNumber: "+mySIM.number+"\nprovider: "+mySIM.provider);
     
     Meeting meeting = new Meeting();
     
     Order order = new Order();

     System.out.println("\n\n");
  }
}