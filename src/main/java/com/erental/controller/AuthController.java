package com.erental.controller;

import com.erental.dto.JwtAuthResponse;
import com.erental.dto.LoginRequestPayLoad;
import com.erental.dto.RefreshTokenRequest;
import com.erental.dto.SignupRequest;
import com.erental.service.AuthService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TEGA
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest registerRequest) {
        authService.registerNewUser(registerRequest);
        return new ResponseEntity<>("User Registration Successful",OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully",OK);
    }
    
    
    @PostMapping("/login")
    public JwtAuthResponse login(@RequestBody LoginRequestPayLoad loginRequest){
        return authService.login(loginRequest);
    }
    
    @PostMapping("/refresh/token")
    public JwtAuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        authService.logout(refreshTokenRequest);
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
    
}
