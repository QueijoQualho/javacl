package com.java_cl.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java_cl.classes.pessoa.Funcionario;
import com.java_cl.classes.pessoa.Cliente;
import com.java_cl.classes.pessoa.Usuario;

public class SistemaLogin {
    public List<Usuario> usuarios = new ArrayList<Usuario>();

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /* Methods */
    public void cadastrarFuncionario(Scanner sc) {
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

        System.out.print("Salário: ");
        double salario = sc.nextDouble();
        sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Endereco endereco = obterEndereco(sc);

        Funcionario funcionario = new Funcionario(nome, telefone, email, cpf, cargo, senha, salario);
        funcionario.addEndereco(endereco);

        usuarios.add(funcionario);
    }

    public Cliente cadastrarCliente(Scanner sc) {
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

        Endereco endereco = obterEndereco(sc);

        Cliente cliente = new Cliente(nome, telefone, email, cpf, cargo, senha);
        cliente.addEndereco(endereco);

        usuarios.add(cliente);

        return cliente;
    }

    public Endereco obterEndereco(Scanner sc) {
        System.out.println("Informações de Endereço:");
        System.out.print("Rua: ");
        String rua = sc.nextLine();

        System.out.println("Numero:");
        String num = sc.nextLine();

        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        System.out.print("Estado: ");
        String estado = sc.nextLine();

        System.out.print("CEP: ");
        String cep = sc.nextLine();

        Endereco endereco = new Endereco(rua, num, cidade, estado, cep);

        return endereco;
    }

    public boolean autenticarUser(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                System.out.println("Login realizado.");
                return true;
            }
        }
        System.out.println("Login falhou. Nome de usuário ou senha incorretos.");
        return false;
    }
}
