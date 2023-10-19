package com.java_cl.classes;

import com.java_cl.classes.pessoa.Representante;

public class Empresa_cliente {
    private Representante representante;
    private String cnpj;
    private String telefone;
    private String razaoSocial;
    private String nomeFantasia;
    private String tamanho;

    public Empresa_cliente(Representante representante, String cnpj, String telefone,
            String razaoSocial, String nomeFantasia, String tamanho) {
        this.representante = representante;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.tamanho = tamanho;
    }

    public Representante getRepresentante() {
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

    public String getTamanho() {
        return tamanho;
    }
}
