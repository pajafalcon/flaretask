package com.jozsefpajor.flaretask.question.repository.jpa;

import com.jozsefpajor.flaretask.question.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * SpringData interface to
 *
 */
@Repository
public interface QuestionJpaRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM QUESTIONS_BY_TAGS WHERE TAG_NAME = ?1", nativeQuery = true)
    List<Question> findByTagName( String tagName );

    @Modifying
    @Query(value = "delete from QUESTION q where q.id IN (:ids)", nativeQuery = true)
    void deleteQuestionsWithIds( List<Long> ids );

    Question findByOriginalId( Long originalId );
}
