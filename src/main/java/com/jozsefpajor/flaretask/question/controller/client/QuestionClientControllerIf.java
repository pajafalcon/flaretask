package com.jozsefpajor.flaretask.question.controller.client;

import com.jozsefpajor.flaretask.question.model.Question;
import java.util.List;

/**
 *
 * @author PJ
 */
public interface QuestionClientControllerIf {

    List<Question> getLatestQuestions( int numberOfQuestions );
}
