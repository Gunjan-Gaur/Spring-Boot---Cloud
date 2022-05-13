package com.in28minutes.rest.webservices.restfulwebservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
	public EntityModel<User> retrieveSpecificUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id-"+id);
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
		model.add(link.withRel("all-users"));
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		Boolean user = userService.deleteById(id);
		if(!user) {
			throw new UserNotFoundException("id-"+id);
		}
	}
}
