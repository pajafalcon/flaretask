package com.jozsefpajor.flaretask.user.service;

import com.jozsefpajor.flaretask.user.model.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author PJ
 */
public interface UserServiceIf {

    User getUserByOriginalId( Long id );
}
