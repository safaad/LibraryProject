package Individual;

public class Adminstrator {

	private String Username;
	private String Password;

	public Adminstrator(String U, String P) {
		setUsername(U);
		setPassword(P);
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}