package com.example.projectone.exchangeRates.service;

import com.example.projectone.exchangeRates.config.App_id;
import com.example.projectone.exchangeRates.config.TypeOfCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class ExchangeRatesService {

    //== fields ==
    private final String app_id;
    private final String typeOfCurrency;

    // == constructor ==
    @Autowired
    public ExchangeRatesService(@App_id String app_id, @TypeOfCurrency String typeOfCurrency) {
        this.app_id = app_id;
        this.typeOfCurrency = typeOfCurrency;
    }

    // == private methods ==
    private double getTodayRate(RestTemplate restTemplate) {

        //request json data
        ExchangeResponseData exchangeResponseData = restTemplate.getForObject(
                "https://openexchangerates.org/api/historical/" +
                        getDate(0) + ".json" +
                        "?app_id=" +
                        this.app_id +
                        "&symbols=" +
                        typeOfCurrency, ExchangeResponseData.class);

        //get and return exchange rate for choosing currency
        return exchangeResponseData.getRates().get(typeOfCurrency);
    }

    private double getYesterdayRate(RestTemplate restTemplate) {

        //request json data
        ExchangeResponseData exchangeResponseData = restTemplate.getForObject(
                "https://openexchangerates.org/api/historical/" +
                        getDate(1) + ".json" +
                        "?app_id=" +
                        this.app_id +
                        "&symbols=" +
                        typeOfCurrency, ExchangeResponseData.class);


//        //request json data
//        ExchangeResponseData exchangeResponseData = restTemplate.getForObject(
//                "https://openexchangerates.org/api/historical/" +
//                        getDate(1) + ".json" +
//                        "?app_id=" +
//                        this.app_id +
//                        "&symbols=" +
//                        typeOfCurrency, ExchangeResponseData.class);

        //get and return exchange rate for choosing currency
        return exchangeResponseData.getRates().get(typeOfCurrency);

    }

    //getting date from current date
    private String getDate(int minusOfDays) {
        LocalDate localDate = LocalDate.now().minusDays(minusOfDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = localDate.format(formatter);

        return formattedString;
    }


    // == public methods ==
    public boolean exchangeRateIsRaised(RestTemplate restTemplate) {
        if (getTodayRate(restTemplate) > getYesterdayRate(restTemplate)) {
            return true;
        }
        return false;

    }
}
