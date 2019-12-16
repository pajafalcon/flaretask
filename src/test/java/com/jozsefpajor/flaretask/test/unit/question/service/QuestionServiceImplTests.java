package com.jozsefpajor.flaretask.test.unit.question.service;

import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.model.Tag;
import com.jozsefpajor.flaretask.question.repository.QuestionRepositoryIf;
import com.jozsefpajor.flaretask.question.service.QuestionServiceImpl;
import com.jozsefpajor.flaretask.question.service.TagServiceIf;
import org.assertj.core.util.Lists;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author PJ
 */
public class QuestionServiceImplTests {

    private TagServiceIf mockTagService;
    private QuestionRepositoryIf mockQuestionRepository;
    private QuestionServiceImpl questionService;

    @BeforeEach
    public void setup() {
        mockTagService = Mockito.mock(TagServiceIf.class);
        mockQuestionRepository = Mockito.mock(QuestionRepositoryIf.class);

        questionService = new QuestionServiceImpl(mockQuestionRepository, mockTagService);

        // return the argument object
        when(mockQuestionRepository.save(any(Question.class)))
                .thenAnswer(new Answer<Question>() {
                    @Override
                    public Question answer( InvocationOnMock invocation ) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (Question) args[0];
                    }
                }
                );
    }

    @Test
    public void testSaveQuestion_TagNotExistsYet() throws Exception {
        // given
        Tag tag = new Tag("testTagName");
        Question question = Question.builder()
                .tags(Lists.list(tag))
                .build();

        when(mockTagService.getTagByTagName(anyString()))
                .thenReturn(null);
        when(mockTagService.saveTag(any(Tag.class)))
                .thenReturn(createTagWithId(-1234L, "testTagName"));

        // when
        Question savedQuestion = questionService.saveQuestion(question);

        // then
        verify(mockTagService, times(1)).saveTag(eq(tag));
        verify(mockQuestionRepository, times(1)).save(question);
        assertNotNull(savedQuestion.getTags());
        assertTrue(
                !savedQuestion.getTags().isEmpty());
        assertEquals(
                -1234L, savedQuestion.getTags().get(0).getId());
    }

    public void testSaveQuestion_UseExistingTag() throws Exception {
        // given
        Tag tag = new Tag("testTagName");
        Question question = Question.builder()
                .tags(Lists.list(tag))
                .build();

        Tag storedTag = createTagWithId(-98765L, "testTagName");

        when(mockTagService.getTagByTagName("testTagName"))
                .thenReturn(storedTag);

        // when
        Question savedQuestion = questionService.saveQuestion(question);

        // then
        verify(mockTagService, never()).saveTag(any());
        verify(mockQuestionRepository, times(1)).save(question);
        assertNotNull(savedQuestion.getTags());
        assertTrue(!savedQuestion.getTags().isEmpty());
        assertSame(storedTag, savedQuestion.getTags().get(0));
    }

    private Tag createTagWithId( long id, String testName ) {
        return Tag.builder()
                .tagName(testName)
                .id(id)
                .build();
    }

}
