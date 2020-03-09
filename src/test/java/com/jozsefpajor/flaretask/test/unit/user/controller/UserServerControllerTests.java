package com.jozsefpajor.flaretask.test.unit.user.controller;

import com.jozsefpajor.flaretask.exception.NotFoundException;
import com.jozsefpajor.flaretask.user.controller.rest.server.v1.UserServerController;
import com.jozsefpajor.flaretask.user.model.User;
import com.jozsefpajor.flaretask.user.service.UserServiceIf;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserServerControllerTests {

    private UserServiceIf mockUserService;
    private UserServerController userServerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockUserService = Mockito.mock(UserServiceIf.class);
        userServerController = new UserServerController(mockUserService);
        mockMvc = MockMvcBuilders.standaloneSetup(userServerController).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/v1/users/1234"})
    public void testGetQuestionByOriginalId_mappingOk( String uri ) throws Exception {
        // given
        when(mockUserService.getUserByOriginalId(anyLong()))
                .thenReturn(new User());

        // when
        mockMvc.perform(get(uri))
                .andExpect(status().isOk());

        // then
        verify(mockUserService, times(1)).getUserByOriginalId(1234L);
    }

    @Test
    public void testGetQuestionByOriginalId_entityExists() throws Exception {
        // given
        User user = new User();
        when(mockUserService.getUserByOriginalId(anyLong()))
                .thenReturn(user);

        // when
        User result = userServerController.getUserByOriginalId(1234L);

        // then
        verify(mockUserService, times(1)).getUserByOriginalId(1234L);
        assertSame(user, result);
    }

    @Test
    public void testGetQuestionByOriginalId_entityNotFound() throws Exception {
        // given
        when(mockUserService.getUserByOriginalId(anyLong()))
                .thenReturn(null);

        // when - then
        Exception e = assertThrows(NotFoundException.class, ()
                -> userServerController.getUserByOriginalId(1234L));

        // then
        verify(mockUserService, times(1)).getUserByOriginalId(1234L);
        assertTrue(e.getMessage().contains("1234"));
    }
}
