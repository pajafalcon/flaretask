package com.jozsefpajor.flaretask.user.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jozsefpajor.flaretask.question.controller.rest.v1.QuestionDTO;
import com.jozsefpajor.flaretask.user.model.User;
import com.jozsefpajor.flaretask.user.service.UserServiceIf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Query implements GraphQLQueryResolver {

    private UserServiceIf userService;

    @Autowired
    public Query(UserServiceIf userService) {
        this.userService = userService;
    }

    public User getUserById(Long id) {
       return userService.getUserByOriginalId(id);
    }

}
