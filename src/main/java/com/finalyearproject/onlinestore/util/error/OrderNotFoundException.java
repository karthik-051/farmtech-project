package com.mitron.onlinestore.util.error;

import static com.mitron.onlinestore.util.constants.ExceptionMessages.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ORDER_NOT_FOUND_EX_MSG)
public class OrderNotFoundException extends RuntimeException {

    private int statusCode;

    public OrderNotFoundException() {
        this.statusCode = 404;
    }

    public OrderNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
