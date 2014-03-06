package market.login;

import java.util.ArrayList;
import java.util.List;

public class UserService {
	private List<User> users = new ArrayList<User>();
	private static UserService instance = null;
	private UserService () {
	}
	public static UserService getInstance () {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	public void addUser(User user){
		users.add(user);
	}
	
}
