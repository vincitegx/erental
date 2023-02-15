package com.erental.repository;

import com.erental.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long>{
    
}
