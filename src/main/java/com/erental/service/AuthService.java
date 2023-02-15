package com.erental.service;

import com.erental.domain.Users;
import com.erental.dto.JwtAuthResponse;
import com.erental.dto.LoginRequestPayLoad;
import com.erental.dto.RefreshTokenRequest;
import com.erental.dto.SignupRequest;
import com.erental.exception.UserAlreadyExistAuthenticationException;
import java.util.Optional;

/**
 *
 * @author TEGA
 */
public interface AuthService {
    
    void registerNewUser(SignupRequest signUpRequest) throws UserAlreadyExistAuthenticationException;
 
    Optional<Users> findUserByEmail(String email);
    
    void verifyAccount(String token);
    
    JwtAuthResponse login(LoginRequestPayLoad loginRequest);
    
    JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    
    boolean isLoggedIn();
    
    void logout(RefreshTokenRequest refreshTokenRequest);
    
    Users getCurrentUser();
    
}
