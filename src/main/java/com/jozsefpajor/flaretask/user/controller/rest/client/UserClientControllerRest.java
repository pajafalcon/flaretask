package com.jozsefpajor.flaretask.user.controller.rest.client;

import com.jozsefpajor.flaretask.user.controller.rest.client.response.UserClientResponse;
import com.jozsefpajor.flaretask.user.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserClientControllerRest implements UserClientControllerIf {

    private static final Logger LOG = LoggerFactory.getLogger(UserClientControllerRest.class);

    private static final String USER_URI
            = "https://api.stackexchange.com/2.2/users/{id}?site=stackoverflow";

    private RestTemplate restTemplate;

    @Autowired
    public UserClientControllerRest( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User getUserByOriginalId( Long originalId ) {

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(originalId));

        UserClientResponse response
                = restTemplate.getForObject(USER_URI, UserClientResponse.class, params);
        LOG.info("Stackexchange api called for user data with user id: " + originalId);
        List<User> users = response.getItems();

        if ( users == null || users.isEmpty() ) {
            return null;
        } else {
            return users.get(0);
        }
    }

}
