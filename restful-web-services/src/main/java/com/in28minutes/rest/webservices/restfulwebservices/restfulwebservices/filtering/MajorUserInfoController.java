package com.in28minutes.rest.webservices.restfulwebservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MajorUserInfoController {

    @GetMapping("/filtering")
    public MappingJacksonValue getUserInfo(){
        UserInfo userInfo = new UserInfo("Pizza","Domino's","90 Crore");
        //These line of Code is for Dynamic Filtering when we have to show ste of variables of same class through different api
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("favFood","favBrand");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(userInfo);
        mapping.setFilters(filters);
        return mapping;
    }

    //This class will show 2 variables only favBrand and brandIncome
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans() {
        List<UserInfo> list = Arrays.asList(new UserInfo("value1", "value2", "value3"),
                new UserInfo("value12", "value22", "value32"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("favBrand", "brandIncome");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }
}
