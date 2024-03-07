package com.javacl.model.pessoa;

import java.util.ArrayList;
import java.util.List;

import com.javacl.model.Endereco;

public abstract class Usuario {
    protected String nome;
    protected String telefone;
    protected String email;
    protected String cpf;
    protected String cargo;
    protected String senha;
    protected List<Endereco> endereco;

    public Usuario(String nome, String telefone, String email, String cpf, String cargo, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.cargo = cargo;
        this.senha = senha;

        this.endereco = new ArrayList<Endereco>();
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

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    /* Methods */
    public void addEndereco(Endereco endereco) {
        this.endereco.add(endereco);
    }

    

}
