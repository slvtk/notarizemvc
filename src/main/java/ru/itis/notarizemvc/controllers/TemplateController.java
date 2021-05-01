package ru.itis.notarizemvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class TemplateController {
    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "exception/access_denied";
    }
}
