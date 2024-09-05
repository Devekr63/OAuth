package com.appsdeveloperblog.ws.api.ResourceServer.controller;

import com.appsdeveloperblog.ws.api.ResourceServer.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    Environment env;
    @GetMapping("/status/check")
    public String checkStatus() {
        return "Working on port no : "+env.getProperty("local.server.port");
    }

//    @Secured("ROLE_developer")
    @PreAuthorize("hasRole('developer') and #id == #jwt.subject")
    @DeleteMapping(path="/{id}")
    public String deleteDeveloper(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "The developer profile has been deleted : "+id+" and JWT subject "+jwt.getSubject();
    }

    @PostAuthorize("returnObject.id == #jwt.subject")
    @GetMapping(path="/{id}")
    public User getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new User("Devendra", "Kumar", "c464f393-65ff-49b6-8c91-23f1411902a0");
    }
}
