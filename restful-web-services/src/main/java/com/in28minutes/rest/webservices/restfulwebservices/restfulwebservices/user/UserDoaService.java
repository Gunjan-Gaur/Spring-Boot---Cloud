package com.in28minutes.rest.webservices.restfulwebservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserDoaService {
	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 3;
	
	static {
		users.add(new User(1,"A",new Date()));
		users.add(new User(2,"B",new Date()));
		users.add(new User(3,"C",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null)
			user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		List<User> output = users.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
		return output.size() > 0 ? output.get(0) : null;
	}
	
	public Boolean deleteById(int id) {
		return users.removeIf(u -> u!=null && u.getId() == id);
	}
}
