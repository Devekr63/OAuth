package com.appsdeveloperblog.ress.PhotoApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class PhotoController {

    @GetMapping("/photos")
    public String getPhotos(){
        return "user photos....";
    }
}
