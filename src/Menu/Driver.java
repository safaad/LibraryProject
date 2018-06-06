package Menu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import BookStore.*;
import Individual.*;

public class Driver {
	public static ArrayList<Client> clients = new ArrayList<Client>();
	public static ArrayList<Employee> emp = new ArrayList<Employee>();
	public static ArrayList<ForRent> rentedBooks = new ArrayList<ForRent>();
	public static ArrayList<Books> books = new ArrayList<Books>();
	public static ArrayList<Transaction> transaction = new ArrayList<Transaction>();
	public Integer k;
	@SuppressWarnings("deprecation")
	public static Books getBookByTitle(String title) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(title))
				return books.get(i);
		}
		return null;
	}

	public static Books getBookByISBN(int ISBN) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getISBN() == ISBN)
				return books.get(i);
		}
		return null;
	}

	public static Employee getEmployeeById(String id) {
		for (int i = 0; i < emp.size(); i++) {
			if (emp.get(i).getID().equals(id))
				return emp.get(i);
		}
		return null;
	}
	public static Client getClientById(String id) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getId().equals(id))
				return clients.get(i);
		}
		return null;
	}

	public static void main(String[] args) {

	}

}
