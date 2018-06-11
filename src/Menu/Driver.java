package Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import BookStore.*;
import Individual.*;
public class Driver {
	public static ArrayList<Client> clients = new ArrayList<Client>();
	public static Employee empAm=null, empPm=null;
	public static ArrayList<ForRent> rentedBooks = new ArrayList<ForRent>();
	public static ArrayList<Books> books = new ArrayList<Books>();
	public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	public Integer k;
	public static Adminstrator admin=new Adminstrator("admin","admin");
	public static Scanner scan = new Scanner(System.in);
	public static files LibraryFiles = new files();
	
	public static void PrintTransactions(){
		boolean flag = false;
		for(Transaction trx : transactions){
			System.out.println(trx);
			System.out.println("########################");
			flag=true;
		}
		if(!flag) {
			System.out.println("No transactions yet!");
		}
	}
	
	public static void PrintListOfBooks(){
		boolean flag = false;
		System.out.println("List of books already rented: ");
		for(Books b : books){
			if(b instanceof ForRent){
				System.out.println((ForRent)b);
				flag = true;
			}
		}
		if(flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No books available for rent");
		flag = false;
		System.out.println("List of books for sale: ");
		for(Books b2 : books){
			if(b2 instanceof Sale){
				System.out.println((Sale)b2);
				flag = true;
			}
		}
		if(flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No books available for sale");
		System.out.println("End of list\n");
	}
	
	public static void ChangeUsername(){
		String k = "";
		System.out.println("Please enter new username: ");
		k = scan.nextLine();
		admin.setUsername(k);
	}
	
	public static void ChangePassword(){
		String k = "";
		System.out.println("Please enter new password: ");
		k = scan.nextLine();
		admin.setPassword(k);
	}
	
	public static void AddBook(){
		String type = "", title = "", author = "";
		int isbn = 0, targetAge = 0;
		System.out.println("Please enter what type of Book (Rent/Sale)");
		scan.nextLine();
		type = scan.nextLine();
		if(type.equalsIgnoreCase("rent")){
			System.out.println("Please enter title of the book: ");
			title = scan.nextLine();
			//scan.nextLine();
			System.out.println("Please enter author: ");
			author = scan.nextLine();
			System.out.println("Please enter ISBN: ");
			//scan.nextLine();
			try {
				isbn = scan.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Wrong input!\n");
				return;
			}
			System.out.println("Please enter target age: ");
			//scan.nextLine();
			targetAge = scan.nextInt();
			if(books.contains(new ForRent(title, author, isbn, targetAge))){
				System.out.println("This book is already inside the library");
				return;
			}
			books.add(new ForRent(title, author, isbn, targetAge));
		}
		else if(type.equalsIgnoreCase("Sale")) {
			System.out.println("Please enter title of the book: ");
			title = scan.nextLine();
			//scan.nextLine();
			System.out.println("Please enter author: ");
			author = scan.nextLine();
			System.out.println("Please enter ISBN: ");
			//scan.nextLine();
			isbn = scan.nextInt();
			System.out.println("Please enter target age: ");
			//scan.nextLine();
			targetAge = scan.nextInt();
			System.out.println("Please enter the price: ");
			double price = scan.nextDouble();
			System.out.println("Please enter the net price: ");
			double netPrice = scan.nextDouble();
			if(books.contains(new Sale(title, author, isbn, targetAge, price, netPrice))){
				System.out.println("This book is already inside the library");
				return;
			}
			books.add(new Sale(title, author, isbn, targetAge, price, netPrice));
		}
		else
			return;
	}
	
	public static void RemoveBook(){
		int isbn= 0;
		
		PrintListOfBooks();
		System.out.println("What book do you want to remove?\nPlease enter the ISBN");
		isbn = scan.nextInt();
		if(getBookByISBN(isbn)==null ||!books.contains(getBookByISBN(isbn))){
			System.out.println("Invalid book");
			return;
		}
		books.remove(getBookByISBN(isbn));
	}
	public static void printCurrentlyRented() {
		System.out.println("\n###########################################");
		if(rentedBooks.size()==0) {
			System.out.println("No rented books");
			return;
		}
		for(int i=0;i<rentedBooks.size();i++) {
				System.out.println(rentedBooks.get(i));
		}
		System.out.println("\n###########################################");
	}	
	public static void CreateEmployees(){
		String edit = "", fn = "", ln = "";
		int a2;
		double sal;
		
		if(empAm != null && empPm != null){
			System.out.println("Employees already exist\nDo you want to edit them? (Yes/No)");
			edit = scan.nextLine();
			if(edit.equalsIgnoreCase("yes")){
				System.out.println("Which one do you want to edit? (AM/PM)");
				edit = scan.nextLine();
				if(edit.equalsIgnoreCase("am")){
					System.out.println("Please enter first name: ");
					fn = scan.nextLine();
					System.out.println("Please enter last name: ");
					ln = scan.nextLine();
					System.out.println("Please enter age: ");
					a2 = scan.nextInt();
					System.out.println("Please enter salary: ");
					sal = scan.nextDouble();
					empAm = new Employee(fn, ln, a2, sal);
					System.out.println("Add success!");
				}
				else if (edit.equalsIgnoreCase("pm")){
					System.out.println("Please enter first name: ");
					fn = scan.nextLine();
					System.out.println("Please enter last name: ");
					ln = scan.nextLine();
					System.out.println("Please enter age: ");
					a2 = scan.nextInt();
					System.out.println("Please enter salary: ");
					sal = scan.nextDouble();
					empPm = new Employee(fn, ln, a2, sal);
					System.out.println("Add success!");
				}
				else{
					return;
				}
			}
			else 
				return;
		}
		else{
			System.out.println("Which one do you want to enter? (AM/PM)\n");
			scan.nextLine();
			edit = scan.nextLine();
			if(edit.equalsIgnoreCase("am")){
				System.out.println("Please enter first name: ");
				fn = scan.nextLine();
				System.out.println("Please enter last name: ");
				ln = scan.nextLine();
				System.out.println("Please enter age: ");
				a2 = scan.nextInt();
				System.out.println("Please enter salary: ");
				sal = scan.nextDouble();
				empAm = new Employee(fn, ln, a2, sal);
				System.out.println("Add success!");
			}
			else if (edit.equalsIgnoreCase("pm")){
				System.out.println("Please enter first name: ");
				fn = scan.nextLine();
				System.out.println("Please enter last name: ");
				ln = scan.nextLine();
				System.out.println("Please enter age: ");
				a2 = scan.nextInt();
				System.out.println("Please enter salary: ");
				sal = scan.nextDouble();
				empPm = new Employee(fn, ln, a2, sal);
				System.out.println("Add success!");
			}
			else{
				return;
			}
		}
	}
	
	public static void ChangeEmployee(){
		String edit= "", fn = "", ln = "";
		double sal;
		int a2;
		
		System.out.println("Which one do you want to edit? (AM/PM)");
		scan.nextLine();
		edit = scan.nextLine();
		if(edit.equalsIgnoreCase("am")){
			System.out.println("Please enter first name: ");
			fn = scan.nextLine();
			scan.nextLine();
			System.out.println("Please enter last name: ");
			ln = scan.nextLine();
			System.out.println("Please enter age: ");
			scan.nextLine();
			a2 = scan.nextInt();
			System.out.println("Please enter salary: ");
			scan.nextLine();
			sal = scan.nextDouble();
			empAm = new Employee(fn, ln, a2, sal);
		}
		else if (edit.equalsIgnoreCase("pm")){
			System.out.println("Please enter first name: ");
			fn = scan.nextLine();
			scan.nextLine();
			System.out.println("Please enter last name: ");
			ln = scan.nextLine();
			System.out.println("Please enter age: ");
			scan.nextLine();
			a2 = scan.nextInt();
			System.out.println("Please enter salary: ");
			scan.nextLine();
			sal = scan.nextDouble();
			empPm = new Employee(fn, ln, a2, sal);
		}
	}
	public static void 	printListOfSaleBooks() {
		System.out.println("\n###########################################");
		if(nbOfSaleBooks()==0) {
			System.out.println("No  books for sale");
			return;
		}
		for(int i=0;i<books.size();i++) {
			if(books.get(i) instanceof Sale)
				System.out.println(books.get(i));
		}
		System.out.println("\n###########################################");

	}
	public static void 	printListOfRentBooks() {
		System.out.println("\n###########################################");
		if(nbOfRentBooks()==0) {
			System.out.println("No books for rent");
			return;
		}
		for(int i=0;i<books.size();i++) {
			if(books.get(i) instanceof ForRent)
				System.out.println(books.get(i));
		}
		System.out.println("\n###########################################");

	}
	public static int nbOfSaleBooks() {
		int c=0;
		for(int i=0;i<books.size();i++) {
			if(books.get(i) instanceof Sale)
				c++;
		}
		return c;
	}
	public static int nbOfRentBooks() {
		int c=0;
		for(int i=0;i<books.size();i++) {
			if(books.get(i) instanceof ForRent)
				c++;
		}
		return c;
	}
	public static void PrintEmployees() {
		System.out.println("\n###########################################");
		if(empAm == null && empPm == null) {
			System.out.println("No employees are currently employed!\n");
			return;
		}
		else if(empAm == null && empPm != null)
			System.out.println("PM Shift:\n" + empPm + "\n");
		if(empPm == null && empAm != null)
			System.out.println("AM Shift:\n" + empAm + "\n");
		else
			System.out.println("AM Shift:\n" +empAm + "\n\nPM Shift:\n" + empPm + "\n");
		System.out.println("\n###########################################");

	}
	public static void PrintListOfClients() {
		System.out.println("\n###########################################");
		if(clients.size()==0){
			System.out.println("No employees are currently employed!\n");
			return;
		}
		for(int i=0;i<clients.size();i++) {
			System.out.println(clients.get(i));
		}
		System.out.println("\n###########################################");
	}
	public static void  AdminLogin() {
		int choice = 0;
		String user;
		boolean login = true;
		System.out.println("\t\t***Login Page***\n");
		System.out.println("Username:\t");
		scan.nextLine();
		user = scan.nextLine();
		
		if (admin.getUsername().equals(user)) {
			int i = 4;
			String s;
			do {
				System.out.println("Enter password : (" + i + " remaining attempts) ");
				s = scan.nextLine();
				i--;
			} while (i > 0 && !admin.confirmPassword(s));
			if (!admin.confirmPassword(s)){
				System.out.println("Logging in unsuccessfull!\nReturning to main menu\n");
				login = false;
				return;
			}
		} else{
			System.out.println("Wrong username or password.");
			login = false;
			return;
		}
		
		while(login){
			System.out.println("1-\tCreate Employees\n2-\tAdd books\n3-\tRemove books\n4-\tChange username\n5-\tChange password\n6-\tEdit employee\n7-\tShow transactions\n8-\tShow employees\n9-\tShow list of books\n"
					+"10-\tShow list of clients\n"+"11-\tshow list of books inRent\n"+ "0-\tLogout");
			choice = scan.nextInt();
			switch(choice){
			case 0:
				login = false;
				System.out.println("Successfully logged out!\n--------------------\n");
				break;
			case 1:
				CreateEmployees();
				break;
			case 2:
				AddBook();
				break;
			case 3:
				RemoveBook();
				break;
			case 4:
				ChangeUsername();
				break;
			case 5:
				ChangePassword();
				break;
			case 6:
				ChangeEmployee();
				break;
			case 7:
				PrintTransactions();
				break;
			case 8:
				PrintEmployees();
				break;
			case 9:
				PrintListOfBooks();
				break;
			case 10:
				PrintListOfClients();
				break;
			case 11:
				printCurrentlyRented();
				break;
			default:
				break;
			}
		}
	}
	
	public static Client ClientSignUp(){
		System.out.println("Please enter first name: ");
		String fn = scan.nextLine();
		System.out.println("Please enter last name: ");
		String ln = scan.nextLine();
		System.out.println("Please enter age: ");
		int age = scan.nextInt();
		Client c = new Client(fn, ln, age);
		clients.add(c);
		return c;
	}
	
	public static Client ClientLogin(){
		String id = "", cancer = "";
		Client c = null;
		
		System.out.println("Please enter your id: ");
		scan.nextLine();
		id = scan.nextLine();
		if(getClientById(id) == null){
			System.out.println("User doesn't exist!\nDo you want to create a new one? (Yes/No)");
			//scan.nextLine();
			cancer = scan.nextLine();
			if(cancer.equalsIgnoreCase("yes")){
				c = ClientSignUp();
				System.out.println("You successfully signed up!");
			}
			return c;
		}
		System.out.println("You are successfully logged in!");
		return getClientById(id);
	}
	
	public static void ClientPage(){
		Client c = null;
		int choice = 0;
		boolean login = true;
		int isbn = 0;
		
		c = ClientLogin();
		if(c == null){
			System.out.println("Not logged in!");
			return;
		}
		System.out.println("Hello there beloved customer \n" + c.getFirstName() + " " + c.getLastName() + "\nYour ID: " + c.getId());
		while(login){
			System.out.println("1-\tBuy a book\n2-\tRent a book\n3-\tShow list of books\n4-\tShow owned books\n5-\tShow list of cart\n6-\tCheckout\n"
					+ "0-\tLogout");
			choice = scan.nextInt();
			switch(choice){
			case 0:
				login = false;
				System.out.println("Successfully logged out!\n--------------------\n");
				break;
			case 1:
				if( nbOfSaleBooks()==0) {
					System.out.println("no books available for sale yet !!");
					break;
				}
				printListOfSaleBooks();

				System.out.println("Please enter ISBN: ");
				isbn = scan.nextInt();
				if(getBookByISBN(isbn)==null || !books.contains(getBookByISBN(isbn))){
					System.out.println("Invalid book");
					return;
				}
				c.addToCartSale((Sale)getBookByISBN(isbn));
				System.out.println("Successfully added to cart!");
				break;
			case 2:
				if( nbOfSaleBooks()==0) {
					System.out.println("no books available for rent yet !!");
					break;
				}
				printListOfRentBooks();
				System.out.println("Please enter ISBN: ");
				isbn = scan.nextInt();
				if(getBookByISBN(isbn)==null || !books.contains(getBookByISBN(isbn))){
					System.out.println("Invalid book");
					return;
				}
				c.addToCartRent((ForRent)getBookByISBN(isbn));
				System.out.println("Successfully added to cart!");
				break;
			case 3:
				PrintListOfBooks();
				break;
			case 4:
				c.PrintListOfOwnedBooks();
				break;
			case 5:
				c.PrintCart();
				break;
			case 6:
				Transaction t = c.checkout();
				System.out.println(t + "-----------------------------------------------------------");
				transactions.add(t);
				break;
			}
		}
	}
	
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
		System.out.println("** Hello and Welcome! **");
		int choice;
		boolean running = true;
		LibraryFiles.read();
		while (running) {
			System.out.print("Login as a\n(1) Admin\t(2) Client\t(0)To Terminate : ");
			choice = scan.nextInt();

			switch (choice) {
			case 0:
				running = false;
				break;
			case 1:
				AdminLogin();
				break;
			case 2:
				if(empAm==null && empPm==null) {
					System.out.println("no employees to serve !!");
					break;
				}
					
				ClientPage();
				break;
			}
		}
		LibraryFiles.save();
	}

}
