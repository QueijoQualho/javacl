package com.javacl.classes;

import com.javacl.classes.pessoa.Cliente;

public class Empresa_cliente {
    private Cliente representante;
    private String cnpj;
    private String telefone;
    private String razaoSocial;
    private String nomeFantasia;
    private int tamanho;

    public Empresa_cliente(Cliente representante, String cnpj, String telefone,
            String razaoSocial, String nomeFantasia, int tamanho) {
        this.representante = representante;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.tamanho = tamanho;
    }

    public Cliente getRepresentante() {
        return representante;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public int getTamanho() {
        return tamanho;
    }
}
