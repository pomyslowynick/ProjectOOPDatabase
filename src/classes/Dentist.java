package classes;

public class Dentist{
	
	String username;
	String password;
	boolean rememberLogin = false;
	
	public Dentist(String username, String password, boolean remember){
		this.username = username;
		this.password = password;
		this.rememberLogin = remember;
	}
	
	public String getUsernName(){return this.username;}
	public String getPassword(){return this.password;}
	public boolean getRememberLogin(){return this.rememberLogin;}
	
}
