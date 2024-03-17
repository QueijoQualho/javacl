package com.javacl.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plano {
    private List<Produto> listaProdutos = new ArrayList<Produto>();
    private String nomeFantasia;
    private TipoPlano tipoPlano;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private double valor;

    public Plano(String nomeFantasia, TipoPlano tipoPlano) {
        this.nomeFantasia = nomeFantasia;
        this.dataInicio = LocalDate.now();
        this.tipoPlano = tipoPlano;

        switch (tipoPlano) {
            case ANUAL:
                this.dataFinal = dataInicio.plusYears(1);
                break;
            case SEMESTRAL:
                this.dataFinal = dataInicio.plusMonths(6);
                break;
            case TRIMESTRAL:
                this.dataFinal = dataInicio.plusMonths(3);
                break;
            case MENSAL:
                this.dataFinal = dataInicio.plusMonths(1);
                break;
            default:
                throw new IllegalArgumentException("Tipo de plano inválido");
        }
        
        this.calcValor();

    }

    /* Method */
    public void addProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public void calcValor() {
        double valor = 0;
        int mesAtual = LocalDate.now().getMonthValue();
    
        for (Produto produto : listaProdutos) {
            valor += produto.getPreco();
        }

        valor *= mesAtual;
    
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
