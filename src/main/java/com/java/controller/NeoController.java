package com.java.controller;

import com.java.neo4j.neoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/neo")
public class NeoController {

    @Autowired
    private neoService neoService;

    @PostMapping("/save")
    public void create(@RequestBody String id){
        this.neoService.create(id);
    }
}
