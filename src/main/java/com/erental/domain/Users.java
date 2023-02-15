package com.erental.domain;

import com.erental.domain.security.AccountType;
import com.erental.domain.security.Role;
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
public class Users implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(name = "enabled", columnDefinition = "BIT", length = 1,nullable = false)
    private boolean enabled;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "gender", nullable = false)
    private String gender;
    
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="users_account", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<AccountType> account;
    
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="users_role", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
 
    @Column(name = "created_date", nullable = false, updatable = false)
    protected Instant createdDate;
    
    @Column(nullable = false)
    protected Instant modifiedDate;
    
    @Column(nullable = false)
    private String password;
    
    private String imageUrl;
    
}
