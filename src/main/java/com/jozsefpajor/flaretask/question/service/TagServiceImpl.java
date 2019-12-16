package com.jozsefpajor.flaretask.question.service;

import com.jozsefpajor.flaretask.question.model.Tag;
import com.jozsefpajor.flaretask.question.repository.TagRepositoryIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author PJ
 */
@Service
public class TagServiceImpl implements TagServiceIf {

    private TagRepositoryIf tagRepository;

    @Autowired
    public TagServiceImpl( TagRepositoryIf tagRepository ) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getTagByTagName( String tagName ) {
        return tagRepository.findByTagName(tagName);
    }

    @Override
    public Tag saveTag( Tag tag ) {
        return tagRepository.saveTag(tag);
    }

}
