package com.jozsefpajor.flaretask.user.controller.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jozsefpajor.flaretask.user.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author PJ
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class UserClientResponse {

    @JsonProperty("items")
    private List<User> items = new ArrayList<>();
}
