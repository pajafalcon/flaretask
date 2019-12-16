package com.jozsefpajor.flaretask.user.controller.client;

import com.jozsefpajor.flaretask.user.model.User;

/**
 *
 * @author PJ
 */
public interface UserClientControllerIf {

    User getUserByOriginalId( Long id );
}
