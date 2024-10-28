package com.dogognon.sohliou.kone.security.data;

import java.time.Instant;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data

@Entity
@Table(name = "roles")
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
@EntityListeners(AuditingEntityListener.class)
public class Role {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

   
    @NaturalId
    @NonNull
    @Column(length = 60)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @CreatedDate
    private Instant createdAt;
	
	
	@LastModifiedDate
    private Instant modifiedAt;

   

}
