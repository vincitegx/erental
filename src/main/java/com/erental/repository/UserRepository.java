package com.erental.repository;

import com.erental.domain.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    
    Optional<Users> findByEmail(String email);
 
    boolean existsByEmail(String email);
    
    Optional<Users> findByName(String name);
    
}
