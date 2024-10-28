package com.dogognon.sohliou.kone.security.audit;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
/**
 * 
 * @author m17405
 * @JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
 *
 */
@Getter @Setter
public abstract class DateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

	@CreatedDate
    private Instant createdAt;

	//@Transient
   @LastModifiedDate
   private Instant updatedAt;

    

}
