package com.erental.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestPayLoad {
    
     @NotBlank
    private String email;
 
    @NotBlank
    private String password;
    
}
