package com.javacl.repositorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.Plano;
import com.javacl.model.Produto;
import com.javacl.model.connection.DatabaseConnection;

public class ProdutoRepository {
    private Connection connection = DatabaseConnection.getConnection();

    public List<Produto> getProdutosPorPlano(Long idPlano) {
        String sql = "SELECT * FROM Produto WHERE id_plano = ?";
        List<Produto> produtos = new ArrayList<>();

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idPlano);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getLong("id_produto"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setIdPlano(idPlano);

                    produtos.add(produto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public void saveProduto(Produto produto) {
        String sql = "INSERT INTO Produto (nome, preco, id_plano) VALUES (?, ?, ?)";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setLong(3, produto.getIdPlano());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProdutosPlano(Plano plano) {
        String sql = "UPDATE Produto SET nome = ?, preco = ? WHERE id_produto = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (Produto produto : plano.getListaProdutos()) {
                stmt.setString(1, produto.getNome());
                stmt.setDouble(2, produto.getPreco());
                stmt.setLong(3, produto.getId());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
