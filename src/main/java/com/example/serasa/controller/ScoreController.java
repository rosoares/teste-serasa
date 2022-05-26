package com.example.serasa.controller;

import com.example.serasa.dto.score.ScoreDTO;
import com.example.serasa.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
@Tag(name = "Score", description = "Faixas de score")
public class ScoreController {

    private ScoreService service;

    @Autowired
    public ScoreController(ScoreService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastra nova faixa de score")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity post(@RequestBody ScoreDTO scoreDTO) {
        service.criar(scoreDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
