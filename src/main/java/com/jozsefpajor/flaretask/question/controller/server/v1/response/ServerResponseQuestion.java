package com.jozsefpajor.flaretask.question.controller.server.v1.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PJ
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerResponseQuestion {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("tags")
    private List<String> tagNames;

    @JsonProperty("is_answered")
    private Boolean aswered;

    @JsonProperty("view_count")
    private Long viewCount;

    @JsonProperty("answer_count")
    private Long answerCount;

    @JsonProperty("creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date creationDate;

    @JsonProperty("user_id")
    private Long askerId;

}
