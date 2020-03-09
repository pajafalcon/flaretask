package com.jozsefpajor.flaretask.test.unit.user.controller;

import com.jozsefpajor.flaretask.user.controller.rest.client.UserClientControllerRest;
import com.jozsefpajor.flaretask.user.controller.rest.client.response.UserClientResponse;
import com.jozsefpajor.flaretask.user.model.User;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.web.client.RestTemplate;

public class UserClientControllerRestTests {

    private UserClientControllerRest userClient;

    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = mock(RestTemplate.class);

        userClient = new UserClientControllerRest(restTemplate);
    }

    @Test
    public void testGetLatestQuestions_restTemplateCallObjectExists() throws Exception {
        // given
        when(restTemplate.getForObject(anyString(), any(), anyMap()))
                .thenReturn(getMockResponse(getMockUser(1234L)));

        // when
        User result = userClient.getUserByOriginalId(1234L);

        // then
        verify(restTemplate, times(1)).getForObject(any(), eq(UserClientResponse.class), anyMap());
        assertEquals(1234L, result.getUserId());
    }

    @Test
    public void testGetLatestQuestions_restTemplateCallObjectNotExists() throws Exception {
        // given
        when(restTemplate.getForObject(anyString(), any(), anyMap()))
                .thenReturn(new UserClientResponse());

        // when
        User result = userClient.getUserByOriginalId(1234L);

        // then
        verify(restTemplate, times(1)).getForObject(any(), eq(UserClientResponse.class), anyMap());
        assertNull(result);
    }

    private UserClientResponse getMockResponse( User user ) {
        UserClientResponse response = new UserClientResponse();
        response.getItems().add(user);
        return response;
    }

    private User getMockUser( Long originalId ) {
        return User.builder()
                .userId(originalId)
                .build();
    }

}
