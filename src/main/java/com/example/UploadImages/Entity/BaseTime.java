package com.example.UploadImages.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTime implements Serializable {

    @CreatedDate
    @Column(name = "created_at" , nullable = false , updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name = "updated_at" , nullable = false)
    private LocalDate updatedAt;
}
