package com.example.serasa.dto.afinidade;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AfinidadeDTO {

    private String regiao;

    private List<String> estados;

}
