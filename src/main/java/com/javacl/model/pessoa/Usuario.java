package com.javacl.model.pessoa;

import java.util.List;

import com.javacl.model.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class Usuario {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String cargo;
    private String senha;
    private List<Endereco> listaEnderecos;

}
