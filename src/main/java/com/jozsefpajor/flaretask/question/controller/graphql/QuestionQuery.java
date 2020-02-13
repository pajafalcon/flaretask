package com.jozsefpajor.flaretask.question.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jozsefpajor.flaretask.question.controller.graphql.dto.QuestionDTO;

import java.util.List;

public class QuestionQuery implements GraphQLQueryResolver {

    private List<QuestionDTO> getQuestion() {
        return List.of(new QuestionDTO());
    }

}
