package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
public class VoteAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotEmpty
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @NotEmpty
    private Question question;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    @NotEmpty
    private Answer answer;

    @Column
    @NotEmpty
    private Integer score;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updateAt;
}
