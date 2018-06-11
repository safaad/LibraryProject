package Individual;


public class Employee extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	double Salary;
	protected static int serial = 100;

	public Employee(String first, String last, int a, double Salary) {
		super(first, last, a);
		id = "" + this.fn.charAt(0) + this.ln.charAt(0) + serial;
		//id = "" + serial + this.fn.charAt(0) + this.fn.charAt(1) + "_" + this.ln.charAt(0) + this.ln.charAt(1);
		this.Salary=Salary;
		serial++;
	}
	
	public static void setSerial(int serial2) {
		serial = serial2;
	}
	
	public static int getSerial() {
		return serial;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
	public String toString() {
		return super.toString() + "Employee ID: " + id + "\nSalary: " + Salary;
	}
}
