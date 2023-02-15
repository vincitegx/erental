package com.erental.dto;

import com.erental.domain.security.Role;
import java.util.Set;
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
public class UserInfo {
    
    private Long id;
    private String name;
    private String imageUrl;
    private Set<Role> userRoles;
    
}
