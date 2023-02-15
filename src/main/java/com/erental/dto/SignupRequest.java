package com.erental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequest {
    
    private String imageUrl;
 
    private String name;
    
    private String email;
    
    private String password;
    
    private String gender;
    
    private String matchingPassword;
 
}
