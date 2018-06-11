package Individual;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import BookStore.*;
import Menu.Driver;

public class Client extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		id = "" + this.fn.charAt(0) + this.ln.charAt(0) + serial;
		serial++;
		ownedBooks = new ArrayList<Books>();
		cart = new ArrayList<Books>();
	}

	public Client(String first, String last, int a, String username) {
		super(first, last, a);
		id = username;
		serial = username.charAt(0) - '0';

	}

	public static void setSerial(int serial2) {
		serial = serial2;
	}

	public static int getSerial() {
		return serial;
	}

	public double addToCartSale(Sale b) {
		double p;
		cart.add(b);
		if (b.getTarget() > age) {
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

	@SuppressWarnings("deprecation")
	public void addToCartRent(ForRent b) {
		Date cur = new Date();
		Date expected = new Date();
		if (b.getTarget() > age) {
			System.out.println("Inappropriate age\n");
			return;
		}
		if (!b.getIsRented()) {
			expected.setHours(cur.getHours() + 24 * 3);
			b.setDeadline(expected);
			cart.add(b);
			b.getRented().add(cur);
			Driver.books.remove(b);
		
		}
	}

	@SuppressWarnings("deprecation")
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
		Driver.rentedBooks.remove(b);
		Driver.books.add(b);
	}

	public String getId() {
		return id;
	}

	public void PrintListOfOwnedBooks() {
		boolean flag = false;
		System.out.println("List of books rented: ");
		for (Books b : ownedBooks) {
			if (b instanceof ForRent) {
				System.out.println((ForRent) b);
				flag = true;
			}
		}
		if (flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No rented books");
		flag = false;
		System.out.println("List of bought books: ");
		for (Books b2 : ownedBooks) {
			if (b2 instanceof Sale) {
				System.out.println((Sale) b2);
				flag = true;
			}
		}
		if (flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No bought books");
		System.out.println("End of list\n");
	}

	public void PrintCart() {
		boolean flag = false;
		if (cart == null) {
			System.out.println("Your cart is empty :)");
			return;
		}
		System.out.println("----------------------\n");

		System.out.println("List of books rented: ");
		System.out.println("---------------------\n");

		for (Books b : cart) {
			if (b instanceof ForRent) {
				System.out.println(((ForRent) b).getTitle());
				flag = true;
			}
		}
		if (flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No rented books in cart");
		System.out.println("----------------------\n");

		flag = false;
		System.out.println("List of bought books: ");
		for (Books b2 : cart) {
			if (b2 instanceof Sale) {
				System.out.println(((Sale) b2).getTitle() +"\tprice " +((Sale) b2).getPrice());
				flag = true;
			}
		}
		if (flag)
			System.out.println("----------------------\n");
		else
			System.out.println("No bought books in cart");
		System.out.println("----------------------\n");

		System.out.println("End of list\n");
		System.out.println("----------------------\n");

	}

	public Transaction checkout() {
		Transaction trx = null;
		@SuppressWarnings("deprecation")
		int currentDate = Calendar.getInstance().getTime().getHours();
		if (this.cart.size() == 0) {
			System.out.println("Your cart is empty !!");
			return null;
		}
		if (Driver.empAm == null)
			trx = new Transaction(this, Driver.empPm);
		else if (Driver.empPm == null)
			trx = new Transaction(this, Driver.empAm);
		else if (currentDate < 12 && currentDate > 0) {
			trx = new Transaction(this, Driver.empAm);
		} else {
			trx = new Transaction(this, Driver.empPm);
		}
		for (int i = 0; i < cart.size(); i++) {
			trx.purchasedBooks.add(cart.get(i));
			if (cart.get(i) instanceof Sale) {
				trx.setTotalMoney(purchase);
				Driver.books.remove(i);
			} else {
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
		return super.toString() + "Customer ID: " + id + "\n---------------------";
	}
}
