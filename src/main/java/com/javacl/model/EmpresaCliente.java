package com.javacl.model;

import com.javacl.model.pessoa.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaCliente {
    private Long id;
    private Cliente cliente;
    private String cnpj;
    private String telefone;
    private String razaoSocial;
    private String nomeFantasia;
    private int tamanho;
}
