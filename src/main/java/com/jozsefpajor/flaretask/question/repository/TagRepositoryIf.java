package com.jozsefpajor.flaretask.question.repository;

import com.jozsefpajor.flaretask.question.model.Tag;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author PJ
 */
public interface TagRepositoryIf {

    Set<Tag> findByTagNameIn( Collection<String> tagNames );

    Tag findByTagName( String tagNames );

    Tag saveTag( Tag tag );
}
