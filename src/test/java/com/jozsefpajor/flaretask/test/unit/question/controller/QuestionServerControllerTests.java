package com.jozsefpajor.flaretask.test.unit.question.controller;

import com.jozsefpajor.flaretask.exception.NotFoundException;
import com.jozsefpajor.flaretask.question.controller.rest.v1.QuestionServerController;
import com.jozsefpajor.flaretask.question.controller.rest.v1.response.ServerResponseQuestion;
import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.model.Tag;
import com.jozsefpajor.flaretask.question.service.QuestionServiceIf;
import java.util.Date;
import java.util.List;
import org.assertj.core.util.Lists;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author PJ
 */
public class QuestionServerControllerTests {

    private QuestionServiceIf mockQuestionService;
    private QuestionServerController questionServerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockQuestionService = Mockito.mock(QuestionServiceIf.class);
        questionServerController = new QuestionServerController(mockQuestionService);
        mockMvc = MockMvcBuilders.standaloneSetup(questionServerController).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/v1/questions", "/api/v1/questions/"})
    public void testGetQuestions_availableUnderUris( String uri ) throws Exception {
        // given
        when(mockQuestionService.getAllQuestions()).thenReturn(Lists.list(new Question()));

        // when
        mockMvc.perform(get(uri))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/v1/questions", "/api/v1/questions/"})
    public void testGetQuestions_callsServiceAll( String uri ) throws Exception {
        // given
        when(mockQuestionService.getAllQuestions()).thenReturn(Lists.list(new Question()));

        // when
        mockMvc.perform(get(uri));

        // then
        verify(mockQuestionService, times(1)).getAllQuestions();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/v1/questions?tag=tag", "/api/v1/questions/?tag=tag"})
    public void testGetQuestions_callsServiceTag( String uri ) throws Exception {
        // given
        String tagName = "tag";
        when(mockQuestionService.getQuestrionsByTagName(anyString()))
                .thenReturn(Lists.list(new Question()));

        // when
        mockMvc.perform(get(uri));

        // then
        verify(mockQuestionService, times(1)).getQuestrionsByTagName(tagName);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "tag"})
    public void testGetQuestions_NotFoundExceptionNullEntityList( String tag ) throws Exception {
        // given
        when(mockQuestionService.getQuestrionsByTagName(anyString()))
                .thenReturn(null);

        // when - then
        assertThrows(NotFoundException.class, ()
                -> questionServerController.getQuestions(tag));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "tag"})
    public void testGetQuestions_NotFoundExceptionEmptyEntityList( String tag ) throws Exception {
        // given
        when(mockQuestionService.getQuestrionsByTagName(anyString()))
                .thenReturn(Lists.<Question>emptyList());

        // when - then
        assertThrows(NotFoundException.class, ()
                -> questionServerController.getQuestions(tag));
    }

    @Test
    public void testGetQuestions_tagWithMapper() throws Exception {
        // given
        String tagName = "tag";
        Question question = getMockQuestion();
        when(mockQuestionService.getQuestrionsByTagName(anyString()))
                .thenReturn(Lists.list(question));

        // when
        List<ServerResponseQuestion> responseQuestionList
                = questionServerController.getQuestions(tagName);

        // then
        verify(mockQuestionService, times(1)).getQuestrionsByTagName(tagName);
        assertNotNull(responseQuestionList);
        assertTrue(responseQuestionList.size() == 1);

        ServerResponseQuestion responseQuestion = responseQuestionList.get(0);
        assertTrue(mappingIsOk(responseQuestion));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/v1/questions/1"})
    public void testGetQuestionByOriginalId_EntityExists( String uri ) throws Exception {
        // given
        String tagName = "tag";
        when(mockQuestionService.getQuestionByOriginalId(anyLong()))
                .thenReturn(new Question());

        // when
        mockMvc.perform(get(uri))
                .andExpect(status().isOk());

        // then
        verify(mockQuestionService, times(1)).getQuestionByOriginalId(1L);
    }

    @Test
    public void testGetQuestionByOriginalId_EntityNotFound() throws Exception {
        // given
        when(mockQuestionService.getQuestionByOriginalId(anyLong()))
                .thenReturn(null);

        // when - then
        Exception e = assertThrows(NotFoundException.class, ()
                -> questionServerController.getQuestionByOriginalId(1234L));

        // then
        verify(mockQuestionService, times(1)).getQuestionByOriginalId(1234L);
        assertTrue(e.getMessage().contains("1234"));
    }

    @Test
    public void testDeleteQuestuin_EntityExists() throws Exception {
        // given
        String uri = "/api/v1/questions/1";
        when(mockQuestionService.getQuestionByOriginalId(anyLong()))
                .thenReturn(getMockQuestion());

        // when
        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent());

        // then
        verify(mockQuestionService, times(1)).deleteQuestionById(-4L);
    }

    @Test
    public void testDeleteQuestuin_EntityNotFound() throws Exception {
        // given
        when(mockQuestionService.getQuestionByOriginalId(anyLong()))
                .thenReturn(null);

        // when - then
        Exception e = assertThrows(NotFoundException.class, ()
                -> questionServerController.deleteQuestion(1234L));
        verify(mockQuestionService, never()).deleteQuestionById(anyLong());
        assertTrue(e.getMessage().contains("1234"));
    }

    private Question getMockQuestion() {
        return Question.builder()
                .createdAt(new Date(0L))
                .acceptedAnswerId(-1L)
                .answerCount(-2L)
                .askerUserId(-3L)
                .id(-4L)
                .originalId(-5L)
                .tags(Lists.list(new Tag("tag1"), new Tag("tag2")))
                .viewCount(-7L)
                .build();
    }

    private boolean mappingIsOk( ServerResponseQuestion q ) {
        return q.getCreationDate().equals(new Date(0L))
                && q.getAswered() == true
                && q.getAnswerCount() == -2L
                && q.getAskerId() == -3L
                && q.getId() == -5L // id in response is the originalid of the question
                && q.getTagNames().size() == 2
                && q.getTagNames().get(0).equals("tag1")
                && q.getTagNames().get(1).equals("tag2")
                && q.getViewCount() == -7L;
    }
}
