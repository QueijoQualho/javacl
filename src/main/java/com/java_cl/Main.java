package com.java_cl;

import java.time.LocalDate;

import com.java_cl.classes.Contrato;
import com.java_cl.classes.Empresa_cliente;
import com.java_cl.classes.Endereco;
import com.java_cl.classes.Plano;
import com.java_cl.classes.Produto;
import com.java_cl.classes.SistemaLogin;
import com.java_cl.classes.pessoa.Funcionario;
import com.java_cl.classes.pessoa.Representante;

public class Main {
    public static void main(String[] args) {
        Representante representante = new Representante("João", "123-456-7890", "joao@email.com", "12345678901", "Vendedor", "123");
        System.out.println("Representante: " + representante.getNome());

        Empresa_cliente empresa = new Empresa_cliente(representante, "1234567890", "987-654-3210", "Minha Empresa", "ME", "Grande");
        System.out.println("Empresa Cliente: " + empresa.getNomeFantasia());

        Plano plano = new Plano("Meu Plano", LocalDate.now(), LocalDate.now().plusMonths(12));
        System.out.println("Plano: " + plano.getNomeFantasia());

        Produto produto1 = new Produto(1, "Produto 1", 100.0);
        Produto produto2 = new Produto(2, "Produto 2", 150.0);
        plano.addProduto(produto1);
        plano.addProduto(produto2);

        plano.calcValor();
        System.out.println("Valor do Plano: " + plano.getValor());

        plano.tipoPLano("anual");
        System.out.println("Tipo de Plano: Anual");
        System.out.println("Valor Anual: " + plano.getValor());

        Funcionario funcionario = new Funcionario("Maria", "987-654-3210", "maria@email.com", "98765432101", "Gerente", "123", 5000.0);
        System.out.println("Funcionário: " + funcionario.getNome());

        Contrato contrato = new Contrato(funcionario, empresa, plano);

        contrato.pagar("Boleto");

        Endereco endereco1 = new Endereco("Rua A", "123", "Cidade A", "Estado A", "12345-678");
        representante.addEndereco(endereco1);

        SistemaLogin sistemaLogin = new SistemaLogin();

        sistemaLogin.Cadastro(representante);
        sistemaLogin.Cadastro(funcionario);

        sistemaLogin.autenticarUser("joao@email.com", "123");
    }
}
