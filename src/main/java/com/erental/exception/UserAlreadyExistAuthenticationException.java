package com.erental.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author TEGA
 */
public class UserAlreadyExistAuthenticationException extends AuthenticationException{
    
    public UserAlreadyExistAuthenticationException(String msg) {
        super(msg);
    }
    
}
