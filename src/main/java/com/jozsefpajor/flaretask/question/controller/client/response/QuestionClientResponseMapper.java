package com.jozsefpajor.flaretask.question.controller.client.response;

import com.jozsefpajor.flaretask.question.model.Question;
import com.jozsefpajor.flaretask.question.model.Tag;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionClientResponseMapper {

    private static final QuestionClientResponseMapper INSTANCE = new QuestionClientResponseMapper();
    private static final int MULTIPLIER_SECOND_TO_MILLISECOND = 1000;

    public static QuestionClientResponseMapper getInstance() {
        return INSTANCE;
    }

    public Question mapResponseToEntity( QuestionClientResponseQuestion response ) {
        return Question.builder()
                .answerCount(response.getAnswerCount())
                .acceptedAnswerId(response.getAcceptedAnswerId())
                .askerUserId(response.getOwner().getUserId())
                .createdAt(parseDateFromEpochSeconds(response.getCreationDate()))
                .originalId(response.getQuestionId())
                .viewCount(response.getViewCount())
                .tags(createTags(response.getTags()))
                .build();
    }

    public List<Question> mapResponsesToEntities( List<QuestionClientResponseQuestion> questions ) {
        return questions.stream()
                .map(this::mapResponseToEntity)
                .collect(Collectors.toList());
    }

    private Date parseDateFromEpochSeconds( Long dateEpochSeconds ) {
        return new Date(MULTIPLIER_SECOND_TO_MILLISECOND * dateEpochSeconds);
    }

    private List<Tag> createTags( List<String> tags ) {
        return tags.stream()
                .map(tagName -> new Tag(tagName))
                .collect(Collectors.toList());
    }

}
