package com.javacl.menus;

import java.util.List;
import java.util.Scanner;

import com.javacl.model.EmpresaCliente;
import com.javacl.model.pessoa.Cliente;
import com.javacl.repositorys.EmpresaClienteRepository;

public class EmpresaMenu {
    private static EmpresaClienteRepository empresaRepository = new EmpresaClienteRepository();

    public static void menuEmpresa(Scanner sc) {
        System.out.println("Menu Empresa:");
        System.out.println("1 - Cadastrar Empresa");
        System.out.println("2 - Atualizar Empresa");
        System.out.println("3 - Excluir Empresa");
        System.out.println("4 - Visualizar Empresas");
        System.out.println("0 - Voltar ao menu principal");

        int opcaoEmpresa = Integer.parseInt(sc.nextLine());
        switch (opcaoEmpresa) {
            case 1:
                cadastrarEmpresa(sc);
                break;
            case 2:
                atualizarEmpresa(sc);
                break;
            case 3:
                deletarEmpresa(sc);
                break;
            case 4:
                List<EmpresaCliente> empresas = pegarEmpresas(sc);

                for (EmpresaCliente empresaCliente : empresas) {
                    System.out.println(empresaCliente);
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

    public static EmpresaCliente pegarEmpresaClientePorID(Long id){
        return empresaRepository.getEmpresaClienteById(id);
    }

    private static void cadastrarEmpresa(Scanner sc) {
        System.out.println("Cadastro de Empresa:");

        System.out.println("Qual o dono da empresa: (Informe o id)");
        Cliente cliente = (Cliente) ClienteMenu.pegarClientePorID(Long.parseLong(sc.nextLine()));
        System.out.print("CNPJ da empresa: ");
        String cnpj = sc.nextLine();
        System.out.print("Telefone da empresa: ");
        String telefone = sc.nextLine();
        System.out.print("Razão Social da empresa: ");
        String razaoSocial = sc.nextLine();
        System.out.print("Nome Fantasia da empresa: ");
        String nomeFantasia = sc.nextLine();
        System.out.print("Tamanho da empresa: ");
        int tamanho = Integer.parseInt(sc.nextLine());

        EmpresaCliente novaEmpresa = new EmpresaCliente(null, cliente, cnpj, telefone, razaoSocial, nomeFantasia,
                tamanho);

        empresaRepository.saveEmpresaCliente(novaEmpresa);
    }

    private static void atualizarEmpresa(Scanner sc) {
        System.out.println("Funcionalidade n implementada");
        return;
    }

    private static void deletarEmpresa(Scanner sc) {
        System.out.println("Exclusão de Empresa:");

        System.out.print("ID da empresa a ser excluída: ");
        Long idEmpresa = Long.parseLong(sc.nextLine());

        EmpresaCliente empresaExistente = empresaRepository.getEmpresaClienteById(idEmpresa);
        if (empresaExistente == null) {
            System.out.println("Empresa não encontrada.");
            return;
        }

        empresaRepository.deleteEmpresaCliente(idEmpresa);
    }

    private static List<EmpresaCliente> pegarEmpresas(Scanner sc) {
        System.out.println("Lista de Empresas:");

        List<EmpresaCliente> empresas = empresaRepository.getEmpresasClientes();

        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa cadastrada.");
        }

        return empresas;
    }
}
