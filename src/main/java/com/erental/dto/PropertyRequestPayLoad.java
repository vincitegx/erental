package com.erental.dto;

import com.erental.domain.PropertyPictures;
import com.erental.domain.State;
import com.erental.domain.Users;
import com.erental.domain.security.AccountType;
import java.time.Instant;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyRequestPayLoad {
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Users owner;
    
    @Column
    private String name;
 
    @Column(name = "verified", columnDefinition = "BIT", length = 1,nullable = false)
    private boolean verified;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private PropertyPictures pictures;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private State location;    
    
    @Column(nullable = false)
    private String type;
    
    @Column(nullable = false)
    private String description;
    
}
