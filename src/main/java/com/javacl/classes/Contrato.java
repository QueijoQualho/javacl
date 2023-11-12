package com.javacl.classes;

import com.javacl.classes.pessoa.Funcionario;

public class Contrato {
    private Funcionario funcio;
    private Empresa_cliente empresa;
    private Plano plano;

    public Contrato(Funcionario funcio, Empresa_cliente empresa, Plano plano) {
        this.funcio = funcio;
        this.empresa = empresa;
        this.plano = plano;
    }

    public Funcionario getFuncio() {
        return funcio;
    }

    public Empresa_cliente getEmpresa() {
        return empresa;
    }

    public Plano getPlano() {
        return plano;
    }

    /* Methods */
    public void pagar(String formaPagamento) {
        switch (formaPagamento.toLowerCase()) {
            case "credito":
            case "crédito":
                System.out.println("Processando pagamento com cartão de crédito...");
                break;
            case "boleto":
                System.out.println("Processando pagamento com boleto bancário...");
                break;
            default:
                System.out.println("Forma de pagamento não suportada.");
                break;
        }
    }
}
