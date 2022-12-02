package com.infodev.ecommerceproject.Exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFormat {

    private String responseCode;
    private HttpStatus statusCode;
    private String message;

    public ResponseFormat(String responseCode, HttpStatus statusCode, String message) {
        this.responseCode = responseCode;
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
