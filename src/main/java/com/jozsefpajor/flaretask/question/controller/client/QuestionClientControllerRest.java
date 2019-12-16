package com.jozsefpajor.flaretask.question.controller.client;

import com.jozsefpajor.flaretask.question.controller.client.response.QuestionClientResponse;
import com.jozsefpajor.flaretask.question.controller.client.response.QuestionClientResponseMapper;
import com.jozsefpajor.flaretask.question.controller.client.response.QuestionClientResponseQuestion;
import com.jozsefpajor.flaretask.question.model.Question;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author PJ
 */
@Controller
public class QuestionClientControllerRest implements QuestionClientControllerIf {

    private static final String QUESTION_GET_URL
            = "https://api.stackexchange.com/2.2/questions/featured/"
            + "?pagesize={pagesize}&order={order}&sort={sort}&site={site}";

    private static final String PARAM_NAME_SITE = "site";
    private static final String PARAM_NAME_PAGESIZE = "pagesize";
    private static final String PARAM_NAME_ORDER = "order";
    private static final String PARAM_NAME_SORT = "sort";

    private static final String PARAM_VALUE_SITE = "stackoverflow";
    private static final String PARAM_VALUE_ORDER = "desc";
    private static final String PARAM_VALUE_SORT = "creation";

    private RestTemplate restTemplate;

    @Autowired
    public QuestionClientControllerRest( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Question> getLatestQuestions( int numberOfQuestions ) {
        Map<String, String> params = buildParams(numberOfQuestions);
        QuestionClientResponse responseWrapper
                = restTemplate.getForObject(QUESTION_GET_URL, QuestionClientResponse.class, params);
        return createQuestionModels(responseWrapper.getItems());
    }

    private Map<String, String> buildParams( int numberOfQuestions ) {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_NAME_SITE, PARAM_VALUE_SITE);
        params.put(PARAM_NAME_ORDER, PARAM_VALUE_ORDER);
        params.put(PARAM_NAME_SORT, PARAM_VALUE_SORT);
        params.put(PARAM_NAME_PAGESIZE, String.valueOf(numberOfQuestions));

        return params;
    }

    private List<Question> createQuestionModels( List<QuestionClientResponseQuestion> responseQuestions ) {
        List<Question> questionModels
                = QuestionClientResponseMapper.getInstance().mapResponsesToEntities(responseQuestions);
        return questionModels;
    }

}
