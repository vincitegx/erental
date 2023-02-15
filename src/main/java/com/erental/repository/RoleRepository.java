package com.erental.repository;

import com.erental.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByName(String name);
    
}
