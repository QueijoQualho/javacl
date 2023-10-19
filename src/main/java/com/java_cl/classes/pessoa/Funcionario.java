package com.java_cl.classes.pessoa;

public class Funcionario extends Usuario {
    private double salario;

    public Funcionario(String nome, String telefone, String email, String cpf, String cargo, double salario) {
        super(nome, telefone, email, cpf, cargo);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

}
