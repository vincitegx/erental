/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erental.service.impl;

import com.erental.domain.Users;
import com.erental.domain.VerificationToken;
import com.erental.dto.JwtAuthResponse;
import com.erental.dto.LoginRequestPayLoad;
import com.erental.dto.RefreshTokenRequest;
import com.erental.dto.SignupRequest;
import com.erental.dto.UserInfo;
import com.erental.exception.RentalException;
import com.erental.exception.UserAlreadyExistAuthenticationException;
import com.erental.mapper.UserMapper;
import com.erental.repository.RoleRepository;
import com.erental.repository.UserRepository;
import com.erental.repository.VerificationTokenRepository;
import com.erental.security.JwtProvider;
import com.erental.service.AuthService;
import com.erental.service.MailService;
import com.erental.service.RefreshTokenService;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TEGA
 */
@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;
    
    @Override
    @Transactional
    public void registerNewUser(SignupRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
              
        if(userRepository.findByEmail(signUpRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistAuthenticationException("User with email " + signUpRequest.getEmail() + " already exist");
        }else{
            if(signUpRequest.getPassword().equals(signUpRequest.getMatchingPassword())){
                signUpRequest.setMatchingPassword(passwordEncoder.encode(signUpRequest.getMatchingPassword()));
                Users user = userMapper.mapDtoToUser(signUpRequest, roleRepository);
                userRepository.save(user);
                String token = generateVerificationToken(user);
//                mailService.sendMail(new NotificationEmail("Please Activate your Account",
//                user.getEmail(), "Thank you for signing up to BetterSounds, " +
//                "please click on the below url to activate your account : " +
//                "http://localhost:8080/api/auth/accountVerification/" + token));
            }else{
                throw new RentalException("Wrong Password Match");
            }
        }
    }
    @Transactional
    private String generateVerificationToken(Users user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
    
    @Override
    @Transactional
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(()-> new RentalException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
        verificationTokenRepository.delete(verificationToken.get());
    }
    
    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        Users user = userRepository.findByEmail(email).orElseThrow(()-> new RentalException("User not found with email - "+ email));
        user.setEnabled(true);
        userRepository.save(user);
        
    }
    
    @Override
    public JwtAuthResponse login(LoginRequestPayLoad loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        UserInfo user = new UserInfo(getCurrentUser().getId(), getCurrentUser().getName(), getCurrentUser().getImageUrl(), getCurrentUser().getRoles());
        return JwtAuthResponse.builder()
                .authenticationToken(token)
                .user(user)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .build();
    }
    
    @Override
    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUser().getName());
        
        return JwtAuthResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .user(refreshTokenRequest.getUser())
                .build();
    }

    
    @Transactional(readOnly = true)
    @Override
    public Users getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }
    
    @Override
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
    
    @Override
    public Optional<Users> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public void logout(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
    }

    
}
