package com.jozsefpajor.flaretask.question.service;

import com.jozsefpajor.flaretask.question.model.Question;
import java.util.List;

/**
 *
 * @author PJ
 */
public interface QuestionServiceIf {

    List<Question> getAllQuestions();

    Question getQuestionById( Long id );

    void deleteQuestionById( Long id );

    void deleteQuestionById( List<Long> id );

    List<Question> getQuestrionsByTagName( String tagName );

    List<Question> saveQuestions( List<Question> questionList );

    Question saveQuestion( Question question );

    public Question getQuestionByOriginalId( Long originalId );
}
