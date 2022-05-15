package com.in28minutes.rest.webservices.restfulwebservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	// retrieve Users
	@GetMapping(path = "jpa/users")
	public List<User> retrieveUsers() {
		return userRepository.findAll();
	}

	// retrieve 1 user
	@GetMapping(path = "jpa/user/{id}")
	public EntityModel<User> retrieveSpecificUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
		model.add(link.withRel("all-users"));
		return model;
	}

	@PostMapping("jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("jpa/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@GetMapping(path = "jpa/user/{id}/post")
	public List<Post> retrieveUserPosts(@PathVariable int id) {
		 Optional<User> user =userRepository.findById(id);
		 if(!user.isPresent())
			 throw new UserNotFoundException("id-"+id);
		 return user.get().getPost();
	}

	@PostMapping("jpa/user/{id}/post")
	public ResponseEntity<Object> createUsersPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> savedUser = userRepository.findById(id);
		if(!savedUser.isPresent())
			throw new UserNotFoundException("id-"+id);
		post.setUser(savedUser.get());
		postRepository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
