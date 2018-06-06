package Individual;

import java.util.Date;
import java.util.ArrayList;

import BookStore.*;

public class Client extends Person {
	protected String id;
	protected int purchase = 0;
	protected ArrayList<Books> ownedBooks;
	protected static int serial = 500;
	double p;
	protected int nbOfBooks = 0;
	protected ArrayList<Books> cart;

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
		// ownedBooks.add(b);
		cart.add(b);
		if (b.getTarget() < age) {
			System.out.println("Inappropriate age\n");
			return 0;
		}
		if (purchase >= 100 && purchase < 200) {
			p = b.getPrice() * 0.9;
			purchase += p - b.getNetPrice();
		} else if (purchase >= 200) {
			p = b.getPrice() * 0.8;
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
			b.setIsRented(true);
		}
	}

	public void returnRented(ForRent b) {
		ownedBooks.remove(b);
		if (!b.getIsRented()) {
			return;
		}
		if (b.getDeadline().after(new Date())) {
			// delete
		} else {
			b.getReturned().add(new Date());
		}
		b.setIsRented(false);
	}

	public String getId() {
		return id;
	}

	public void finished(){
		Transaction trx;
		String empString;
		do{
			
		}
	}
}