package Individual;

public class Adminstrator {

	private String Username;
	private String Password;

	public Adminstrator(String U, String P) {
		Username = U;
		Password = P;
	}
	
	public boolean confirmPassword(String s){
		if(s.equals(Password))
			return true;
		return false;
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