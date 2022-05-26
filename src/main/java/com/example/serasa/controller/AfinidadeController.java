package com.example.serasa.controller;

import com.example.serasa.dto.afinidade.AfinidadeDTO;
import com.example.serasa.service.AfinidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/afinidade")
@Tag(name = "Afinidade", description = "Regiões de afinidade")
public class AfinidadeController {

    private AfinidadeService afinidadeService;

    @Autowired
    public AfinidadeController(AfinidadeService afinidadeService) {
        this.afinidadeService = afinidadeService;
    }

    @PostMapping
    @Operation(summary = "Cadastra região de afinidades")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity post(@RequestBody AfinidadeDTO afinidadeDTO) {

        afinidadeService.criar(afinidadeDTO);
        return new ResponseEntity(HttpStatus.CREATED);

    }

}
