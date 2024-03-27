package com.javacl.menus;

import java.util.List;
import java.util.Scanner;

import com.javacl.model.Contrato;
import com.javacl.model.EmpresaCliente;
import com.javacl.model.Plano;
import com.javacl.model.enums.TipoPagamento;
import com.javacl.model.pessoa.Funcionario;
import com.javacl.repositorys.ContratoRepository;

public class ContratoMenu {
    private static ContratoRepository contratoRepo = new ContratoRepository();

    public static void menuContrato(Scanner sc) {
        System.out.println("Menu Contrato:");
        System.out.println("1 - Criar Contrato");
        System.out.println("2 - Atualizar Contrato");
        System.out.println("3 - Excluir Contrato");
        System.out.println("4 - Visualizar Contratos");
        System.out.println("0 - Voltar ao menu principal");

        int opcaoContrato = Integer.parseInt(sc.nextLine());
        switch (opcaoContrato) {
            case 1:
                criarContrato(sc);
                break;
            case 2:
                atualizarContrato(sc);
                break;
            case 3:
                excluirContrato(sc);
                break;
            case 4:
                List<Contrato> contratos = contratoRepo.getAllContratos();
                if (!contratos.isEmpty()) {
                    for (Contrato contrato : contratos) {
                        System.out.println(contrato.toString());
                    }
                } else {
                    System.out.println("Não há contratos cadastrados.");
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

    private static void criarContrato(Scanner sc) {
        System.out.println("Criar Contrato: ");
        System.out.print("Id da Empresa: ");
        long idEmpresa = Long.parseLong(sc.nextLine());
        System.out.print("Id do Funcionario: ");
        long idFuncionario = Long.parseLong(sc.nextLine());
        System.out.print("Id do Plano: ");
        long idPlano = Long.parseLong(sc.nextLine());
        System.out.println("Forma de pagamento (PIX, DEBITO, CREDITO, BOLETO): ");
        String formaPagamento = sc.nextLine();

        Funcionario funcionario = FuncionarioMenu.pegarFuncionarioPorID(idFuncionario);
        Plano plano = PlanoMenu.pegarPlanoPorID(idPlano);
        EmpresaCliente empresaCliente = EmpresaMenu.pegarEmpresaClientePorID(idEmpresa);

        Contrato contrato = new Contrato(null, plano, funcionario, empresaCliente,
                TipoPagamento.valueOf(formaPagamento.toUpperCase()));

        contratoRepo.saveContrato(contrato);
    }

    private static void atualizarContrato(Scanner sc) {
        System.out.println("Funcionalidade n implementada");
    }

    private static void excluirContrato(Scanner sc) {
        System.out.println("Exclusão de Contrato:");
    
        System.out.print("ID do Contrato a ser excluído: ");
        Long idContrato = Long.parseLong(sc.nextLine());
    
        Contrato contratoExistente = contratoRepo.getContratoById(idContrato);
    
        if (contratoExistente != null) {
            contratoRepo.deleteContrato(idContrato);
    
            Contrato contratoExcluido = contratoRepo.getContratoById(idContrato);
            if (contratoExcluido == null) {
                System.out.println("Contrato com ID " + idContrato + " foi excluído com sucesso.");
            } else {
                System.out.println("Falha ao excluir o contrato com ID " + idContrato + ".");
            }
        } else {
            System.out.println("Contrato não encontrado!");
        }
    }
    

}
