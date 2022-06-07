package com.in28minutes.microservices.currencyexchangeservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchange")
public class CurrencyExchange {
    @Id
    private long id;
    @Column(name ="fromm")
    private String from;
    @Column(name ="too")
    private String to;

    @Column(name = "multiple")
    private BigDecimal conversionMultiple;
    private String environment;
}
