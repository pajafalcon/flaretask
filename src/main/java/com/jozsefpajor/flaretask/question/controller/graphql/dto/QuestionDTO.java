package com.jozsefpajor.flaretask.question.controller.graphql.dto;

import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.model.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class QuestionDTO {
    private Long id;

    // question_id on stackexchange sites
    private Long originalId;

    private Long acceptedAnswerId;
    private Long viewCount;
    private Long answerCount;
    private Long askerUserId;

    private Date createdAt;

    private List<TagDTO> tags = new ArrayList<>();

    public static QuestionDTO fromModel(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .originalId(question.getOriginalId())
                .acceptedAnswerId(question.getAcceptedAnswerId())
                .viewCount(question.getViewCount())
                .answerCount(question.getAnswerCount())
                .askerUserId(question.getAskerUserId())
                .createdAt(question.getCreatedAt())
                .tags(question.getTags().stream().map(TagDTO::fromModel).collect(Collectors.toList()))
                .build();
    }

}
