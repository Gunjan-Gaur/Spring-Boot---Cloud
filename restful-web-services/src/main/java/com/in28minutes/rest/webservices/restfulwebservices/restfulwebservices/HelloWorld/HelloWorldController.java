package com.in28minutes.rest.webservices.restfulwebservices.restfulwebservices.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource contextMsg;

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

	//Internationalization is concept to accept locale from Postman and send response in diff-diff languages as req.
	@GetMapping(path = "/hello-world-internationalization")
	public String helloWorldInter(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return contextMsg.getMessage("good.morning.message",null,"default",locale);
	}

	//Another Way - preferred one using(LocaleContextHolder)
	@GetMapping(path = "/hello-world-internationalization-new")
	public String helloWorldInterNew() {
		return contextMsg.getMessage("good.morning.message",null,"default", LocaleContextHolder.getLocale());
	}
}

