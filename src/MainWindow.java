import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;
/**
 * This class show user main window of this program
 * @author Саша
 *
 */
public class MainWindow extends JFrame{
	private JMenuBar menuBar;
	private JMenu trips,passengers,income;
	private JMenuItem createTrip,printAllTrips,createPassengers,allPassengers,deleteTrip,saveFile;
	JLabel background;
	JTextArea textArea;
	/**
	 * Default constructor of the Main Window
	 * It creates panel, and add elements on to it
	 */
	MainWindow(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("back.jpg");
		background = new JLabel("",img,JLabel.CENTER);
		ImageIcon icon = new ImageIcon("icon.png");
		frame.setIconImage(icon.getImage());


		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);


		frame.setTitle("Бухгалтерія поїздок");
		menuBar = new JMenuBar();
		JPanel panel = new JPanel();
		trips = new JMenu("Поїздки");
		trips.setForeground(Color.BLUE);
		createTrip = new JMenuItem("Створити");
		printAllTrips = new JMenuItem("Всі поїздки");
		deleteTrip = new JMenuItem("Видалити поїздку");
		JMenuItem readTrip = new JMenuItem("Зчитати поїздку");
		JMenuItem readPassengers = new JMenuItem("Зчитати пасажирів");
		createPassengers = new JMenuItem("Додати пасажира");
		allPassengers = new JMenuItem("Всі пасажири");
		saveFile = new JMenuItem("Зберегти");
		JMenuItem exit = new JMenuItem("Вийти");
		createTrip.setForeground(Color.BLUE);
		printAllTrips.setForeground(Color.BLUE);
		deleteTrip.setForeground(Color.BLUE);
		readTrip.setForeground(Color.BLUE);
		saveFile.setForeground(Color.blue);
		exit.setForeground(Color.RED);
		createPassengers.setForeground(Color.MAGENTA);
		allPassengers.setForeground(Color.MAGENTA);
		readPassengers.setForeground(Color.MAGENTA);
		trips.add(createTrip);
		trips.add(printAllTrips);
		trips.add(deleteTrip);
		trips.add(readTrip);
		trips.add(saveFile);
		trips.add(exit);
		passengers = new JMenu("Пасажири");
		passengers.setForeground(Color.MAGENTA);
		passengers.add(createPassengers);
		passengers.add(allPassengers);
		passengers.add(readPassengers);
		income = new JMenu("Дохід");
		income.setForeground(Color.green);
		JMenuItem byTrips = new JMenuItem("За поїздками");
		JMenuItem byMonth = new JMenuItem("За місяцями");
		byTrips.setForeground(Color.green);
		byMonth.setForeground(Color.green);
		income.add(byTrips);
		income.add(byMonth);
		createTrip.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		printAllTrips.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		deleteTrip.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		readTrip.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		saveFile.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		createPassengers.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		allPassengers.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		readPassengers.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		byTrips.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		byMonth.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		exit.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		menuBar.add(trips);
		menuBar.add(passengers);
		menuBar.add(income);
		trips.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		passengers.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		passengers.setHorizontalAlignment(JLabel.CENTER);
		income.setFont(new Font("Bahnschrift", Font.BOLD, 42));
		income.setHorizontalAlignment(JLabel.CENTER);
		frame.setJMenuBar(menuBar);
		frame.add(background);
		frame.setVisible(true);
		centreWindow(frame);
		/**
		 * When user clicks add Trip - it creates new Object in class TripsGui
		 * than program opens window, where user can describe his trip
		 */
		createTrip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TripsGui trip = new TripsGui();
				
			}
		});
		/**
		 * This displays grid with all available trips
		 */
		printAllTrips.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TripsGui allTrips = new TripsGui(1);
				allTrips.setVisible(true);
			       
			        
				
			}
		});
		/**
		 * Clicking on this button, user can delete his trip
		 */
		deleteTrip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Trips.trips.length!=0) {
					
					TripsGui deleteTrip = new TripsGui(2);
					
					}else {
						JOptionPane.showMessageDialog(new JFrame(), "Створіть поїдку!", "Попередження!",
								JOptionPane.WARNING_MESSAGE);
					}
				
			}
		});
		readTrip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Trips.trips.length==0) {
				JOptionPane.showMessageDialog(new JFrame(), "Поїздки зчитані!", "Інформація!",
						JOptionPane.INFORMATION_MESSAGE);
				FileInputStream fileInputStream = null;
				try {
					fileInputStream = new FileInputStream("save.ser");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			       ObjectInputStream objectInputStream = null;
				try {
					objectInputStream = new ObjectInputStream(fileInputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			       try {
					Trips.trips = (Trips[]) objectInputStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Створіть поїдку!", "Попередження!",
						JOptionPane.WARNING_MESSAGE);
			}
				}
		});

		saveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeTripsInFile();
				writePassengersInFile();
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});

		createPassengers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Trips.trips.length!=0) {
					
				PassengerGui passenger = new PassengerGui();
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Створіть поїдку!", "Попередження!",
							JOptionPane.WARNING_MESSAGE);
				}			
			}
		});
		/**
		 * Displays grid with all available Passengers 
		 */
		allPassengers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Trips.trips.length!=0) {
				PassengerGui allPassengers = new PassengerGui(1);
				allPassengers.setVisible(true);
			       
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Створіть поїдку!", "Попередження!",
							JOptionPane.WARNING_MESSAGE);
				}	
				
			}
		});
		/**
		 * Here user can see all passenger of certain trip
		 */
		byTrips.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Trips.trips.length!=0) {
				Incomes income1 = new Incomes();
				
			}else {
				JOptionPane.showMessageDialog(new JFrame(), "Створіть поїдку!", "Попередження!",
						JOptionPane.WARNING_MESSAGE);
			}	
			}
		});
		byMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Trips.trips.length!=0) {
				Incomes income1 = new Incomes(1);
				
			}else {
				JOptionPane.showMessageDialog(new JFrame(), "Створіть поїдку!", "Попередження!",
						JOptionPane.WARNING_MESSAGE);
			}	
			}
		});
		readPassengers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(new JFrame(), "Пасажири зчитані!", "Інформація!",
						JOptionPane.INFORMATION_MESSAGE);
				FileInputStream fileInputStream = null;
				try {
					fileInputStream = new FileInputStream("savePassengers.ser");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			       ObjectInputStream objectInputStream = null;
				try {
					objectInputStream = new ObjectInputStream(fileInputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			       try {
					Trips.passengers = (Passengers[]) objectInputStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Centers the window
	 * @param frame
	 */
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
	private void writePassengersInFile() {
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
	private void writeTripsInFile() {
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
}
