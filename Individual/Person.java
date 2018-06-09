package Individual;

import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String fn, ln;
	protected int age;

	Person(String first, String last, int a) {
		fn = first;
		ln = last;
		age = a;

	}

	public String getFirstName() {
		return fn;
	}

	public String getLastName() {
		return ln;
	}

	public int getAge() {
		return age;
	}

	public void setName(String first, String last) {
		fn = first;
		ln = last;
	}

	public void setAge(int a) {
		age = a;
	}

	public String toString() {
		return "First name: " + fn + "\nLast name: " + ln;
	}
}