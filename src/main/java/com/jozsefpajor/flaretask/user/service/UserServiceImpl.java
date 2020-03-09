package com.jozsefpajor.flaretask.user.service;

import com.jozsefpajor.flaretask.config.CacheConfig;
import com.jozsefpajor.flaretask.user.controller.rest.client.UserClientControllerIf;
import com.jozsefpajor.flaretask.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author PJ
 */
@Service
public class UserServiceImpl implements UserServiceIf {

    private UserClientControllerIf userClientController;

    @Autowired
    public UserServiceImpl( UserClientControllerIf userClientController ) {
        this.userClientController = userClientController;
    }

    @Override
    @Cacheable(CacheConfig.USERS)
    public User getUserByOriginalId( Long id ) {
        return userClientController.getUserByOriginalId(id);
    }

}
