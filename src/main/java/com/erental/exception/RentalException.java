package com.erental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author TEGA
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RentalException extends RuntimeException{

    public RentalException() {
    }

    public RentalException(String message) {
        super(message);
    }

    public RentalException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    
}
