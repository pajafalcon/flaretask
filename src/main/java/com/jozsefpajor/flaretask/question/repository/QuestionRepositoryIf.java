package com.jozsefpajor.flaretask.question.repository;

import com.jozsefpajor.flaretask.question.model.Question;
import java.util.List;

/**
 *
 * @author PJ
 */
public interface QuestionRepositoryIf {

    List<Question> getAll();

    Question getById( Long id );

    void deleteById( Long id );

    List<Question> getByTagName( String tagName );

    List<Question> saveAll( List<Question> questionList );

    Question save( Question question );

    Question getByOriginalId( Long originalId );

}
