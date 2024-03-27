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

    public static void menuCliente(Scanner sc) {
        System.out.println("Menu Cliente:");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Atualizar Cliente");
        System.out.println("3 - Excluir Cliente");
        System.out.println("4 - Visualizar Clientes");
        System.out.println("0 - Voltar ao menu principal");

        int opcaoCliente = Integer.parseInt(sc.nextLine());
        switch (opcaoCliente) {
            case 1:
                cadastrarCliente(sc);
                break;
            case 2:
                atualizarCliente(sc);
                break;
            case 3:
                deletarCliente(sc);
                break;
            case 4:
                List<Usuario> clientes = pegarCliente();
                for (Usuario usuario : clientes) {
                    System.out.println(usuario.toString());
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

    private static void cadastrarCliente(Scanner sc) {
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

        Endereco enderecos = EnderecoCadastro.cadastroEndereco(sc);

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setTelefone(telefone);
        novoCliente.setEmail(email);
        novoCliente.setCpf(cpf);
        novoCliente.setCargo(cargo);
        novoCliente.setSenha(senha);
        novoCliente.addEndereco(enderecos);

        clienteRepo.saveUsuario(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static List<Usuario> pegarCliente() {
        System.out.println("Informações do Cliente:");

        List<Usuario> usuarios = clienteRepo.getUsuarios();

        return usuarios;
    }

    public static Usuario pegarClientePorID(Long id) {
        Usuario usuario = clienteRepo.getUsuarioById(id);

        if (usuario == null) {
            System.out.println("Esse usuário não existe.");
            return null;
        }
        return usuario;
    }

    private static void atualizarCliente(Scanner sc) {
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

            clienteExistente.setNome(novoNome);
            clienteExistente.setTelefone(novoTelefone);
            clienteExistente.setEmail(novoEmail);
            clienteExistente.setCpf(novoCpf);
            clienteExistente.setCargo(novoCargo);
            clienteExistente.setSenha(senha);

            clienteRepo.updateUsuario(clienteExistente);

            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private static void deletarCliente(Scanner sc) {
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
