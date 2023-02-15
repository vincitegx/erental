package com.erental.repository;

import com.erental.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
    
}
