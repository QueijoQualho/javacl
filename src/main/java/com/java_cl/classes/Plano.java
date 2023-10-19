package com.java_cl.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plano {
    private List<Produto> listaProdutos;
    private String nomeFantasia;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private double valor;

    public Plano(String nomeFantasia, LocalDate dataInicio, LocalDate dataFinal) {
        this.nomeFantasia = nomeFantasia;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;

        listaProdutos = new ArrayList<Produto>();
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    } 

    /* Method */
    public void addProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public void tipoPlano(String tipoPlano) {
        switch(tipoPlano.toLowerCase()){
            case "anual":
                this.valor = planoAnual();
                break;
            case "mensal":
                break;
        }
    }

    public void calcValor() {
        double valor = 0;
        for (Produto produto : listaProdutos) {
            valor += produto.getPreco();
        }
        this.setValor(valor);
    }
    
    public double planoAnual() {
        return this.valor * 12;
    }
}
