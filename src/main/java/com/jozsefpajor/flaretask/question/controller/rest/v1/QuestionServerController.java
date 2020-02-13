package com.jozsefpajor.flaretask.question.controller.rest.v1;

import com.jozsefpajor.flaretask.exception.NotFoundException;
import com.jozsefpajor.flaretask.question.controller.rest.v1.response.ServerResponseQuestion;
import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.model.Tag;
import com.jozsefpajor.flaretask.question.service.QuestionServiceIf;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PJ
 */
@RestController
@RequestMapping("/api/v1/questions")
public class QuestionServerController {

    private final QuestionServiceIf questionService;

    @Autowired
    public QuestionServerController( QuestionServiceIf questionService ) {
        this.questionService = questionService;
    }

    @GetMapping(value = {"", "/"})
    public List<ServerResponseQuestion> getQuestions(
            @RequestParam(value = "tag", required = false) String tag )
            throws NotFoundException {

        List<Question> questions;

        if ( tag != null && !tag.isEmpty() ) {
            questions = questionService.getQuestrionsByTagName(tag);
        } else {
            questions = questionService.getAllQuestions();
        }

        if ( questions == null || questions.isEmpty() ) {
            String message = new StringBuilder("No question found")
                    .append(tag == null || tag.isEmpty() ? "." : " with tag '" + tag + "'.")
                    .toString();

            throw new NotFoundException(message);
        }

        List<ServerResponseQuestion> questionResponseList = mapQuestionList(questions);
        return questionResponseList;
    }

    @GetMapping("/{originalId}")
    public ServerResponseQuestion getQuestionByOriginalId(
            @PathVariable(value = "originalId") Long originalId ) throws Exception {

        Question question = questionService.getQuestionByOriginalId(originalId);

        if ( question != null ) {
            return mapQuestion(question);
        } else {
            throw new NotFoundException("Question with id '" + originalId + "' not found.");
        }
    }

    @DeleteMapping("/{originalId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteQuestion( @PathVariable(value = "originalId") Long originalId )
            throws NotFoundException {

        Question question = questionService.getQuestionByOriginalId(originalId);

        if ( question != null ) {
            questionService.deleteQuestionById(question.getId());
        } else {
            throw new NotFoundException("Question with id '" + originalId + "' not found.");
        }
    }

    private List<ServerResponseQuestion> mapQuestionList( List<Question> questions ) {
        return questions.stream()
                .map(this::mapQuestion)
                .collect(Collectors.toList());
    }

    private ServerResponseQuestion mapQuestion( Question question ) {
        return ServerResponseQuestion.builder()
                .answerCount(question.getAnswerCount())
                .askerId(question.getAskerUserId())
                .aswered(isAnswered(question))
                .creationDate(question.getCreatedAt())
                .id(question.getOriginalId())
                .tagNames(mapTags(question.getTags()))
                .viewCount(question.getViewCount())
                .build();
    }

    private List<String> mapTags( List<Tag> tags ) {
        return tags.stream()
                .map(tag -> tag.getTagName())
                .collect(Collectors.toList());
    }

    private Boolean isAnswered( Question question ) {
        return null != question.getAcceptedAnswerId();
    }
}
