package BookStore;

import java.util.Date;
import java.util.ArrayList;

import Individual.*;
import Menu.Driver;
import Menu.DriverB;

public class Transaction {
	public boolean Type; // True for Sale, False for Renting
	Date date;
	public ArrayList<Books> purchasedBooks = new ArrayList<Books>();
	protected Employee emp;
	protected Client customer;
	protected static int serial = 1;
	private String TrxId;

	public Transaction(boolean t, String idClient, String idEmployee) {
		emp = DriverB.getEmployeeById(idEmployee);
		customer = DriverB.getClientById(idClient);
		TrxId = "" + serial + emp.getFirstName().charAt(0) + emp.getFirstName().charAt(1)
				+ customer.getFirstName().charAt(0) + customer.getFirstName().charAt(1);
		serial++;
		Type = t;
		date = new Date();
	}
}
