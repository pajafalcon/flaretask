package com.jozsefpajor.flaretask.question.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

/**
 * @author PJ
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // question_id on stackexchange sites
    private Long originalId;

    private Long acceptedAnswerId;
    private Long viewCount;
    private Long answerCount;
    private Long askerUserId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToMany(
            cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "question_tag",
            joinColumns = @JoinColumn(name = "question_1"),
            inverseJoinColumns = @JoinColumn(name = "tag_1"))
    @Builder.Default
    private List<Tag> tags = new ArrayList<>();
}
