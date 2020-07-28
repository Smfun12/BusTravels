import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
/**
 * This class creates trips
 * @author Саша
 *
 */
public class Trips implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String from;
	String to;
	String month;
	String certainDate;
	int price;
	
	int costs;
	int income;
	int profit;
	/**
	 * Default constructor
	 * @param from departure
	 * @param to arrival
	 * @param month month
	 * @param certainDate date of month
	 * @param price price
	 */
	public Trips(String from, String to, String month, String certainDate, int price) {
		this.from = from;
		this.to = to;
		this.month = month;
		this.certainDate = certainDate;
		this.price = price;
		addTrip(this);
		writeInFile();
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getIncome() {
		return income;
	}
	public int getCosts() {
		return costs;
	}
	public void setCosts(int costs) {
		this.costs = costs;
	}
	public int getPrice() {
		return price;
	}

	public String getMonth() {
		return month;
	}

	public String getCertainDate() {
		return certainDate;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	static Trips[] trips = {};
	static Passengers[] passengers = {};
	
	/**
	 * Write trips in file in specific directory trips/
	 */
	private void writeInFile() {
		FileWriter f1;
		try {
			f1 = new FileWriter("trips/Trips.txt");
			f1.write(printTrips());
			f1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Display info about trips in file in pretty way
	 * @return result
	 */
	private String printTrips() {
		String result="";
		for (Trips trip : trips) {
			result += "\nЗвідки: " + trip.getFrom() + " Куди: " + trip.getTo()
					+ " Місяць: " + trip.getMonth() +
					"День: " + trip.getCertainDate() + "Ціна(в гривнях): " +
					trip.getPrice();
		}
		return result;
	}
	/**
	 * Add trip to Trips array
	 * @param trip gets Trip
	 */
	private void addTrip(Trips trip) {
		Trips[] temp = new Trips[trips.length+1];
		System.arraycopy(trips, 0, temp, 0, trips.length);
		temp[trips.length] = trip;
		trips = temp;
		
	} 
	/**
	 * Add passenger to Trips.passengers array
	 * @param pass gets pass
	 */
	public void addPassenger(Passengers pass) {
		Passengers[] temp = new Passengers[passengers.length+1];
		System.arraycopy(passengers, 0, temp, 0, passengers.length);
		temp[passengers.length] = pass;
		passengers = temp;
		
	}

}
