package com.example.projectone.exchangeRates.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.example.projectone")
@PropertySource("classpath:application.properties")
public class ExchangeRateConfig {


    //== fields ==
    @Value("${exchangeRates.app_id}")
    private String app_id;

    @Value("${exchangeRates.typeOfCurrency}")
    private String typeOfCurrency;


    // == bean methods ==
    @Bean
    @App_id
    public String App_id() {
        return this.app_id;
    }

    @Bean
    @TypeOfCurrency
    public String TypeOfCurrency() {
        return this.typeOfCurrency;
    }
}
