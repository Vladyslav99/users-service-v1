package com.testtask.usersservicev1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ControllerTest {

    @GetMapping
    public String test() {
        return "USERS";
    }
}
