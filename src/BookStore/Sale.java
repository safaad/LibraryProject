package BookStore;

public class Sale extends Books {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double price, netprice;

	public Sale(String t, String a, int i, int target, double p, double netp) {
		super(t, a, i, target);
		price = p;
		netprice = netp;
	}

	public double getPrice() {
		return price;
	}

	public double getNetPrice() {
		return netprice;
	}

	public String toString() {
		return super.toString() + "\n";
	}
}
