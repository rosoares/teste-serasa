package com.example.serasa.controller;

import com.example.serasa.dto.afinidade.AfinidadeDTO;
import com.example.serasa.service.AfinidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/afinidade")
public class AfinidadeController {

    private AfinidadeService afinidadeService;

    @Autowired
    public AfinidadeController(AfinidadeService afinidadeService) {
        this.afinidadeService = afinidadeService;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody AfinidadeDTO afinidadeDTO) {

        afinidadeService.criar(afinidadeDTO);
        return new ResponseEntity(HttpStatus.CREATED);

    }

}
