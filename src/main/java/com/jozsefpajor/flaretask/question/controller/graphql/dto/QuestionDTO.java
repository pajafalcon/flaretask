package com.jozsefpajor.flaretask.question.controller.graphql.dto;

import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;

    // question_id on stackexchange sites
    private Long originalId;

    private Long acceptedAnswerId;
    private Long viewCount;
    private Long answerCount;
    private Long askerUserId;

    private Date createdAt;

    private List<String> tags = new ArrayList<>();
}
