package com.javacl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.pessoa.Funcionario;
import com.javacl.model.Endereco;

public class FuncionarioDAO {
    private Connection connection;
    private EnderecoDAO enderecoDAO;

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
        enderecoDAO = new EnderecoDAO(this.connection);
    }

    /* GET ALL */
    public List<Funcionario> getAllFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sqlSelect = "SELECT f.*, e.* " +
                "FROM funcionario f " +
                "LEFT JOIN endereco e ON f.endereco_id = e.id ";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
             ResultSet resultSet = selectStatement.executeQuery()) {

            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String cpf = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                String cargo = resultSet.getString("cargo");
                String senha = resultSet.getString("senha");
                double salario = resultSet.getDouble("salario");

                Funcionario newFuncionario = new Funcionario(nome, telefone, email, cpf, cargo, senha, salario);

                int enderecoId = resultSet.getInt("endereco_id");

                Endereco endereco = new Endereco(enderecoDAO.getEnderecoById(enderecoId));
                newFuncionario.setEndereco(endereco);

                funcionarios.add(newFuncionario);
            }

            return funcionarios;
        }
    }

    /* GET by CPF */
    public Funcionario getFuncionarioByCPF(String cpf) throws SQLException {
        String sqlSelect = "SELECT f.*, e.* " +
                "FROM funcionario f " +
                "INNER JOIN endereco e ON f.endereco_id = e.id " +
                "WHERE f.cpf = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
            selectStatement.setString(1, cpf);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Funcionario funcionario = null;

                while (resultSet.next()) {
                    if (funcionario == null) {
                        String nome = resultSet.getString("nome");
                        String telefone = resultSet.getString("telefone");
                        String email = resultSet.getString("email");
                        String cargo = resultSet.getString("cargo");
                        String senha = resultSet.getString("senha");
                        double salario = resultSet.getDouble("salario");

                        funcionario = new Funcionario(nome, telefone, email, cpf, cargo, senha, salario);
                    }

                    // Dados do endereço
                    String rua = resultSet.getString("rua");
                    String numero = resultSet.getString("numero");
                    String cidade = resultSet.getString("cidade");
                    String estado = resultSet.getString("estado");
                    String cep = resultSet.getString("cep");

                    Endereco endereco = new Endereco(rua, numero, cidade, estado, cep);
                    funcionario.setEndereco(endereco);
                }

                return funcionario;
            }
        }
    }

    /* POST */
    public void createFuncionario(Funcionario funcionario, Endereco endereco) throws SQLException {
        int idEndereco = enderecoDAO.createEndereco(endereco);

        String sqlInsertFuncionario = "INSERT INTO funcionario (nome, telefone, email, cpf, cargo, senha, salario, endereco_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatementFuncionario = connection.prepareStatement(sqlInsertFuncionario,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStatementFuncionario.setString(1, funcionario.getNome());
            insertStatementFuncionario.setString(2, funcionario.getTelefone());
            insertStatementFuncionario.setString(3, funcionario.getEmail());
            insertStatementFuncionario.setString(4, funcionario.getCpf());
            insertStatementFuncionario.setString(5, funcionario.getCargo());
            insertStatementFuncionario.setString(6, funcionario.getSenha());
            insertStatementFuncionario.setDouble(7, funcionario.getSalario());
            insertStatementFuncionario.setInt(8, idEndereco);

            insertStatementFuncionario.executeUpdate();
        }
    }

    /* DELETE */
    public void deleteFuncionario(String cpf) throws SQLException {
        String sqlDeleteFuncionario = "DELETE FROM funcionario WHERE cpf = ?";

        try (PreparedStatement deleteStatementFuncionario = connection.prepareStatement(sqlDeleteFuncionario)) {
            deleteStatementFuncionario.setString(1, cpf);

            int affectedRows = deleteStatementFuncionario.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Funcionário deletado com sucesso!");
            } else {
                System.out.println("Nenhum funcionário encontrado com o CPF informado.");
            }
        }
    }

    /* UPDATE */
    public void updateFuncionario(Funcionario funcionario, Endereco endereco) throws SQLException {
        String sqlUpdateFuncionario = "UPDATE funcionario SET nome = ?, telefone = ?, email = ?, cargo = ?, senha = ?, salario = ? WHERE cpf = ?";

        try (PreparedStatement updateStatementFuncionario = connection.prepareStatement(sqlUpdateFuncionario)) {
            updateStatementFuncionario.setString(1, funcionario.getNome());
            updateStatementFuncionario.setString(2, funcionario.getTelefone());
            updateStatementFuncionario.setString(3, funcionario.getEmail());
            updateStatementFuncionario.setString(4, funcionario.getCargo());
            updateStatementFuncionario.setString(5, funcionario.getSenha());
            updateStatementFuncionario.setDouble(6, funcionario.getSalario());
            updateStatementFuncionario.setString(7, funcionario.getCpf());

            int affectedRows = updateStatementFuncionario.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum funcionário encontrado com o CPF informado para atualização.");
            }
        }
    }
}
