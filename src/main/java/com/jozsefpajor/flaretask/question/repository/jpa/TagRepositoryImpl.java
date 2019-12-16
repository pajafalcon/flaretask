package com.jozsefpajor.flaretask.question.repository.jpa;

import com.jozsefpajor.flaretask.question.model.Tag;
import com.jozsefpajor.flaretask.question.repository.TagRepositoryIf;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepositoryImpl implements TagRepositoryIf {

    private static final Logger LOG = LoggerFactory.getLogger(TagRepositoryImpl.class);

    private TagJpaRepository jpaRepository;

    @Autowired
    public TagRepositoryImpl( TagJpaRepository jpaRepository ) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Set<Tag> findByTagNameIn( Collection<String> tagNames ) {
        return jpaRepository.findByTagNameIn(tagNames);
    }

    @Override
    public Tag findByTagName( String tagName ) {
        List<Tag> tagList = jpaRepository.findByTagName(tagName);
        if ( tagList.size() > 1 ) {
            LOG.error("Inconsistent data in database. Multiple TAG row with the same TAG_NAME: "
                    + tagName);
        }
        return tagList != null && !tagList.isEmpty() ? tagList.get(0) : null;
    }

    @Override
    public Tag saveTag( Tag tag ) {
        return jpaRepository.save(tag);
    }

}
