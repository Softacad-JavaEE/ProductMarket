package market.login;

import java.util.ArrayList;
import java.util.List;

import market.models.User;

// TODO should be reimplemented to affect entity beans
public class UserService {
	private List<User> users = new ArrayList<User>();
	private static UserService instance = null;
	private UserService () {
		users.add(new User("Ivan Ivanov", "ivan", "ivan", "0"));
		users.add(new User("John Smith", "john", "smith", "1"));		
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
	public List<User> getUsers() {
		return users;
	}
}
