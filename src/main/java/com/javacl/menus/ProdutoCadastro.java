package com.javacl.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javacl.model.Produto;

public class ProdutoCadastro {

    public static List<Produto> cadastrarProduto(Scanner sc) {
        List<Produto> produtos = new ArrayList<>();

        System.out.println("Cadastro de Produto:");

        System.out.println("Quantos produtos vai cadastrar:");
        int qtd = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < qtd; i++) {

            System.out.print("Nome do Produto: ");
            String nome = sc.nextLine();

            System.out.print("Preço do Produto: ");
            double preco = Double.parseDouble(sc.nextLine());

            Produto novoProduto = new Produto();
            novoProduto.setNome(nome);
            novoProduto.setPreco(preco);

            produtos.add(novoProduto);
        }

        return produtos;
    }
}
