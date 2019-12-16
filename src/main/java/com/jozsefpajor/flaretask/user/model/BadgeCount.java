package com.jozsefpajor.flaretask.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
class BadgeCount {

    @JsonProperty("bronze")
    private Long bronzeBadgeCount;
    @JsonProperty("silver")
    private Long silverBadgeCount;
    @JsonProperty("gold")
    private Long goldBadgeCount;
}
