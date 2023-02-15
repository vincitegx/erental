package com.erental.domain;

import com.erental.domain.security.AccountType;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Property implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Users owner;
    
    @Column
    private String name;
    
    @Column(name = "taken", columnDefinition = "BIT", length = 1,nullable = false)
    private boolean taken;
    
    @Column(name = "verified", columnDefinition = "BIT", length = 1,nullable = false)
    private boolean verified;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private PropertyPictures pictures;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private State location;
    
//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinTable(name="users_account", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "account_id"))
//    private Set<AccountType> account;
 
    @Column(name = "created_date", nullable = false, updatable = false)
    protected Instant createdDate;
    
    @Column(nullable = false)
    protected Instant modifiedDate;
    
    @Column(nullable = false)
    private String type;
    
    @Column(nullable = false)
    private String description;
    
}
