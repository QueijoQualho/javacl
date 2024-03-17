package com.javacl.model.pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Funcionario extends Usuario {
    private double salario;
}
