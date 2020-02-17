package com.jozsefpajor.flaretask.question.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
    public Boolean deleteQuestion(Long id){
        return null;
    }


}
