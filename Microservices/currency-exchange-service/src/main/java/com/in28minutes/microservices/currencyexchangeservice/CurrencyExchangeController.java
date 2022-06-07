package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retreiveCurrency(@PathVariable String from, @PathVariable String to){
        //ExchangeValue currencyExchange = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(50),"");
        //Using JPA Now
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from,to);
        if(currencyExchange == null)
            throw new RuntimeException("Unable to find from "+from+ " and to "+to);
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
