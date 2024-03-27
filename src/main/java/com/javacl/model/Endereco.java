package com.javacl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private Long id;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
}
