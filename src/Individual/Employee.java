package Individual;

public class Employee extends Person {
	String id;
	double Salary;
	protected static int serial = 100;

	public Employee(String first, String last, int a, double Salary) {
		super(first, last, a);
		id = "" + serial + this.fn.charAt(0) + this.fn.charAt(1) + "_"
				+ this.ln.charAt(0) + this.ln.charAt(1);
		serial++;
	}

	public Employee(String first, String last, int a, double Salary, String username) {
		super(first, last, a);
		id = username;
		this.Salary = Salary;
		serial=username.charAt(0) - '0';
		
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
}
