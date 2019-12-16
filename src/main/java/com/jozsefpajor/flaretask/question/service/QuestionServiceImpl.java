package com.jozsefpajor.flaretask.question.service;

import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.model.Tag;
import com.jozsefpajor.flaretask.question.repository.QuestionRepositoryIf;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Implementation of the QutestionServiceIf with persistence handled by SpringData.
 *
 */
@Service
public class QuestionServiceImpl implements QuestionServiceIf {

    private TagServiceIf tagService;
    private QuestionRepositoryIf questionRepository;

    @Autowired
    public QuestionServiceImpl( QuestionRepositoryIf questionRepository,
            TagServiceIf tagService ) {
        this.questionRepository = questionRepository;
        this.tagService = tagService;
    }

    public QuestionServiceImpl() {
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.getAll();
    }

    @Override
    public Question getQuestionById( Long id ) {
        return questionRepository.getById(id);
    }

    @Override
    public void deleteQuestionById( Long id ) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestrionsByTagName( String tagName ) {
        return questionRepository.getByTagName(tagName);
    }

    @Override
    @Transactional
    public Question saveQuestion( Question question ) {

        // Tag enities should be unique by tagName
        List<Tag> tags = question.getTags();
        for ( int i = 0; i < tags.size(); i++ ) {
            Tag tag = tags.get(i);
            Tag storedTag = tagService.getTagByTagName(tag.getTagName());
            if ( storedTag != null ) {
                tags.set(i, storedTag);
            } else {
                tags.set(i, tagService.saveTag(tag));
            }
        }

        return questionRepository.save(question);
    }

    @Override
    public List<Question> saveQuestions( List<Question> questionList ) {
        return questionList.stream()
                .map(this::saveQuestion)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQuestionById( List<Long> ids ) {
        ids.forEach(this::deleteQuestionById);
    }

    @Override
    public Question getQuestionByOriginalId( Long originalId ) {
        return questionRepository.getByOriginalId(originalId);
    }
}
