package com.hp.feTest.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {

    private Log logger = LogFactory.getLog(getClass());


    @RequestMapping(value = "/launchMain.htm", method = RequestMethod.GET)

    public String getStartPage() {
        return "startPanel";
    }

}
