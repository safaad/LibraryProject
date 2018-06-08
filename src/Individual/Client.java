package Individual;

import java.security.acl.Owner;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import BookStore.*;
import Menu.Driver;

public class Client extends Person {
	protected String id;
	protected int purchase = 0;
	protected ArrayList<Books> ownedBooks;
	protected static int serial = 500;
	double p;
	protected int nbOfBooks = 0;
	protected ArrayList<Books> cart;
	public boolean flag10 = false, flag20 = false;

	public Client(String first, String last, int a) {
		super(first, last, a);
		id = "" + serial + this.fn.charAt(0) + this.fn.charAt(1) + "_" + this.ln.charAt(0) + this.ln.charAt(1);
		serial++;
		ownedBooks = new ArrayList<Books>();
		cart = new ArrayList<Books>();
	}

	public Client(String first, String last, int a, String username) {
		super(first, last, a);
		id = username;
		serial = username.charAt(0) - '0';

	}

	public double addToCartSale(Sale b) {
		double p;
		cart.add(b);
		if (b.getTarget() < age) {
			System.out.println("Inappropriate age\n");
			return 0;
		}
		if (purchase >= 100 && purchase < 200) {
			p = b.getPrice() * 0.9;
			flag10 = true;
			purchase += p - b.getNetPrice();
		} else if (purchase >= 200) {
			p = b.getPrice() * 0.8;
			flag10 = false;
			flag20 = true;
			purchase += p - b.getNetPrice();
		} else {
			p = b.getPrice();
			purchase += p - b.getNetPrice();
		}
		return p;
	}

	public void addToCartRent(ForRent b) {
		Date cur = new Date();
		Date expected = new Date();
		if (!b.getIsRented()) {
			expected.setHours(cur.getHours() + 24 * 3);
			b.setDeadline(expected);
			cart.add(b);
			b.getRented().add(cur);
		}
	}

	public void returnRented(ForRent b) {
		ownedBooks.remove(b);
		if (!b.getIsRented()) {
			return;
		}
		if (b.getDeadline().after(new Date())) {
			System.out.println("You are late but ok! It's " + (new Date()).getDay());
		}
		b.getReturned().add(new Date());
		b.setIsRented(false);
	}

	public String getId() {
		return id;
	}
	
	public void PrintListOfOwnedBooks(){
		boolean flag = false;
		System.out.println("List of books rented: ");
		for(Books b : ownedBooks){
			if(b instanceof ForRent){
				System.out.println((ForRent)b);
				flag = true;
			}
		}
		if(flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No rented books");
		flag = false;
		System.out.println("List of bought books: ");
		for(Books b2 : ownedBooks){
			if(b2 instanceof Sale){
				System.out.println((Sale)b2);
				flag = true;
			}
		}
		if(flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No bought books");
		System.out.println("End of list\n");
	}
	
	public void PrintCart(){
		boolean flag = false;
		if(cart == null){
			System.out.println("Your cart is empty :)");
			return;
		}
		System.out.println("List of books rented: ");
		for(Books b : cart){
			if(b instanceof ForRent){
				System.out.println((ForRent)b);
				flag = true;
			}
		}
		if(flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No rented books in cart");
		flag = false;
		System.out.println("List of bought books: ");
		for(Books b2 : cart){
			if(b2 instanceof Sale){
				System.out.println((Sale)b2);
				flag = true;
			}
		}
		if(flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No bought books in cart");
		System.out.println("End of list\n");
	}

	public Transaction checkout(){
		Transaction trx = null;
		int currentDate = Calendar.getInstance().getTime().getHours();
		if(currentDate < 12 && currentDate > 0) {
			trx = new Transaction(this, Driver.empAm);
		}
		else {
			trx = new Transaction(this, Driver.empPm);
		}
		for(int i = 0; i < cart.size(); i++) {
			trx.purchasedBooks.add(cart.get(i));
			if(cart.get(i) instanceof Sale) {
				trx.setTotalMoney(purchase);
				Driver.books.remove(i);
			}
			else {
				Driver.rentedBooks.add((ForRent) cart.get(i));
				((ForRent) cart.get(i)).setIsRented(true);
			}
			ownedBooks.add(cart.get(i));
			cart.remove(i);
		}
		purchase = 0;
		flag10 = flag20 = false;
		return trx;
	}
	
	public String toString() {
		return super.toString() + "Customer ID: " + id;
	}
}
