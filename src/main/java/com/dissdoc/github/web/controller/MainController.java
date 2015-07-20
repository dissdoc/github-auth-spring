package com.dissdoc.github.web.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by KMukhov on 16.07.2015.
 */
@RestController
@RequestMapping("/welcome")
public class MainController {

    @RequestMapping(value = "")
    public String printWelcome(ModelMap model) {
        return "github";
    }
}
