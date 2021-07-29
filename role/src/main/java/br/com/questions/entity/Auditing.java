package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditing {

    @Column(updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:MM:ss")
    private LocalDateTime createdAt;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:MM:ss")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
