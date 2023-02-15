package com.erental.mapper;

import com.erental.constant.DefaultRoles;
import com.erental.domain.Users;
import com.erental.domain.security.Role;
import com.erental.dto.SignupRequest;
import com.erental.repository.RoleRepository;
import java.util.HashSet;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author TEGA
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "password", source = "signupRequest.matchingPassword")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "modifiedDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "roles", expression = "java(setDefaultRole(roleRepository))")
    Users mapDtoToUser(SignupRequest signupRequest, RoleRepository roleRepository);

    default Set<Role> setDefaultRole(RoleRepository roleRepository){
        Role role = roleRepository.findByName(DefaultRoles.DEFAULT_ROLE);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return roles;
    }
    
}
