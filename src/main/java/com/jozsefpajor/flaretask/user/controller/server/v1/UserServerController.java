package com.jozsefpajor.flaretask.user.controller.server.v1;

import com.jozsefpajor.flaretask.exception.NotFoundException;
import com.jozsefpajor.flaretask.user.model.User;
import com.jozsefpajor.flaretask.user.service.UserServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PJ
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserServerController {

    private UserServiceIf userService;

    @Autowired
    public UserServerController( UserServiceIf userService ) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserByOriginalId( @PathVariable(value = "id") Long id )
            throws NotFoundException {

        User user = userService.getUserByOriginalId(id);
        if ( user == null ) {
            throw new NotFoundException("User with id '" + id + "' not found.");
        }
        return user;
    }

}
