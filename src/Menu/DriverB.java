package Menu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import BookStore.*;
import Individual.*;
import java.time.LocalDateTime ;
public class DriverB {
	public static ArrayList<Client> clients = new ArrayList<Client>();
	public static Employee empAm, empPm;
	public static ArrayList<ForRent> rentedBooks = new ArrayList<ForRent>();
	public static ArrayList<Books> books = new ArrayList<Books>();
	public static ArrayList<Transaction> transaction = new ArrayList<Transaction>();
	public Integer k;
	public static Adminstrator admin=new Adminstrator("X","123");
	public static Scanner in = new Scanner(System.in);
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

		if (empAm.getID().equals(id))
			return empAm;
		if (empPm.getID().equals(id))
			return empPm;

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
		System.out.println(LocalDateTime.now());
		
	
	}
	
	

}
