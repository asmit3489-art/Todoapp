
package com.example.firstapp;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}