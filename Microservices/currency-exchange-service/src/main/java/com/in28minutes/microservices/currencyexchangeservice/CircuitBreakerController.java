package com.in28minutes.microservices.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Retry(name = "default", fallbackMethod = "fallBackMethod")
    public String sampleAPI(){
        logger.info("Retry attempt");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy", String.class);
        return forEntity.getBody();
    }

    public String fallBackMethod(Exception ex){
        return "Fall back Method";
    }
}
