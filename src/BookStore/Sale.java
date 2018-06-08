
public class Sale extends Books {
	protected int price, netprice;

	public Sale(String t, String a, int i, int target, int p, int netp) {
		super(t, a, i, target);
		price = p;
		netprice = netp;
	}

	public int getPrice() {
		return price;
	}

	public int getNetPrice() {
		return netprice;
	}

	public String toString() {
		return super.toString() +"\n";
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 58edee289614ac2a49f51a82605f6d6ca9369374
