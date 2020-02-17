package com.jozsefpajor.flaretask.question.controller.graphql.dto;

import com.jozsefpajor.flaretask.question.model.Tag;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDTO {

    private Long id;
    private String tagName;

    public static TagDTO fromModel(Tag tag) {
        return  TagDTO.builder()
                .id(tag.getId())
                .tagName(tag.getTagName())
                .build();
    }
}
