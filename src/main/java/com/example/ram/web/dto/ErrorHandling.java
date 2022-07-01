package com.example.ram.web.dto;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorHandling implements ErrorController {
    @RequestMapping("/error")
    public String errorRedirect() {
        return "customErrorPage";
    }

    @RequestMapping("/")
    public String errorRedirectTwo() {
        return "customErrorPage";
    }
}
