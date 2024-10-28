package com.dogognon.sohliou.kone.security.data;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UuidGenerator;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Poste;
//import com.dogognon.sohliou.kone.NouvelleProcedure.data.Services;
import com.dogognon.sohliou.kone.security.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "phone"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})


@NoArgsConstructor
@AllArgsConstructor
public class User extends DateAudit {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String id;

	// User identity
    @Size(max = 250)
	@Column(updatable=true, nullable = true)
    private String nomprenoms;
	

 
    @NaturalId(mutable=true)
    @Size(max = 10)
    @Column(updatable=true, nullable = true,length = 10)
    private String phone;

	
   
    @NaturalId(mutable=true)
    @Size(max = 150)
    @Email
    @Column(updatable=true, nullable = true,length = 150)
    private String email;
    
    //@Column(updatable=true, nullable = true)
    //private String addresse;
    

    /*@JsonFormat(pattern="dd-MM-yyyy")
    @Column(updatable=true, nullable = true)
    private int datedenaissance;
    
    @Column(updatable=true, nullable = true)
    private String numeropiece;
    
    @Column(updatable=true, nullable = true)
    private String latLong;
    
   @NonNull
	@Column(nullable = true,updatable=true,length = 36)
	private String photoUser;*/
    
    @JsonBackReference
	 @ManyToOne
	 @JoinColumn(name = "postes")
	 private Poste postes;
    
    
    @Transient
	private String poste_id;
  
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    // User Access and metadata
    @JsonIgnore
    @Size(max = 250)
    @Column(updatable=true, nullable = true)
    private String passwords;
    
    /*@Size(max = 4)
    @JsonIgnore
    @Column(updatable=true, nullable = true,length = 4)
    private String otp;
    */
    /*@JsonIgnore
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(updatable=true, nullable = false)
    private AuthentifProvider provider;
    
    @JsonIgnore
    @Column(updatable=true, nullable = true)
    private String providerId;*/
    

    
}