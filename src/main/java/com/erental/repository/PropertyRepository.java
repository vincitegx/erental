package com.erental.repository;

import com.erental.domain.Property;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{
    
    List<String> findByType();
    
}
