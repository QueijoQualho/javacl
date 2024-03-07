package com.javacl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.javacl.model.Endereco;
import com.javacl.model.pessoa.Funcionario;

public class FuncionarioDAO {
    /* GET by CPF */
    public Funcionario obterFuncionarioPorCPF(Connection connection, String cpf) throws SQLException {
        String sqlSelect = "SELECT * FROM funcionario WHERE cpf = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
            selectStatement.setString(1, cpf);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    String cargo = resultSet.getString("cargo");
                    String senha = resultSet.getString("senha");
                    double salario = resultSet.getDouble("salario");

                    String enderecoString = resultSet.getString("endereco_parte");
                    List<String> enderecoList = new ArrayList<>(List.of(enderecoString.split(",")));

                    List<Endereco> endereco = new ArrayList<>();
                    for (String enderecoItem : enderecoList) {
                        endereco.add(new Endereco(enderecoItem));
                    }

                    Funcionario newFuncionario = new Funcionario(nome, telefone, email, cpf, cargo, senha, salario);
                    newFuncionario.setEndereco(endereco);
                    return newFuncionario;
                }
            }
        }

        return null;
    }

    /* GET ALL */
    public List<Funcionario> obterTodosFuncionarios(Connection connection) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sqlSelect = "SELECT * FROM funcionario";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    String cpf = resultSet.getString("cpf");
                    String cargo = resultSet.getString("cargo");
                    String senha = resultSet.getString("senha");
                    double salario = resultSet.getDouble("salario");

                    String enderecoString = resultSet.getString("endereco_parte");
                    List<String> enderecoList = new ArrayList<>(List.of(enderecoString.split(",")));

                    List<Endereco> endereco = new ArrayList<>();
                    for (String enderecoItem : enderecoList) {
                        endereco.add(new Endereco(enderecoItem));
                    }

                    Funcionario newFuncionario = new Funcionario(nome, telefone, email, cpf, cargo, senha, salario);
                    newFuncionario.setEndereco(endereco);
                    funcionarios.add(newFuncionario);
                }
            }
        }

        return funcionarios;
    }

    /* POST */
    public void salvarFuncionario(Connection connection, Funcionario funcionario) throws SQLException {
        String sqlInsert = "INSERT INTO funcionario (nome, telefone, email, cpf, cargo, senha, endereco_parte, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {
            insertStatement.setString(1, funcionario.getNome());
            insertStatement.setString(2, funcionario.getTelefone());
            insertStatement.setString(3, funcionario.getEmail());
            insertStatement.setString(4, funcionario.getCpf());
            insertStatement.setString(5, funcionario.getCargo());
            insertStatement.setString(6, funcionario.getSenha());

            String endereco = funcionario.getEndereco().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            insertStatement.setString(7, endereco);
            insertStatement.setDouble(8, funcionario.getSalario());

            insertStatement.executeUpdate();

            System.out.println("Funcionário salvo com sucesso!");
        }
    }
}
