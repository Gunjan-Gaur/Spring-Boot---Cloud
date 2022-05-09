package com.learning.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDoaService userService;

	// retrieve Users
	@GetMapping(path = "/users")
	public List<User> retrieveUsers() {
		return userService.findAll();
	}

	// retrieve 1 user
	@GetMapping(path = "/user/{id}")
	public User retrieveSpecificUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id-"+id);
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
	}
}
