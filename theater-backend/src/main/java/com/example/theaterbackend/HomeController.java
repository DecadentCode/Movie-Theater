package com.example.theaterbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // visible to all
    @GetMapping("/")
    public String landing() {
        return "Everyone can see this.";
    }

    @GetMapping("/user")
    public String home() {
        return "Customer view.";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Employee view.";
    }

}
