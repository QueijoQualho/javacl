package com.javacl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto{
    private Long id;
    private Long idPlano;
    private String nome;
    private double preco;

}
