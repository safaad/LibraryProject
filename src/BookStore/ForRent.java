package BookStore;

import java.util.Date;
import java.util.ArrayList;

public class ForRent extends Books {
	private ArrayList<Date> Rented;
	private ArrayList<Date> Returned;
	private Date Deadline;
	private boolean isRented;
	private double rentPrice;
	
	public ForRent(String title, String author, int isbn, int a, double p){
		super(title, author, isbn, a);
		Rented = new ArrayList<Date>();
		Returned = new ArrayList<Date>();
		Deadline = null;
		isRented = false;
		rentPrice = p;
	}
	
	public ArrayList<Date> getRented(){
		return Rented;
	}
	
	public ArrayList<Date> getReturned(){
		return Returned;
	}
	
	public Date getDeadline(){
		return Deadline;
	}
	
	public void setDeadline(Date d){
		Deadline = d;
	}
	
	public boolean getIsRented(){
		return isRented;
	}
	
	public void setIsRented(boolean k){
		isRented = k;
	}
	
	public double getRentPrice(){
		return rentPrice;
	}
	
	public void setRentPrice(double p){
		rentPrice = p;
	}
	
	public String toString() {
		return super.toString();
	}
}
