package com.jozsefpajor.flaretask.question.repository.jpa;

import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.repository.QuestionRepositoryIf;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PJ
 */
@Repository
public class QuestionRepositoryImpl implements QuestionRepositoryIf {

    QuestionJpaRepository questionJpa;
    TagJpaRepository tagJpa;

    @Autowired
    public QuestionRepositoryImpl( QuestionJpaRepository questionJpa, TagJpaRepository tagJpa ) {
        this.questionJpa = questionJpa;
        this.tagJpa = tagJpa;
    }

    @Override
    public List<Question> getAll() {
        return questionJpa.findAll();
    }

    @Override
    public Question getById( Long id ) {
        return questionJpa.findById(id).orElse(null);
    }

    @Override
    public void deleteById( Long id ) {
        questionJpa.deleteById(id);
    }

    @Override
    public List<Question> getByTagName( String tagName ) {
        return questionJpa.findByTagName(tagName);
    }

    @Override
    public List<Question> saveAll( List<Question> questionList ) {
        return questionJpa.saveAll(questionList);
    }

    @Override
    public Question save( Question question ) {
        return questionJpa.save(question);
    }

    @Override
    public Question getByOriginalId( Long originalId ) {
        return questionJpa.findByOriginalId(originalId);
    }

}
