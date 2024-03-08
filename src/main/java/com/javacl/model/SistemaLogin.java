package com.javacl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javacl.model.pessoa.Cliente;
import com.javacl.model.pessoa.Funcionario;
import com.javacl.model.pessoa.Usuario;

public class SistemaLogin {
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Connection conexaoBD() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String username = "RM553912";
        String password = "141204";

        Connection connection = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
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
        funcionario.setEndereco(endereco);

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
        cliente.setEndereco(endereco);

        usuarios.add(cliente);

        return cliente;
    }

    public Endereco obterEndereco(Scanner sc) {
        System.out.println("Informações de Endereço:");
        System.out.print("Rua: ");
        String rua = sc.nextLine();

        System.out.print("Numero:");
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

    public EmpresaCliente cadastroEmpresa(Scanner sc, Cliente representante) {
        System.out.println("Cadastre a sua empresa:");
    
        System.out.println("Digite o CNPJ: ");
        String cnpj = sc.nextLine();
    
        System.out.println("Digite o telefone: ");
        String telefone = sc.nextLine();
    
        System.out.println("Digite a razão social: ");
        String razaoSocial = sc.nextLine();
    
        System.out.println("Digite o nome fantasia: ");
        String nomeFantasia = sc.nextLine();
    
        System.out.println("Digite o numero de funcionarios: ");
        int tamanho = sc.nextInt();
        sc.nextLine();
    
        EmpresaCliente empresa = new EmpresaCliente(representante, cnpj, telefone, razaoSocial, nomeFantasia,
                tamanho);
    
        return empresa;
    
    }
}
