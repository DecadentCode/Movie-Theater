package com.example.backendtheater.paypal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> customerInfo) {

        String customerInfoJson = null;
        try {
            customerInfoJson = (new ObjectMapper()).writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }

        return customerInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {

        Map<String, Object> customerInfo = null;
        try {
            customerInfo = (new ObjectMapper()).readValue(customerInfoJSON, Map.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return customerInfo;
    }

}