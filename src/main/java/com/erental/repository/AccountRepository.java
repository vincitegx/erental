package com.erental.repository;

import com.erental.domain.security.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountType, Long>{
    
}
