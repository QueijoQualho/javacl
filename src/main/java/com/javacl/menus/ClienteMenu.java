package com.javacl.menus;

import java.util.List;
import java.util.Scanner;

import com.javacl.model.Endereco;
import com.javacl.model.pessoa.Cliente;
import com.javacl.model.pessoa.Usuario;
import com.javacl.repositorys.ClienteRepository;
import com.javacl.repositorys.UsuarioRepository;

public class ClienteMenu {
    public static final UsuarioRepository clienteRepo = new ClienteRepository();

    public static void cadastrarCliente(Scanner sc) {
        System.out.println("Cadastro de Cliente:");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Cargo: ");
        String cargo = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Endereco enderecos = EnderecoMenu.cadastroEndereco(sc);

        // Criação do cliente com os dados fornecidos
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setTelefone(telefone);
        novoCliente.setEmail(email);
        novoCliente.setCpf(cpf);
        novoCliente.setCargo(cargo);
        novoCliente.setSenha(senha);
        novoCliente.addEndereco(enderecos);

        // Adicionando o cliente ao banco de dados
        clienteRepo.saveUsuario(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static List<Usuario> pegarCliente() {
        System.out.println("Informações do Cliente:");

        List<Usuario> usuarios = clienteRepo.getUsuarios();

        return usuarios;
    }

    public static Usuario pegarClientePorID(Long id) {
        Usuario usuario = clienteRepo.getUsuarioById(id);

        if(usuario == null){
            System.out.println("Esse usuário não existe.");
            
            return null;
        }
        return usuario;
    }

    public static void atualizarCliente(Scanner sc) {
        System.out.println("Atualização de Cliente:");

        System.out.print("ID do Cliente a ser atualizado: ");
        Long idCliente = Long.parseLong(sc.nextLine());

        Cliente clienteExistente = (Cliente) clienteRepo.getUsuarioById(idCliente);

        if (clienteExistente != null) {
            System.out.print("Novo Nome: ");
            String novoNome = sc.nextLine();

            System.out.print("Novo Telefone: ");
            String novoTelefone = sc.nextLine();

            System.out.print("Novo Email: ");
            String novoEmail = sc.nextLine();

            System.out.print("Novo CPF: ");
            String novoCpf = sc.nextLine();

            System.out.print("Novo Cargo: ");
            String novoCargo = sc.nextLine();

            System.out.print("Senha: ");
            String senha = sc.nextLine();
    

            // Atualizando os dados do cliente existente
            clienteExistente.setNome(novoNome);
            clienteExistente.setTelefone(novoTelefone);
            clienteExistente.setEmail(novoEmail);
            clienteExistente.setCpf(novoCpf);
            clienteExistente.setCargo(novoCargo);
            clienteExistente.setSenha(senha);

            // Persistindo as mudanças no banco de dados
            clienteRepo.updateUsuario(clienteExistente);

            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    public static void deletarCliente(Scanner sc) {
        System.out.println("Delete de Cliente:");

        System.out.print("ID do Cliente a ser atualizado: ");
        Long idCliente = Long.parseLong(sc.nextLine());

        clienteRepo.deleteUsuario(idCliente);

        Cliente ClienteExcluido = (Cliente) clienteRepo.getUsuarioById(idCliente);
        if (ClienteExcluido == null) {
            System.out.println("Cliente com ID " + idCliente + " foi deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar o Cliente com ID " + idCliente + ".");
        }
    }
}
