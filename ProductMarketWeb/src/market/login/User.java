package market.login;


public class User {
	

	public User(String name, String username, String password, String seller) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.seller = seller;
	}
	private String name;
	private String username;
	private String password;
	private String seller;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String isSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
}
