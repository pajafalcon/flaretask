package com.jozsefpajor.flaretask.question.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jozsefpajor.flaretask.question.controller.graphql.dto.QuestionDTO;
import com.jozsefpajor.flaretask.question.controller.graphql.dto.TagDTO;
import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.repository.QuestionRepositoryIf;
import com.jozsefpajor.flaretask.question.service.QuestionServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Query implements GraphQLQueryResolver {

    private QuestionServiceIf questionService;

    @Autowired
    public Query(QuestionServiceIf questionService) {
        this.questionService = questionService;
    }

    public Iterable<QuestionDTO> findAllQuestion(){
       return questionService.getAllQuestions().stream()
                .map(QuestionDTO :: fromModel)
                .collect(Collectors.toList());
    }

    public Iterable<QuestionDTO> findQuestionsByTag(TagDTO tag){
        return questionService.getQuestrionsByTagName(tag.getTagName()).stream()
                .map(QuestionDTO :: fromModel)
                .collect(Collectors.toList());
    }

    public Boolean deleteQuestion(Long id){
        return null;
    }
}
