package com.jozsefpajor.flaretask.question.repository.jpa;

import com.jozsefpajor.flaretask.question.model.Tag;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PJ
 */
@Repository
public interface TagJpaRepository extends JpaRepository<Tag, Long> {

    public Set<Tag> findByTagNameIn( Collection<String> tagNames );

    public List<Tag> findByTagName( String tagName );

}
