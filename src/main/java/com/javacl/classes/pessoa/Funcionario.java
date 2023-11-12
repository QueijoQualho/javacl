package com.javacl.classes.pessoa;

public class Funcionario extends Usuario {
    private double salario;

    public Funcionario(String nome, String telefone, String email, String cpf, String cargo, String senha,
            double salario) {
        super(nome, telefone, email, cpf, cargo, senha);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

}
