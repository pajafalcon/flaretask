package com.jozsefpajor.flaretask.test.unit.question.controller;

import com.jozsefpajor.flaretask.question.controller.client.QuestionClientControllerRest;
import com.jozsefpajor.flaretask.question.controller.client.response.QuestionClientResponse;
import com.jozsefpajor.flaretask.question.controller.client.response.QuestionClientResponseMapper;
import com.jozsefpajor.flaretask.question.model.Question;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.web.client.RestTemplate;

public class QuestionClientControllerRestTests {

    private QuestionClientControllerRest questionClient;

    private QuestionClientResponseMapper mockMapper;
    private RestTemplate restTemplate;

    private ArrayList<Question> mockQuestionList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        mockMapper = mock(QuestionClientResponseMapper.class);
        initMapperMockSingleton();

        restTemplate = mock(RestTemplate.class);

        questionClient = new QuestionClientControllerRest(restTemplate);
    }

    @AfterEach
    public void resetSingleton() throws Exception {
        Field instance = QuestionClientResponseMapper.class.getDeclaredField("INSTANCE");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    private void initMapperMockSingleton() {
        try {
            Field instance = QuestionClientResponseMapper.class.getDeclaredField("INSTANCE");
            setFinalStatic(instance, mockMapper);
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    static void setFinalStatic( Field field, Object newValue ) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

    @Test
    public void testGetLatestQuestions_restTemplateCall() throws Exception {
        // given
        String url = "https://api.stackexchange.com/2.2/questions/featured/"
                + "?pagesize=1234&order=desc&sort=creation&site=stackoverflow";

        when(restTemplate.getForObject(anyString(), any(), anyMap()))
                .thenReturn(new QuestionClientResponse());

        // when
        List<Question> result = questionClient.getLatestQuestions(1234);

        // then
        verify(restTemplate, times(1)).getForObject(any(), eq(QuestionClientResponse.class), anyMap());
    }

}
