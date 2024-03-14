package com.javacl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.javacl.model.Endereco;

public class EnderecoDAO {
    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    /* GET */
    public Endereco getEnderecoByCep(String cep) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE cep = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cep);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Endereco(
                        resultSet.getString("rua"),
                        resultSet.getString("numero"),
                        resultSet.getString("cidade"),
                        resultSet.getString("estado"),
                        resultSet.getString("cep"));
            }
        }
        return null;
    }

    public Endereco getEnderecoById(int id) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Endereco(
                            resultSet.getString("rua"),
                            resultSet.getString("numero"),
                            resultSet.getString("cidade"),
                            resultSet.getString("estado"),
                            resultSet.getString("cep"));
                }
            }
        }

        return null;
    }

    /* POST */
    public int createEndereco(Endereco endereco) throws SQLException {

        String sql = "INSERT INTO endereco (rua, numero, cidade, estado, cep) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, endereco.getRua());
            statement.setString(2, endereco.getNumero());
            statement.setString(3, endereco.getCidade());
            statement.setString(4, endereco.getEstado());
            statement.setString(5, endereco.getCep());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }

        return -1;
    }

    public int createEndereco(String rua, String numero, String cidade, String estado, String cep) throws SQLException {
        String sql = "INSERT INTO endereco (rua, numero, cidade, estado, cep) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, rua);
            statement.setString(2, numero);
            statement.setString(3, cidade);
            statement.setString(4, estado);
            statement.setString(5, cep);
        
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Não foi possivel pegar o ID");
                }
            }
        }
    }
    

    /* PUT */
    public void updateEndereco(Endereco endereco) throws SQLException {
        String sql = "UPDATE endereco SET rua = ?, numero = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, endereco.getRua());
            statement.setString(2, endereco.getNumero());
            statement.setString(3, endereco.getCidade());
            statement.setString(4, endereco.getEstado());
            statement.setString(5, endereco.getCep());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Endereço atualizado com sucesso!");
            } else {
                System.out.println("Nenhum endereço encontrado para atualizar.");
            }
        }
    }

}
