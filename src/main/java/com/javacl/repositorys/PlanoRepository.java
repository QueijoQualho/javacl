package com.javacl.repositorys;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.Plano;
import com.javacl.model.Produto;
import com.javacl.model.enums.TipoPlano;
import com.javacl.model.connection.DatabaseConnection;

public class PlanoRepository {

    private Connection connection = DatabaseConnection.getConnection();
    private ProdutoRepository prodRepo = new ProdutoRepository();

    public List<Plano> getPlano() {
        String sql = "SELECT * FROM Plano ORDER BY NomeFantasia ASC";
        List<Plano> planos = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Plano plano = new Plano();
                plano.setId(rs.getLong("id_plano"));
                plano.setNomeFantasia(rs.getString("nomeFantasia"));
                plano.setTipoPlano(TipoPlano.valueOf(rs.getString("tipoPlano")));
                plano.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                plano.setDataFinal(rs.getDate("dataFinal").toLocalDate());
                plano.setValor(rs.getDouble("valor"));

                List<Produto> produtos = prodRepo.getProdutosPorPlano(plano.getId());
                plano.setListaProdutos(produtos);

                planos.add(plano);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planos;
    }

    public Plano getPlanoById(Long idPlano) {
        String sql = "SELECT * FROM Plano WHERE id_plano = ?";
        Plano plano = null;

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setLong(1, idPlano);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    plano = new Plano();
                    plano.setId(rs.getLong("id_plano"));
                    plano.setNomeFantasia(rs.getString("nomeFantasia"));
                    plano.setTipoPlano(TipoPlano.valueOf(rs.getString("tipoPlano")));
                    plano.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                    plano.setDataFinal(rs.getDate("dataFinal").toLocalDate());
                    plano.setValor(rs.getDouble("valor"));

                    List<Produto> produtos = prodRepo.getProdutosPorPlano(idPlano);
                    plano.setListaProdutos(produtos);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plano;
    }

    public void savePlano(Plano plano) {
        String sql = "INSERT INTO Plano (nomeFantasia, tipoPlano, dataInicio, dataFinal, valor) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, new String[]{"id_plano"})) {

            pstm.setString(1, plano.getNomeFantasia());
            pstm.setString(2, plano.getTipoPlano().toString());
            pstm.setDate(3, Date.valueOf(plano.getDataInicio()));
            pstm.setDate(4, Date.valueOf(plano.getDataFinal()));
            pstm.setDouble(5, plano.getValor());

            pstm.executeUpdate();
            
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Valor retornado pela chave gerada: " + generatedKeys.getObject(1));
                Long idPlano = generatedKeys.getLong(1);

                for (Produto produto : plano.getListaProdutos()) {
                    produto.setIdPlano(idPlano);
                    prodRepo.saveProduto(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlano(Plano plano) {
        String sql = "UPDATE Plano SET nomeFantasia = ?, tipoPlano = ?, dataInicio = ?, dataFinal = ?, valor = ? WHERE id_plano = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setString(1, plano.getNomeFantasia());
            psmt.setString(2, plano.getTipoPlano().toString());
            psmt.setDate(3, Date.valueOf(plano.getDataInicio()));
            psmt.setDate(4, Date.valueOf(plano.getDataFinal()));
            psmt.setDouble(5, plano.getValor());
            psmt.setLong(6, plano.getId());

            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlano(Long idPlano) {
        String sql = "DELETE FROM Plano WHERE id_plano = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setLong(1, idPlano);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
