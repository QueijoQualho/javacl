package com.javacl.menus;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.javacl.model.Plano;
import com.javacl.model.Produto;
import com.javacl.model.enums.TipoPlano;
import com.javacl.repositorys.PlanoRepository;

public class PlanoMenu {
    private static final PlanoRepository planoRepo = new PlanoRepository();

    public static void cadastrarPlano(Scanner sc) {
        System.out.println("Cadastro de Plano:");

        System.out.println("Nome Fantasia: ");
        String nomeFantasia = sc.nextLine();

        System.out.println("Tipo de Plano (ANUAL, SEMESTRAL, TRIMESTRAL, MENSAL): ");
        String tipoPlano = sc.nextLine();

        List<Produto> produtos = ProdutoMenu.cadastrarProduto(sc);

        // Criação do plano com os dados fornecidos
        Plano novoPlano = new Plano(nomeFantasia, TipoPlano.valueOf(tipoPlano.toUpperCase()), null);
        novoPlano.setListaProdutos(produtos);

        // Adicionando o plano ao banco de dados
        planoRepo.savePlano(novoPlano);

        System.out.println("Plano cadastrado com sucesso!");
    }

    public static List<Plano> pegarPlanos() {
        System.out.println("Informações do Plano:");

        List<Plano> planosDisponiveis = planoRepo.getPlano();

        return planosDisponiveis;
    }

    public static void atualizarPlano(Scanner sc) {
        System.out.println("Atualização de Plano:");

        System.out.println("ID do Plano a ser atualizado: ");
        Long idPlano = Long.parseLong(sc.nextLine());

        Plano planoExistente = planoRepo.getPlanoById(idPlano);

        if (planoExistente != null) {
            System.out.print("Novo Nome Fantasia: ");
            String novoNomeFantasia = sc.nextLine();

            System.out.print("Novo Tipo de Plano (ANUAL, SEMESTRAL, TRIMESTRAL, MENSAL): ");
            String novoTipoPlano = sc.nextLine();

            System.out.print("Nova Data de Início (YYYY-MM-DD): ");
            String novaDataInicio = sc.nextLine();

            System.out.print("Nova Data Final (YYYY-MM-DD): ");
            String novaDataFinal = sc.nextLine();

            System.out.print("Novo Valor: ");
            double novoValor = Double.parseDouble(sc.nextLine());

            // Atualizando os dados do plano existente
            planoExistente.setNomeFantasia(novoNomeFantasia);
            planoExistente.setTipoPlano(TipoPlano.valueOf(novoTipoPlano.toUpperCase()));
            planoExistente.setDataInicio(LocalDate.parse(novaDataInicio));
            planoExistente.setDataFinal(LocalDate.parse(novaDataFinal));
            planoExistente.setValor(novoValor);

            // Persistindo as mudanças no banco de dados
            planoRepo.updatePlano(planoExistente);

            System.out.println("Plano atualizado com sucesso!");
        } else {
            System.out.println("Plano não encontrado!");
        }
    }

    public static void deletarPlano(Scanner sc) {
        System.out.println("Exclusão de Plano:");

        System.out.print("ID do Plano a ser excluído: ");
        Long idPlano = Long.parseLong(sc.nextLine());

        Plano planoExistente = planoRepo.getPlanoById(idPlano);

        if (planoExistente != null) {
            planoRepo.deletePlano(idPlano);

            Plano planoExcluido = planoRepo.getPlanoById(idPlano);
            if (planoExcluido == null) {
                System.out.println("Plano com ID " + idPlano + " foi excluído com sucesso.");
            } else {
                System.out.println("Falha ao excluir o plano com ID " + idPlano + ".");
            }
        } else {
            System.out.println("Plano não encontrado!");
        }
    }

}
