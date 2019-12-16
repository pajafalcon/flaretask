package com.jozsefpajor.flaretask.question.service;

import com.jozsefpajor.flaretask.question.model.Tag;

/**
 *
 * @author PJ
 */
public interface TagServiceIf {

    Tag getTagByTagName( String tagName );

    Tag saveTag( Tag tag );

}
