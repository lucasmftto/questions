package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Description obrigatorio")
    private String description;

    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updatedAt;

    @Column
    private Boolean enabled;

    @PrePersist
    public void prePersist() {
        setCreatedAt(LocalDate.now());
        setUpdatedAt(LocalDate.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(LocalDate.now());
    }


}
