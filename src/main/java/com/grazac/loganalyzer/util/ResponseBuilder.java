package com.grazac.loganalyzer.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ResponseBuilder {
    public ResponseEntity<?> buildResponse(boolean success, String message, HttpStatus status, Object data){

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("success", success);
            response.put("message", message);

            if(data != null) {
                response.put("data", data);
            }

            return new ResponseEntity<>(response, status);

    }
}
