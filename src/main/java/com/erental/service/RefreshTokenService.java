package com.erental.service;

import com.erental.domain.RefreshToken;

/**
 *
 * @author TEGA
 */
public interface RefreshTokenService {
    
    RefreshToken generateRefreshToken();
    
    void validateRefreshToken(String token); 
    
    void deleteRefreshToken(String token);
}
