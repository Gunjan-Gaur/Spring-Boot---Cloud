package com.learning.restfulwebservices.HelloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world") //Instead of this we can use this -
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello  World";
	}	
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloBean() {
		return new HelloWorldBean("Gunjan Gaur");
	}
	
	@GetMapping(path = "/hello/path-variable/{name}")
	public HelloWorldBean pathVarHello(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hey, %s", name));
	}
}

