package com.javacl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpresaCliente {
    private Long id;
    private Long idCliente;
    private String cnpj;
    private String telefone;
    private String razaoSocial;
    private String nomeFantasia;
    private int tamanho;
}
