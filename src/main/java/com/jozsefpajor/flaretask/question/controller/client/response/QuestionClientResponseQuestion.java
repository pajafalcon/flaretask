package com.jozsefpajor.flaretask.question.controller.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class QuestionClientResponseQuestion {

    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("owner")
    private QuestionClientResponseOwner owner;
    @JsonProperty("is_answered")
    private Boolean isAnswered;
    @JsonProperty("view_count")
    private Long viewCount;
    @JsonProperty("bounty_amount")
    private Long bountyAmount;
    @JsonProperty("bounty_closes_date")
    private Long bountyClosesDate;
    @JsonProperty("answer_count")
    private Long answerCount;
    @JsonProperty("score")
    private Long score;
    @JsonProperty("last_activity_date")
    private Long lastActivityDate;
    @JsonProperty("creation_date")
    private Long creationDate;
    @JsonProperty("last_edit_date")
    private Long lastEditDate;
    @JsonProperty("question_id")
    private Long questionId;
    @JsonProperty("link")
    private String link;
    @JsonProperty("title")
    private String title;
    @JsonProperty("accepted_answer_id")
    private Long acceptedAnswerId;
    @JsonProperty("protected_date")
    private Long protectedDate;
}
