package com.javacl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.pessoa.Cliente;
import com.javacl.model.Endereco;

public class ClienteDAO {
    /* GET ALL */
    public List<Cliente> obterTodosClientes(Connection connection) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sqlSelect = "SELECT c.*, e.* " +
                "FROM cliente c " +
                "LEFT JOIN endereco e ON c.id = e.cliente_id ";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Cliente newCliente = null;

                while (resultSet.next()) {
                    if (newCliente == null) {
                        String nome = resultSet.getString("nome");
                        String telefone = resultSet.getString("telefone");
                        String cpf = resultSet.getString("cpf");
                        String email = resultSet.getString("email");
                        String cargo = resultSet.getString("cargo");
                        String senha = resultSet.getString("senha");

                        newCliente = new Cliente(nome, telefone, email, cpf, cargo, senha);
                    }

                    // Dados do endereço
                    String rua = resultSet.getString("rua");
                    String numero = resultSet.getString("numero");
                    String cidade = resultSet.getString("cidade");
                    String estado = resultSet.getString("estado");
                    String cep = resultSet.getString("cep");

                    Endereco endereco = new Endereco(rua, numero, cidade, estado, cep);
                    newCliente.addEndereco(endereco);
                    clientes.add(newCliente);
                }

            }
        }

        return clientes;
    }

    /* GET by CPF */
    public Cliente obterClientePorCPF(Connection connection, String cpf) throws SQLException {
        String sqlSelect = "SELECT c.*, e.* " +
                "FROM cliente c " +
                "LEFT JOIN endereco e ON c.id = e.cliente_id " +
                "WHERE c.cpf = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
            selectStatement.setString(1, cpf);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Cliente cliente = null;

                while (resultSet.next()) {
                    if (cliente == null) {
                        String nome = resultSet.getString("nome");
                        String telefone = resultSet.getString("telefone");
                        String email = resultSet.getString("email");
                        String cargo = resultSet.getString("cargo");
                        String senha = resultSet.getString("senha");

                        cliente = new Cliente(nome, telefone, email, cpf, cargo, senha);
                    }

                    // Dados do endereço
                    String rua = resultSet.getString("rua");
                    String numero = resultSet.getString("numero");
                    String cidade = resultSet.getString("cidade");
                    String estado = resultSet.getString("estado");
                    String cep = resultSet.getString("cep");

                    Endereco endereco = new Endereco(rua, numero, cidade, estado, cep);
                    cliente.addEndereco(endereco);
                }

                return cliente;
            }
        }
    }

    /* POST */
    public void salvarCliente(Connection connection, Cliente cliente) throws SQLException {
        String sqlInsertCliente = "INSERT INTO cliente (nome, telefone, email, cpf, cargo, senha) VALUES (?, ?, ?, ?, ?, ?)";
    
        try (PreparedStatement insertStatementCliente = connection.prepareStatement(sqlInsertCliente, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStatementCliente.setString(1, cliente.getNome());
            insertStatementCliente.setString(2, cliente.getTelefone());
            insertStatementCliente.setString(3, cliente.getEmail());
            insertStatementCliente.setString(4, cliente.getCpf());
            insertStatementCliente.setString(5, cliente.getCargo());
            insertStatementCliente.setString(6, cliente.getSenha());
    
            int affectedRows = insertStatementCliente.executeUpdate();
    
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = insertStatementCliente.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int clienteId = generatedKeys.getInt(1);
    
                        String sqlInsertEndereco = "INSERT INTO endereco (cliente_id, rua, numero, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?)";
                        for (Endereco endereco : cliente.getEndereco()) {
                            try (PreparedStatement insertStatementEndereco = connection.prepareStatement(sqlInsertEndereco)) {
                                insertStatementEndereco.setInt(1, clienteId);
                                insertStatementEndereco.setString(2, endereco.getRua());
                                insertStatementEndereco.setString(3, endereco.getNumero());
                                insertStatementEndereco.setString(4, endereco.getCidade());
                                insertStatementEndereco.setString(5, endereco.getEstado());
                                insertStatementEndereco.setString(6, endereco.getCep());
    
                                insertStatementEndereco.executeUpdate();
                            }
                        }
    
                        System.out.println("Cliente salvo com sucesso!");
                    }
                }
            }
        }
    }

     /* DELETE */
     public void deletarClientePorCPF(Connection connection, String cpf) throws SQLException {
        String sqlDeleteCliente = "DELETE FROM cliente WHERE cpf = ?";

        try (PreparedStatement deleteStatementCliente = connection.prepareStatement(sqlDeleteCliente)) {
            deleteStatementCliente.setString(1, cpf);

            int affectedRows = deleteStatementCliente.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o CPF informado.");
            }
        }
    }

    /* UPDATE */
    public void atualizarCliente(Connection connection, Cliente cliente) throws SQLException {
        String sqlUpdateCliente = "UPDATE cliente SET nome = ?, telefone = ?, email = ?, cargo = ?, senha = ? WHERE cpf = ?";

        try (PreparedStatement updateStatementCliente = connection.prepareStatement(sqlUpdateCliente)) {
            updateStatementCliente.setString(1, cliente.getNome());
            updateStatementCliente.setString(2, cliente.getTelefone());
            updateStatementCliente.setString(3, cliente.getEmail());
            updateStatementCliente.setString(4, cliente.getCargo());
            updateStatementCliente.setString(5, cliente.getSenha());
            updateStatementCliente.setString(6, cliente.getCpf());

            int affectedRows = updateStatementCliente.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o CPF informado para atualização.");
            }
        }
    }
    

}
