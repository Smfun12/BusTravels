import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ComboBoxModel;
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
 * This class displays Income of certain trip
 * @author Саша
 *
 */
public class Incomes extends JFrame{
	static int totalQuantityOfPassengers;
	JTextField costs;
	JLabel income,spendings;
	JPanel panel;
	JButton ADD;
	JComboBox combo;
	static String result;
	
	int profit;
	/**
	 * Default income constructor
	 */
	Incomes(){
		
			this.setSize(400,400);
			
			centreWindow(this);
			panel = new JPanel(new GridLayout(0,1));
			combo = new JComboBox(printAllTrips());
			JButton showIncome = new JButton("Показати дохід");
			JButton showProfit = new JButton("Покахати прибуток");
			costs = new JTextField();
			income = new JLabel();
			
			spendings = new JLabel();
			spendings.setText("Витрати");
			costs = new JTextField();
			
			panel.add(combo);
			panel.add(income);
			panel.add(showIncome);
			panel.add(spendings);
			panel.add(costs);
			panel.add(showProfit);
			/**
			 * Show income by certain trip
			 */
			showIncome.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					income.setText("Дохід: " + countIncome());
					
				}
			});
			/**
			 * Show profit (income - costs)
			 */
			showProfit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					try {
					Trips temp =findTrips((String)combo.getSelectedItem());
					temp.setCosts(Integer.valueOf(costs.getText()));
					temp.setIncome(countIncome());
					profit = countIncome()-Integer.valueOf(costs.getText());
					temp.setProfit(profit);
					JOptionPane.showMessageDialog(new JFrame(), "Прибуток: "+profit, "Прибуток!",
							JOptionPane.INFORMATION_MESSAGE);
					writeProfitinFile();
					}catch(NumberFormatException j) {
						JOptionPane.showMessageDialog(new JFrame(), "Введіть витрати! ", "Прибуток!",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				/**
				 * Find certain trip
				 * @param selectedItem gets selectedItem
				 * @return null
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
				
			this.getContentPane().add(panel);
			this.setVisible(true);
		}
		

			
		
	public Incomes(int i) {
		this.setSize(400,400);
		
		centreWindow(this);
		panel = new JPanel(new GridLayout(0,1));
		combo = new JComboBox(printMonth());
		JButton showIncome = new JButton("Показати прибуток за місяць");
		costs = new JTextField();
		income = new JLabel();
		
		
		
		panel.add(combo);
		panel.add(income);
		panel.add(showIncome);
		showIncome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				income.setText("Дохід: " + countIncomeByMonth());
				writeProfitByMonthInFile((String)combo.getSelectedItem());
			}

			private void writeProfitByMonthInFile(String item) {
				 FileWriter f1;
				 try {
					f1 = new FileWriter("Profit_By_Month.txt");
					f1.write(writeProfitByMonth(item));
					f1.close();
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			private String writeProfitByMonth(String item) {
				
				int result1 = 0;
				result += "\n"+item;
				for (int i = 0; i < Trips.trips.length;i++) {
					if (Trips.trips[i].getMonth().equals(item)) {
					
					result += "\nПоїздка: "+ Trips.trips[i].getFrom()+" - " +
				Trips.trips[i].getTo() + 
									", прибуток: " + Trips.trips[i].getProfit() ;
							result1 += Trips.trips[i].getProfit();		
						}
				}
				result += "\nВсього: " + result1;
				return result;
				
			}

			private int countIncomeByMonth() {
				int result = 0;
				for (int i = 0; i < Trips.trips.length;i++) {
					if (Trips.trips[i].getMonth().equals((String)combo.getSelectedItem())){
						result += Trips.trips[i].getProfit();
					}
				}
				return result;
			}
		});
		this.getContentPane().add(panel);
		this.setVisible(true);
	}




	private String[] printMonth() {
		String month[] = {"Січень","Лютий","Березень","Квітень","Травень","Червень",
				"Липень","Серпень","Вересень","Жовтень","Листопад","Грудень"};
		return month;
	}



	/**
	 * Counts income by multiplying passenger quantity and price
	 * @return
	 */
	private int countIncome() {
		int income = 0;
		for (int i = 0;i<Trips.passengers.length;i++) {
			if ((Trips.passengers[i].getGroup().equals(combo.getSelectedItem()))) {
				income +=  Trips.passengers[i].getQuantity()*Trips.passengers[i].getPrice();
				
			}
		}
		return income;
	}


	/**
	 * Writes in file profit of certain trip
	 */
	private void writeProfitinFile() {
		 FileWriter f1;
		 try {
			f1 = new FileWriter("Profit.txt");
			f1.write(writeProfit());
			f1.close();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Display info about Profit in file in pretty way
	 * @return
	 */
	private String writeProfit() {
		String result="";
		for (int i = 0; i < Trips.trips.length;i++) {
			result += "\nПоїздка: "+ Trips.trips[i].getFrom()+" - " +
		Trips.trips[i].getTo() + 
							", дохід: " + Trips.trips[i].getIncome() + 
							", витрати: " +  Trips.trips[i].getCosts() +
							", прибуток: " + Trips.trips[i].getProfit();
				}
			
		
		return result;
	}
	/**
	 * Add to comboBox array of available trips
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
	
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}
