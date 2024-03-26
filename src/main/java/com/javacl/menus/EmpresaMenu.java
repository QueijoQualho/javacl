package com.javacl.menus;

import java.util.List;
import java.util.Scanner;

import com.javacl.model.EmpresaCliente;
import com.javacl.model.pessoa.Cliente;
import com.javacl.repositorys.EmpresaClienteRepository;

public class EmpresaMenu {
    private static EmpresaClienteRepository empresaRepository = new EmpresaClienteRepository();

    public static void cadastrarEmpresa(Scanner sc) {
        System.out.println("Cadastro de Empresa:");

        // Solicitar informações da empresa ao usuário
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

        // Criar o objeto EmpresaCliente com as informações fornecidas
        EmpresaCliente novaEmpresa = new EmpresaCliente(null, cliente, cnpj, telefone, razaoSocial, nomeFantasia, tamanho);
 
        // Salvar a empresa no banco de dados
        empresaRepository.saveEmpresaCliente(novaEmpresa);
    }

    public static void atualizarEmpresa(Scanner sc) {
        System.out.println("Funcionalidade n implementada");
        return;
    }

    public static void deletarEmpresa(Scanner sc) {
        System.out.println("Exclusão de Empresa:");

        // Solicitar o ID da empresa a ser excluída
        System.out.print("ID da empresa a ser excluída: ");
        Long idEmpresa = Long.parseLong(sc.nextLine());

        // Verificar se a empresa existe antes de excluir
        EmpresaCliente empresaExistente = empresaRepository.getEmpresaClienteById(idEmpresa);
        if (empresaExistente == null) {
            System.out.println("Empresa não encontrada.");
            return;
        }

        // Excluir a empresa do banco de dados
        empresaRepository.deleteEmpresaCliente(idEmpresa);
    }

    public static List<EmpresaCliente> pegarEmpresas(Scanner sc) {
        System.out.println("Lista de Empresas:");

        // Recuperar a lista de empresas do banco de dados
        List<EmpresaCliente> empresas = empresaRepository.getEmpresasClientes();

        // Verificar se existem empresas cadastradas
        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa cadastrada.");
        }

        return empresas;
    }
}
