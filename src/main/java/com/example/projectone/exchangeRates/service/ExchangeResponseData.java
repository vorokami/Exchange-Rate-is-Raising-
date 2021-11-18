package com.example.projectone.exchangeRates.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeResponseData {
        private long timestamp;
        private String base;
        private HashMap<String, Double> rates;

}
