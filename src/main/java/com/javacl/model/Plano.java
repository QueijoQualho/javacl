package com.javacl.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plano {
    private List<Produto> listaProdutos = new ArrayList<Produto>();

    private String nomeFantasia;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private double valor;

    public Plano(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getNome() {
        return nomeFantasia;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public double getValor() {
        return valor;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    /* Method */
    public void addProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public void tipoPlano(String tipoPlano) {
        this.dataInicio = LocalDate.now();
        switch (tipoPlano.toLowerCase()) {
            case "anual":
                this.valor *= 12;
                this.dataFinal = this.dataInicio.plusMonths(12);
                break;
            case "mensal":
                this.dataFinal = this.dataInicio.plusMonths(1);
                break;
        }
    }

    public void calcValor() {
        double valor = 0;
        for (Produto produto : listaProdutos) {
            valor += produto.getPreco();
        }
        this.valor = valor;
    }

    @Override
    public String toString() {
        /* https://www.devmedia.com.br/a-classe-stringbuilder-em-java/25609 */
        StringBuilder sb = new StringBuilder();
        sb.append("Produtos: \n");
        for (Produto produto : listaProdutos) {
            sb.append(" - ").append(produto.getNome()).append(", Preço: R$").append(produto.getPreco()).append("\n");
        }
        sb.append("Data de Início: ").append(dataInicio).append("\n");
        sb.append("Data Final: ").append(dataFinal).append("\n");
        sb.append("Valor Total: R$").append(valor).append("\n");
        return sb.toString();
    }

}
