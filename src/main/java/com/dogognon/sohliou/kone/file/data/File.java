package com.dogognon.sohliou.kone.file.data;

import java.time.Instant;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fichiers")
@EntityListeners(AuditingEntityListener.class)
public class File {

	  @Id
	  @GeneratedValue(strategy = GenerationType.UUID)
	  @UuidGenerator(style = UuidGenerator.Style.TIME)
	  private String id;
    
    @CreatedDate
    private Instant createdAt;


    private String fileName;
    
    private String fileType;
    @JsonIgnore
    private String filePath;
    
    @Transient
    private String fileDownloadUri;
    
    //@Transient
    private long size;
    
    @JsonIgnore
    @Lob
    private byte[] data;

    public File(String fileName, String fileType, byte[] data,String filePath,Long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.filePath = filePath;
        this.size = size;
    }
}
