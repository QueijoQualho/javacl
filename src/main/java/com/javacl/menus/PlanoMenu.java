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

    public static void menuPlano(Scanner sc) {
        System.out.println("Menu Plano:");
        System.out.println("1 - Cadastrar Plano");
        System.out.println("2 - Atualizar Plano");
        System.out.println("3 - Excluir Plano");
        System.out.println("4 - Visualizar Planos");
        System.out.println("0 - Voltar ao menu principal");

        int opcaoPlano = Integer.parseInt(sc.nextLine());
        switch (opcaoPlano) {
            case 1:
                cadastrarPlano(sc);
                break;
            case 2:
                atualizarPlano(sc);
                break;
            case 3:
                deletarPlano(sc);
                break;
            case 4:
                List<Plano> planos = pegarPlanos();
                for (Plano plano : planos) {
                    System.out.println(plano.toString());
                }
                break;
            case 0:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static Plano pegarPlanoPorID(Long id) {
        return planoRepo.getPlanoById(id);
    }

    private static void cadastrarPlano(Scanner sc) {
        System.out.println("Cadastro de Plano:");
        System.out.println("Nome Fantasia: ");
        String nomeFantasia = sc.nextLine();
        System.out.println("Tipo de Plano (ANUAL, SEMESTRAL, TRIMESTRAL, MENSAL): ");
        String tipoPlano = sc.nextLine();

        List<Produto> produtos = ProdutoCadastro.cadastrarProduto(sc);

        Plano novoPlano = new Plano(nomeFantasia, TipoPlano.valueOf(tipoPlano.toUpperCase()), null);
        novoPlano.setListaProdutos(produtos);

        planoRepo.savePlano(novoPlano);

        System.out.println("Plano cadastrado com sucesso!");
    }

    private static List<Plano> pegarPlanos() {
        System.out.println("Informações do Plano:");

        List<Plano> planosDisponiveis = planoRepo.getPlano();

        return planosDisponiveis;
    }

    private static void atualizarPlano(Scanner sc) {
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

            planoExistente.setNomeFantasia(novoNomeFantasia);
            planoExistente.setTipoPlano(TipoPlano.valueOf(novoTipoPlano.toUpperCase()));
            planoExistente.setDataInicio(LocalDate.parse(novaDataInicio));
            planoExistente.setDataFinal(LocalDate.parse(novaDataFinal));
            planoExistente.setValor(novoValor);

            planoRepo.updatePlano(planoExistente);

            System.out.println("Plano atualizado com sucesso!");
        } else {
            System.out.println("Plano não encontrado!");
        }
    }

    private static void deletarPlano(Scanner sc) {
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
