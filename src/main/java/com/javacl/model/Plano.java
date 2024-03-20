package com.javacl.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.enums.TipoPlano;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plano {
    private Long id;
    private String nomeFantasia;
    private TipoPlano tipoPlano;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private double valor;
    private List<Produto> listaProdutos = new ArrayList<Produto>();


    public Plano(String nomeFantasia, TipoPlano tipoPlano, Long id) {
        this.id = id;
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

    }

    /* Method */
    public void addProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public void calcValor() {
        double valorTotal = 0;

        if (dataInicio.isBefore(dataFinal)) {
            long meses = ChronoUnit.MONTHS.between(dataInicio, dataFinal);
            for (Produto produto : listaProdutos) {
                valorTotal += produto.getPreco() * meses;
            }
        }

        this.valor = valorTotal;
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
