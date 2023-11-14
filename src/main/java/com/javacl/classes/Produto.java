package com.javacl.classes;

import com.javacl.classes.interfaces.Nomeavel;

public class Produto implements Nomeavel{
    private int id;
    private String nome;
    private double preco;

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

}
