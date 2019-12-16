package com.jozsefpajor.flaretask.question.controller.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class QuestionClientResponseOwner {

    @JsonProperty("reputation")
    private Long reputation;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("user_type")
    private String userType;
    @JsonProperty("accept_rate")
    private Long acceptRate;
    @JsonProperty("profile_image")
    private String profileImage;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("link")
    private String link;
}
