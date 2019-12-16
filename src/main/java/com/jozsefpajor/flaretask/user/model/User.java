package com.jozsefpajor.flaretask.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("badge_counts")
    private BadgeCount badgeCounts;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("is_employee")
    private Boolean isEmployee;
    @JsonProperty("last_modified_date")
    private Long lastModifiedDate;
    @JsonProperty("last_access_date")
    private Long lastAccessDate;
    @JsonProperty("reputation_change_year")
    private Long reputationChangeYear;
    @JsonProperty("reputation_change_quarter")
    private Long reputationChangeQuarter;
    @JsonProperty("reputation_change_month")
    private Long reputationChangeMonth;
    @JsonProperty("reputation_change_week")
    private Long reputationChangeWeek;
    @JsonProperty("reputation_change_day")
    private Long reputationChangeDay;
    @JsonProperty("reputation")
    private Long reputation;
    @JsonProperty("creation_date")
    private Long creationDate;
    @JsonProperty("user_type")
    private String userType;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("accept_rate")
    private Long acceptRate;
    @JsonProperty("location")
    private String location;
    @JsonProperty("website_url")
    private String websiteUrl;
    @JsonProperty("link")
    private String link;
    @JsonProperty("profile_image")
    private String profileImage;
    @JsonProperty("display_name")
    private String displayName;
}
