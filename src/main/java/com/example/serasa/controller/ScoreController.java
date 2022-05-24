package com.example.serasa.controller;

import com.example.serasa.dto.score.ScoreDTO;
import com.example.serasa.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private ScoreService service;

    @Autowired
    public ScoreController(ScoreService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ScoreDTO scoreDTO) {
        service.criar(scoreDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
