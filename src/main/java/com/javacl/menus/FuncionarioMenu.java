package com.javacl.menus;

import java.util.List;
import java.util.Scanner;

import com.javacl.model.Endereco;
import com.javacl.model.pessoa.Funcionario;
import com.javacl.model.pessoa.Usuario;
import com.javacl.repositorys.FuncionarioRepository;

public class FuncionarioMenu {
    private static final FuncionarioRepository funcionarioRepo = new FuncionarioRepository();

    public static void cadastrarFuncionario(Scanner sc) {
        System.out.println("Cadastro de Funcionário:");

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

        System.out.print("Salário: ");
        double salario = Double.parseDouble(sc.nextLine());

        Endereco enderecos = EnderecoMenu.cadastroEndereco(sc);

        // Criação do cliente com os dados fornecidos
        Funcionario novoFuncio = new Funcionario();
        novoFuncio.setNome(nome);
        novoFuncio.setTelefone(telefone);
        novoFuncio.setEmail(email);
        novoFuncio.setCpf(cpf);
        novoFuncio.setCargo(cargo);
        novoFuncio.addEndereco(enderecos);
        novoFuncio.setSalario(salario);
        novoFuncio.setSenha(senha);
        
        // Adicionando o funcionário ao banco de dados
        funcionarioRepo.saveUsuario(novoFuncio);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public static List<Usuario> pegarFuncionario(Scanner sc) {
        System.out.println("Informações do Funcionario:");

        List<Usuario> usuarios = funcionarioRepo.getUsuarios();

        return usuarios;
    }

    public static void atualizarFuncionario(Scanner sc) {
        System.out.println("Atualização de Funcionário:");

        System.out.print("ID do Funcionário a ser atualizado: ");
        Long idFuncionario = Long.parseLong(sc.nextLine());

        Funcionario funcionarioExistente = (Funcionario) funcionarioRepo.getUsuarioById(idFuncionario);

        if (funcionarioExistente != null) {
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

            System.out.print("Novo Salário: ");
            double novoSalario = Double.parseDouble(sc.nextLine());

            // Atualizando os dados do funcionário existente
            funcionarioExistente.setNome(novoNome);
            funcionarioExistente.setTelefone(novoTelefone);
            funcionarioExistente.setEmail(novoEmail);
            funcionarioExistente.setCpf(novoCpf);
            funcionarioExistente.setCargo(novoCargo);
            funcionarioExistente.setSenha(senha);
            funcionarioExistente.setSalario(novoSalario);

            // Persistindo as mudanças no banco de dados
            funcionarioRepo.updateUsuario(funcionarioExistente);

            System.out.println("Funcionário atualizado com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado!");
        }
    }

    public static void deletarFuncionario(Scanner sc) {
        System.out.println("Delete de Cliente:");

        System.out.print("ID do Cliente a ser atualizado: ");
        Long idFuncio = Long.parseLong(sc.nextLine());

        funcionarioRepo.deleteUsuario(idFuncio);

        Funcionario FuncioExcluido = (Funcionario) funcionarioRepo.getUsuarioById(idFuncio);
        if (FuncioExcluido == null) {
            System.out.println("Funcionario com ID " + idFuncio + " foi deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar o Funcionario com ID " + idFuncio + ".");
        }
    }
}
