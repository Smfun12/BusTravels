import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
 * This class creates windows to work with passengers
 * @author Саша
 *
 */
public class PassengerGui extends JFrame{
	JTextField name,number,time,quantity,value;
	JLabel begin,phoneNumber,destination,payment,price;
	JPanel panel;
	JButton ADD;
	/**
	 * Default passenger constructor
	 */
	PassengerGui(){
		 this.setSize(700,700);
		 JComboBox trips2 = new JComboBox(printAllTrips());
		 panel = new JPanel(new GridLayout(0,2));
		 begin = new JLabel();
		 begin.setText("Ім'я");
		 name = new JTextField();
		 phoneNumber = new JLabel();
		 phoneNumber.setText("Телефон");
		 number = new JTextField();
		 destination = new JLabel();
		 destination.setText("Напрямок");
		 time = new JTextField();
		 payment = new JLabel();
		 payment.setText("Кількість");
		 price = new JLabel("Ціна");
		 value = new JTextField();
		 quantity = new JTextField();
		 ADD = new JButton("Додати");
		 JLabel trips1 = new JLabel();
		 trips1.setText("Поїздки");
		 name.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 number.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 time.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 value.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 quantity.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 trips1.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 begin.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 phoneNumber.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 destination.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 payment.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 price.setFont(new Font("Bahnschrift", Font.BOLD, 25));
		 panel.add(trips1);
		 panel.add(trips2);
		 panel.add(begin);
		 panel.add(name);
		 panel.add(phoneNumber);
		 panel.add(number);
		 panel.add(destination);
		 panel.add(time);
		 panel.add(payment);
		 panel.add(quantity);
		 panel.add(price);
		 panel.add(value);
		 panel.add(ADD);
		 
		 centreWindow(this);
		 this.getContentPane().add(panel);
		 this.setVisible(true);
		 /**
		  * Add passenger to Trips.passengers array
		  */
		 ADD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if (name.getText().equals("")||number.getText().equals("")||time.getText().equals("")||payment.getText().equals("")||value.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Заповніть всі поля", "Попередження",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						
						int i = Integer.valueOf(quantity.getText());
						int j = Integer.valueOf(value.getText());
						
						if (i>=0 && j>=0) {
					Trips trip = findTrips((String)trips2.getSelectedItem());
					Passengers pass = new Passengers(name.getText(),number.getText(),
							time.getText(),Integer.valueOf(quantity.getText()),
							Integer.valueOf(value.getText()),(String)trips2.getSelectedItem());
					trip.addPassenger(pass);
					pass.writePassengersInFile();
//					writeObjectInFile();

					JOptionPane.showMessageDialog(new JFrame(), "Пасажира додано!", "Інформаційне вікно",
							JOptionPane.INFORMATION_MESSAGE);
					name.setText("");
					number.setText("");
					quantity.setText("");
					value.setText("");
					time.setText("");
						}
						else {
							JOptionPane.showMessageDialog(new JFrame(), "Введіть додатнє число!", "Попередження!",
									JOptionPane.WARNING_MESSAGE);
						}
					
				}catch(NumberFormatException j) {
					JOptionPane.showMessageDialog(new JFrame(), "Введіть нормальну кількість!", "Попередження!",
							JOptionPane.WARNING_MESSAGE);
				}
					}
				
			
				}
			/**
			 * Find certain trip, and then creates passenger in this trip
			 * @param selectedItem
			 * @return
			 */
			private Trips findTrips(String selectedItem) {
				for (int i = 0; i < Trips.trips.length; i++) {
					if ((Trips.trips[i].getFrom() +" - "+ Trips.trips[i].getTo() + ", " + Trips.trips[i].getMonth() + " "+Trips.trips[i].getCertainDate()).equals(selectedItem)) {
						return Trips.trips[i];
					}
				}
				return null;
			}
		});
	}

	private void writeObjectInFile() {
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream("savePassengers.ser");
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
			objectOutputStream.writeObject(Trips.passengers);
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


	/**
	 * Add trip in ComboBox
	 * @return
	 */
	private String[] printAllTrips() {
		String[] result = new String[Trips.trips.length];
		for(int i = 0; i < Trips.trips.length;i++) {
				result[i] = Trips.trips[i].getFrom() +" - "+ Trips.trips[i].getTo()
						+ ", " +Trips.trips[i].getMonth() +" "+ Trips.trips[i].getCertainDate();
				
			}
		return result;
	}
	/**
	 * Constructor, that launches when user want to see all passengers
	 * it created table with available passengers 
	 * @param j
	 */
	PassengerGui(int j){
		
		this.setSize(600,400);
		 String[][] prodTableString = new String[Trips.passengers.length][6];
	        for(int i = 0;i<prodTableString.length;i++){
	            prodTableString[i][0] = Trips.passengers[i].getName();
	            prodTableString[i][1] = Trips.passengers[i].getPhoneNumber();
	            prodTableString[i][2] = Trips.passengers[i].getGroup();
	            prodTableString[i][3] = Trips.passengers[i].getDestination();
	            prodTableString[i][4] = Trips.passengers[i].getQuantity()+"";
	            prodTableString[i][5] = Trips.passengers[i].getPrice()+"";
	            
	          
	             
	        }
	        String[] columnsHeader = new String[]{"Ім'я","Телефон","Поїздка","Напрямок","Кількість","Ціна"};
	        JTable table = new JTable(prodTableString,columnsHeader);
	        table.setBounds(30,40,200,300);
	        table.setEnabled(false);
	        Font font = new Font("Verdana", Font.PLAIN, 15);
	        table.setFont(font);
	        JScrollPane sp = new JScrollPane(table);
	        
	        
	        this.add(sp);
	        centreWindow(this);
	}
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}
