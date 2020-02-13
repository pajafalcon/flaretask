package com.jozsefpajor.flaretask.question.controller.rest;

import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.service.QuestionServiceIf;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.jozsefpajor.flaretask.question.controller.rest.client.QuestionClientControllerIf;

/**
 *
 * @author PJ
 */
@Controller
public class DBInitControllerImpl implements DBInitControllerIf {

    private static final Logger LOG
            = LoggerFactory.getLogger(DBInitControllerImpl.class);

    private QuestionClientControllerIf questionClient;
    private QuestionServiceIf questionService;

    @Autowired
    public DBInitControllerImpl( QuestionClientControllerIf questionClient, QuestionServiceIf questionService ) {
        this.questionClient = questionClient;
        this.questionService = questionService;
    }

    @Override
    public void initLatestQuestions( int numberOfQuestions ) {
        LOG.info("Deleting existing questions");
        deleteExistingQuestions();
        LOG.info("Getting questions from Stackexchange API");
        List<Question> questions = questionClient.getLatestQuestions(20);
        LOG.info("Populating the database with Questions.");
        questionService.saveQuestions(questions);
        LOG.info("Initializing latest questions was successful");
    }

    private void deleteExistingQuestions() {
        List<Question> allQuestions = questionService.getAllQuestions();
        List<Long> allId = allQuestions.stream()
                .map(q -> q.getId()).collect(Collectors.toList());
        questionService.deleteQuestionById(allId);
    }
}
