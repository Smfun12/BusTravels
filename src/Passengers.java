import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
/**
 * This class creates Passengers
 * @author Саша
 *
 */
public class Passengers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String phoneNumber;
	String destination;
	int	 quantity;
	int price;
	int income;
	/**
	 * Default constructor 
	 * @param name gets passengers name
	 * @param phoneNumber gets phone number of passengers
	 * @param destination gets destination
	 * @param quantity get quantity
	 * @param price get price
	 * @param group get certain trip
	 */
	Passengers(String name, String phoneNumber, String destination, int quantity, int price, String group){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.destination = destination;
		this.quantity = quantity;
		this.price = price;
		this.group = group;
	}
	public int getIncome() {
		return quantity*price;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	String group;
	
	public String getGroup() {
		return group;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getDestination() {
		return destination;
	}

	public int getQuantity() {
		return quantity;
	}

	/**
	 * Writes info about passengers in file in specific directory passengers/
	 */
	public void writePassengersInFile() {
		FileWriter f1;
		try {
			f1 = new FileWriter("passengers/Passengers.txt");
			f1.write(printPassengers());
			f1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Make displaying info about passengers in file nicely
	 * @return result
	 */
	private String printPassengers() {
		String result="";
		for (int i = 0; i < Trips.passengers.length;i++) {
			result += "\nІм'я: " +Trips.passengers[i].getName() + " Телефон: " + Trips.passengers[i].getPhoneNumber()
					+ " Напрямок: " + Trips.passengers[i].getDestination() + " Кількість: " + Trips.passengers[i].getQuantity()
					+" Ціна: " + Trips.passengers[i].getPrice() + 
					", Поїздка: " + Trips.passengers[i].getGroup();
		}
		return result;
	}
}
