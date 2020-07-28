import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * This class creates pop up windows to work with trips
 * @author Саша
 *
 */
public class TripsGui extends JFrame{
	JTextField from,to,price;
	JLabel begin,destination,date,payment;
	JPanel panel;
	JButton ADD;
	JComboBox time,certainDate;
	boolean o=true;
	/*
	 * Default constructor of TripsGui
	 * Here user can create trip by filling all fields(required), and then clicking on the 'Додати'(Add) Button
	 */
	TripsGui(){
		 this.setSize(700,700);
		 this.setTitle("Створення поїздки");
		 JComboBox currency = new JComboBox();
		 panel = new JPanel(new GridLayout(0,2));
		 begin = new JLabel();
		 begin.setText("Звідки");
		 begin.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 from = new JTextField();
		 destination = new JLabel();
		 destination.setText("Куди");
		 to = new JTextField();
		 date = new JLabel();
		 date.setText("Місяць");
		 time = new JComboBox(printMonth());
		 time.setSelectedIndex(-1);
		 certainDate = new JComboBox(printDates());
		 payment = new JLabel();
		 payment.setText("Ціна(в гривнях)");
		 price = new JTextField();
		 ADD = new JButton("Додати");
		 JLabel data = new JLabel("День");
		 from.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 to.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 time.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 price.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 date.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 destination.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 payment.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 data.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 panel.add(begin);
		 panel.add(from);
		 panel.add(destination);
		 panel.add(to);
		 panel.add(date);
		 panel.add(time);
		 panel.add(data);
		 panel.add(certainDate);
		 panel.add(payment);
		 panel.add(price);
		 
		 panel.add(ADD);
		 centreWindow(this);
		 this.getContentPane().add(panel);
		 this.setVisible(true);

		 /**
		  * Add new trip (creates new Object in Trips class)
		  * and check if trip is unique(you cannot create two trips with equal from, to and date textField)
		  */
		 ADD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (from.getText().equals("")||to.getText().equals("")||payment.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Заповніть всі поля", "Попередження",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						for (int i = 0; i < Trips.trips.length;i++) {
							if (from.getText().equals(Trips.trips[i].getFrom())&& 
									to.getText().equals(Trips.trips[i].getTo())
									) {
								o = false;
							}
						}
						if (o) {
						int i = Integer.valueOf(price.getText());
						if (certainDate.getSelectedItem()!=null) {
						if (i>=0) {
					Trips trip = new Trips(from.getText(),to.getText(),(String)time.getSelectedItem(),(String)certainDate.getSelectedItem(),Integer.valueOf(price.getText()));
//					writeObjectInFile();

					JOptionPane.showMessageDialog(new JFrame(), "Поїздку додано!", "Інформаційне вікно",
							JOptionPane.INFORMATION_MESSAGE);
					from.setText("");
					to.setText("");
					time.setSelectedIndex(-1);
					certainDate.setSelectedIndex(-1);
					price.setText("");
						}
						else {
							JOptionPane.showMessageDialog(new JFrame(), "Введіть додатнє число!", "Попередження!",
									JOptionPane.WARNING_MESSAGE);
						}
						}else {
							JOptionPane.showMessageDialog(new JFrame(), "Введіть нормальне число!", "Попередження!",
									JOptionPane.WARNING_MESSAGE);
						}
						}else {
							JOptionPane.showMessageDialog(new JFrame(), "Поїздка вже створена!", "Попередження!",
									JOptionPane.WARNING_MESSAGE);
						}
				}catch(NumberFormatException j) {
					JOptionPane.showMessageDialog(new JFrame(), "Введіть нормальну ціну!", "Попередження!",
							JOptionPane.WARNING_MESSAGE);
				}
					}
				
			}
		});
	}

	private void writeObjectInFile() {
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream("save.ser");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(outputStream);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// сохраняем игру в файл
		try {
			objectOutputStream.writeObject(Trips.trips);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//закрываем поток и освобождаем ресурсы
		try {
			objectOutputStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String[] printDates() {
		String dates[] = new String[32];
		for (int i = 1; i < dates.length;i++) {
			dates[i] = i + "";
		}
		return dates;
	}
	private String[] printMonth() {
		String month[] = {"Січень","Лютий","Березень","Квітень","Травень","Червень",
				"Липень","Серпень","Вересень","Жовтень","Листопад","Грудень"};
		return month;
	}
	/**
	 * Second constructor. There is 2 cases
	 * When person in MainWindow click 'Show allTrips' its case 1
	 * Deleting Button transfer program in case 2
	 * 
	 * @param j
	 */
	TripsGui(int j){
		switch (j) {
		case 1:{
		this.setSize(700,700);
		 String[][] prodTableString = new String[Trips.trips.length][4];
	        for(int i = 0;i<prodTableString.length;i++){
	            prodTableString[i][0] = Trips.trips[i].getMonth() + ", " + Trips.trips[i].getCertainDate();
	            prodTableString[i][1] = Trips.trips[i].getFrom();
	            prodTableString[i][2] = Trips.trips[i].getTo();
	            prodTableString[i][3] = Trips.trips[i].getPrice()+"";
	        }
	        String[] columnsHeader = new String[]{"Дата","Звідки","Куди","Ціна"};
	        JTable table = new JTable(prodTableString,columnsHeader);
	        table.setBounds(30,40,200,300);
	        table.setEnabled(false);
	        Font font = new Font("Bahnshift", Font.PLAIN, 15);
	        table.setFont(font);
	        JScrollPane sp = new JScrollPane(table);
	        
	        
	        this.add(sp);
	        centreWindow(this);}
		break;
		case 2:{
			this.setSize(700,700);
			JPanel panel = new JPanel();
			GridLayout layout = new GridLayout(0, 1);
			panel.setLayout(layout);
			layout.setVgap(40);
			JComboBox b1 = new JComboBox(printAllTrips());
			panel.add(b1);			
			JButton deleteButton = new JButton("Видалити поїзку");
			panel.add(new JLabel(" "));
			panel.add(deleteButton);
			centreWindow(this);
			this.getContentPane().add(panel);
			this.setVisible(true);
			/**
			 * Deletes trip
			 */
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (Trips.passengers.length!=0) {
						int counter = 0;
						for (int i = 0; i < Trips.passengers.length;i++) {
							if (Trips.passengers[i].getGroup().equals((String)b1.getSelectedItem())) {
								counter++;
							}
						}
				        Passengers[] anotherGoodArray = new Passengers[Trips.passengers.length-counter];
						for (int i = 0,k=0;i<Trips.passengers.length;i++) {
							if (Trips.passengers[i].getGroup().equals((String)b1.getSelectedItem())) {
								continue;
							}
							anotherGoodArray[k++] = Trips.passengers[i]; 
						}
						Trips.passengers = anotherGoodArray;
						writePassengersInFile();
					}
					for (int i = 0; i < Trips.trips.length;i++) {
						if ((Trips.trips[i].getFrom() +" - "+ Trips.trips[i].getTo()
								+ ", " +Trips.trips[i].getMonth()).equals((String)b1.getSelectedItem())) {
							deleteTrip((String)b1.getSelectedItem());
							JOptionPane.showMessageDialog(new JFrame(), "Поїздка видалена!", "Повідомлення про видалення!",
									JOptionPane.INFORMATION_MESSAGE);
							writeTrip();
//							writeObjectInFile();
						}
					}
				}
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
	 * @return
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
				/**
				 * Write trip in file
				 */
				private void writeTrip() {
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
				 * Write in file info about certain trip in pretty way :)
				 * @return
				 */
				private String printTrips() {
					String result="";
					for (int i = 0; i < Trips.trips.length;i++) {
						result += "\nЗвідки: " + Trips.trips[i].getFrom() + " Куди: " + Trips.trips[i].getTo()
								+ " Дата: " + Trips.trips[i].getMonth() + " Ціна(в гривнях): " + Trips.trips[i].getPrice();
					}
					return result;
				}
				/**
				 * Delete Trip
				 * @param selectedItem
				 */
				private void deleteTrip(String selectedItem) {
					Trips[] anotherArray = new Trips[Trips.trips.length - 1]; 
					
					for (int i = 0, k = 0; i < Trips.trips.length; i++) { 
			            if ((Trips.trips[i].getFrom() +" - "+ Trips.trips[i].getTo()
								+ ", " +Trips.trips[i].getMonth()).equals(selectedItem)) { 
			                continue; 
			            } 
			            anotherArray[k++] = Trips.trips[i]; 
			        }
			        Trips.trips = anotherArray;
				}
			});
		}
		
	        }
	}
	/**
	 * Add to comboBox array of trips
	 * @return
	 */
	private String[] printAllTrips() {
		String[] result = new String[Trips.trips.length];
		for(int i = 0; i < Trips.trips.length;i++) {
				result[i] = Trips.trips[i].getFrom() +" - "+ Trips.trips[i].getTo()
						+ ", " +Trips.trips[i].getMonth();
				
			}
		return result;
		}
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}
