package co.uk.mytutor.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    private String message;

    public Response(String message) {
        this.message = message;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }
}
