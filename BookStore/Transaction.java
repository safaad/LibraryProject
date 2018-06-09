package BookStore;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import Individual.*;
import Menu.Driver;

public class Transaction { // True for Sale, False for Renting
	Date date;
	public ArrayList<Books> purchasedBooks = new ArrayList<Books>();
	protected Employee emp;
	protected Client customer;
	protected static int serial = 1;
	private String TrxId;
	private double totalMoney;

	public Transaction(Client c, Employee emp2) {
		emp = emp2;
		customer = c;
		date = Calendar.getInstance().getTime();
		TrxId = ""  + emp.getFirstName().charAt(0) + customer.getFirstName().charAt(0) + serial;
		serial++;
	}
	
	public static void setSerial(int serial2) {
		serial = serial2;
	}
	
	public static int getSerial() {
		return serial;
	}
	
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public String toString() {
		String s = "";
		s+= "Transaction " + TrxId +" on " + date + "\n";
		s+= "-----------\n";
		s+= "Employee: " + ((Person) emp).toString() + "\n";
		s+= "Client:" + customer.getId()+ "\n";
		s+= "---------------------------------\n";
		s+= "List of bought books: \n";
		for(int i = 0; i < purchasedBooks.size(); i++) {
			if(purchasedBooks.get(i) instanceof Sale) {
				s+= (Sale) purchasedBooks.get(i);
				s+= "\n";
			}
		}
		s+= "List of rented books: \n";
		for(int i = 0; i < purchasedBooks.size(); i++) {
			if(purchasedBooks.get(i) instanceof ForRent) {
				s+= (ForRent) purchasedBooks.get(i);
				s+= "\n";
			}
		}
		s+= "---------------------------------\n";
		s+= "Total amount paid: " + totalMoney + "\n";
		if(customer.flag10) {
			s+= "Total discount: 10%\n";
		}
		else
			s+= "Total discount: 20%\n";
		s+= "---------------------------------\n";
		return s;
	}
}
