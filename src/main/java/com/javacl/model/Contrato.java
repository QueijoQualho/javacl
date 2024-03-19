package com.javacl.model;

import com.javacl.model.enums.TipoPagamento;
import com.javacl.model.pessoa.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Contrato {
    private Long id;
    private Long idPlano;
    private Funcionario funcio;
    private EmpresaCliente empresa;
    private TipoPagamento tipoPagamento;
}
