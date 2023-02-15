package com.erental.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class PropertyOwner implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fullname", nullable = false)
    private String fullname;
    
    @Column(name = "enabled", columnDefinition = "BIT", length = 1,nullable = false)
    private boolean enabled;
    
    @Column(name = "verified", columnDefinition = "BIT", length = 1,nullable = false)
    private boolean verified;
    
    @Column(name = "name", nullable = false)
    private String dob;
 
    @Column(name = "created_date", nullable = false, updatable = false)
    protected Instant createdDate;
    
    @Column(nullable = false)
    protected Instant modifiedDate;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private State state;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Lga lga;
    
    
}
