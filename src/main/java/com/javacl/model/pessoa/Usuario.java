package com.javacl.model.pessoa;

import com.javacl.model.Endereco;

public abstract class Usuario {
    protected String nome;
    protected String telefone;
    protected String email;
    protected String cpf;
    protected String cargo;
    protected String senha;
    protected Endereco endereco;

    public Usuario(String nome, String telefone, String email, String cpf, String cargo, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.cargo = cargo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public String getSenha() {
        return senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    

}
