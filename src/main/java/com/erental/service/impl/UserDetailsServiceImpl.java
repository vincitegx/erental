package com.erental.service.impl;

import com.erental.domain.Users;
import com.erental.domain.security.Role;
import com.erental.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TEGA
 */
@Service
@AllArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        Users user = userOptional.orElseThrow(()-> new UsernameNotFoundException("User not found with email "+ email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }
}
