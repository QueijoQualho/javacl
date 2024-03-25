package com.javacl.repositorys.tests;

import com.javacl.model.EmpresaCliente;
import com.javacl.repositorys.EmpresaClienteRepository;

public class TestEmpresaCliente {
    public static void main(String[] args) {
        // Instanciando o repositório de empresas clientes
        EmpresaClienteRepository empresaClienteRepository = new EmpresaClienteRepository();

        // Testando a função de buscar todas as empresas clientes
        System.out.println("Todas as empresas clientes:");
        empresaClienteRepository.getEmpresasClientes().forEach(System.out::println);

        // Testando a função de buscar uma empresa cliente pelo ID
        Long idEmpresaCliente = 1L; // Defina o ID da empresa cliente desejada
        System.out.println("\nEmpresa cliente com ID " + idEmpresaCliente + ":");
        EmpresaCliente empresaClienteById = empresaClienteRepository.getEmpresaClienteById(idEmpresaCliente);
        if (empresaClienteById != null) {
            System.out.println(empresaClienteById);
        } else {
            System.out.println("Empresa cliente não encontrada!");
        }

        // Testando a função de salvar uma nova empresa cliente
        EmpresaCliente novaEmpresaCliente = new EmpresaCliente();
        novaEmpresaCliente.setCnpj("12345678901234");
        novaEmpresaCliente.setTelefone("(11) 98765-4321");
        novaEmpresaCliente.setRazaoSocial("Nova Razão Social");
        novaEmpresaCliente.setNomeFantasia("Novo Nome Fantasia");
        novaEmpresaCliente.setTamanho(50);
        novaEmpresaCliente.setIdCliente(15L); // Defina o ID do cliente associado

        empresaClienteRepository.saveEmpresaCliente(novaEmpresaCliente);

        // Testando a função de excluir uma empresa cliente
        Long idEmpresaClienteExcluir = 2L; // Defina o ID da empresa cliente a ser excluída
        System.out.println("\nExcluindo empresa cliente com ID " + idEmpresaClienteExcluir + "...");
        empresaClienteRepository.deleteEmpresaCliente(idEmpresaClienteExcluir);
    }
}