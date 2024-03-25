package com.javacl.repositorys.tests;

import java.util.ArrayList;
import java.util.List;

import com.javacl.model.Endereco;
import com.javacl.model.pessoa.Cliente;
import com.javacl.model.pessoa.Usuario;
import com.javacl.repositorys.ClienteRepository;

public class TestCliente {
    public static void main(String[] args) {
        // Criar uma instância do ClienteRepository
        ClienteRepository clienteRepository = new ClienteRepository();

        // Testar o método getUsuarios()
        List<Usuario> usuarios = clienteRepository.getUsuarios();
        System.out.println("Lista de Usuários:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNome());
        }

        // Testar o método getUsuarioById()
        System.out.println("\nUsuário com ID 13:");
        Usuario usuarioById = clienteRepository.getUsuarioById(13L);
        System.out.println(usuarioById);

        // Testar o método saveUsuario()
        List<Endereco> enderecos = new ArrayList<>();

        // Adicionar alguns endereços à lista
        enderecos.add(new Endereco(1L, "Rua A", "123", "Cidade A", "Estado A", "12345-678"));
        enderecos.add(new Endereco(2L, "Rua B", "456", "Cidade B", "Estado B", "98765-432"));
        enderecos.add(new Endereco(3L, "Rua C", "789", "Cidade C", "Estado C", "54321-876"));

        System.out.println("Criando cliente");
        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Novo Cliente");
        novoCliente.setTelefone("123456789");
        novoCliente.setEmail("novo_cliente@example.com");
        novoCliente.setCpf("a98765s32142");
        novoCliente.setCargo("Cliente");
        novoCliente.setSenha("senha123");
        novoCliente.setListaEnderecos(enderecos);
        clienteRepository.saveUsuario(novoCliente);
        System.out.println("\nNovo cliente adicionado com sucesso!");

        // Testar o método updateUsuario()
        novoCliente.setNome("Novo Nome Cliente");
        novoCliente.setId(11L);
        clienteRepository.updateUsuario(novoCliente);
        System.out.println("\nCliente atualizado com sucesso!");

        /* // Testar o método deleteUsuario()
        clienteRepository.deleteUsuario(novoCliente.getId());
        System.out.println("\nCliente excluído com sucesso!"); */
    }
}