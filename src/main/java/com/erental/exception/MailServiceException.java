package com.erental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author TEGA
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MailServiceException extends RuntimeException{

    public MailServiceException(String message, MailException e) {
        super(message,e);
    }
    
    
    
}
