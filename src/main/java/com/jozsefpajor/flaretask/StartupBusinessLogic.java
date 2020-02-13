package com.jozsefpajor.flaretask;

import com.jozsefpajor.flaretask.question.controller.rest.DBInitControllerIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * Class to execute business logic task right after startup.
 */
@Component
public class StartupBusinessLogic {

    private DBInitControllerIf dbInitController;

    @Autowired
    public StartupBusinessLogic( DBInitControllerIf dbInitController ) {
        this.dbInitController = dbInitController;
    }

    @EventListener
    public void onApplicationEvent( ApplicationReadyEvent event ) {
        dbInitController.initLatestQuestions(20);
    }

}
