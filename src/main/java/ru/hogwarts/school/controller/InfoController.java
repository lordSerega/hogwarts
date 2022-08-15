package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
public class InfoController {

    @Value("${server.port}" )
    private String port;

    @PostMapping(value = "/getPort")
    public String getPort(){
        return port;
    }
}
