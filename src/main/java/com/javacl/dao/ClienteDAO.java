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
    private Connection connection;
    private EnderecoDAO enderecoDAO;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
        enderecoDAO = new EnderecoDAO(this.connection);
    }

    /* GET ALL */
    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sqlSelect = "SELECT * FROM cliente";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
                ResultSet resultSet = selectStatement.executeQuery()) {

            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String cpf = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                String cargo = resultSet.getString("cargo");
                String senha = resultSet.getString("senha");

                Cliente newCliente = new Cliente(nome, telefone, email, cpf, cargo, senha);

                int enderecoId = resultSet.getInt("endereco_id");

                if (enderecoId != 0) {
                    Endereco endereco = new Endereco(enderecoDAO.getEnderecoById(enderecoId));
                    newCliente.setEndereco(endereco);
                }

                clientes.add(newCliente);
            }
        }

        return clientes;
    }

    /* GET by CPF */
    public Cliente getClientebyCPF(String cpf) throws SQLException {
        String sqlSelect = "SELECT * FROM cliente WHERE cpf = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
            selectStatement.setString(1, cpf);
            
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    String cargo = resultSet.getString("cargo");
                    String senha = resultSet.getString("senha");
    
                    Cliente cliente = new Cliente(nome, telefone, email, cpf, cargo, senha);
    
                    int enderecoId = resultSet.getInt("endereco_id");
    
                    if (enderecoId != 0) {
                        Endereco endereco = new Endereco(enderecoDAO.getEnderecoById(enderecoId));
                        cliente.setEndereco(endereco);
                    }
    
                    return cliente;
                }
            }
        }
        return null;
    }
    

    /* POST */
    public void createCliente(Cliente cliente, Endereco endereco) throws SQLException {
        int idEndereco = enderecoDAO.createEndereco(endereco);

        String sqlInsertCliente = "INSERT INTO cliente (nome, telefone, email, cpf, cargo, senha, endereco_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatementCliente = connection.prepareStatement(sqlInsertCliente,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStatementCliente.setString(1, cliente.getNome());
            insertStatementCliente.setString(2, cliente.getTelefone());
            insertStatementCliente.setString(3, cliente.getEmail());
            insertStatementCliente.setString(4, cliente.getCpf());
            insertStatementCliente.setString(5, cliente.getCargo());
            insertStatementCliente.setString(6, cliente.getSenha());
            insertStatementCliente.setInt(7, idEndereco);

            insertStatementCliente.executeUpdate();
        }
    }

    /* DELETE */
    public void deleteCliente(String cpf) throws SQLException {
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
    public void updateCliente(Cliente cliente, Endereco endereco) throws SQLException {
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
