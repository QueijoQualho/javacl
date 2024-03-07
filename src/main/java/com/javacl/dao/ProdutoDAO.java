package com.javacl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.Produto;

public class ProdutoDAO {

    // GET by ID 
    public Produto obterProdutoPorId(Connection connection, int produtoId) throws SQLException {
        String sqlSelect = "SELECT * FROM produto WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
            selectStatement.setInt(1, produtoId);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    double preco = resultSet.getDouble("preco");

                    Produto produto = new Produto(produtoId, nome, preco);
                    return produto;
                }
            }
        }

        return null;
    }

    // GET All 
    public List<Produto> obterTodosProdutos(Connection connection) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sqlSelect = "SELECT * FROM produto";

        try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    double preco = resultSet.getDouble("preco");

                    Produto produto = new Produto(id, nome, preco);
                    produtos.add(produto);
                }
            }
        }

        return produtos;
    }

    // POST
    public void salvarProduto(Connection connection, Produto produto) throws SQLException {
        String sqlInsert = "INSERT INTO produto (nome, preco) VALUES (?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {
            insertStatement.setString(1, produto.getNome());
            insertStatement.setDouble(2, produto.getPreco());

            insertStatement.executeUpdate();

            System.out.println("Produto salvo com sucesso!");
        }
    }

    // PUT
    public void atualizarProduto(Connection connection, Produto produto) throws SQLException {
        String sqlUpdate = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate)) {
            updateStatement.setString(1, produto.getNome());
            updateStatement.setDouble(2, produto.getPreco());
            updateStatement.setInt(3, produto.getId());

            updateStatement.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");
        }
    }

    /* DELETE */
    public void deletarProduto(Connection connection, int produtoId) throws SQLException {
        String sqlDelete = "DELETE FROM produto WHERE id = ?";

        try (PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete)) {
            deleteStatement.setInt(1, produtoId);

            deleteStatement.executeUpdate();

            System.out.println("Produto deletado com sucesso!");
        }
    }

}
