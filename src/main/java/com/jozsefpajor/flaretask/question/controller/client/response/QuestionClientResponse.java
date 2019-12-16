package com.jozsefpajor.flaretask.question.controller.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author PJ
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class QuestionClientResponse {

    @JsonProperty("items")
    private List<QuestionClientResponseQuestion> items = new ArrayList<>();
    @JsonProperty("has_more")
    private Boolean hasMore;
    @JsonProperty("quota_max")
    private Long quotaMax;
    @JsonProperty("quota_remaining")
    private Long quotaRemaining;
}
