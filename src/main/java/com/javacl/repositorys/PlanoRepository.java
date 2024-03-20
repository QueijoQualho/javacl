package com.javacl.repositorys;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String sql = "SELECT * FROM Plano ORDER BY descricao ASC";
        List<Plano> planos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return planos;
    }

    public Plano getPlanoById(Long idPlano) {
        String sql = "SELECT * FROM Plano WHERE id_plano = ?";
        Plano plano = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idPlano);
            try (ResultSet rs = stmt.executeQuery()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return plano;
    }

    public void savePlano(Plano plano) {
        String sql = "INSERT INTO Plano (nomeFantasia, tipoPlano, dataInicio, dataFinal, valor) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, plano.getNomeFantasia());
            stmt.setString(2, plano.getTipoPlano().toString());
            stmt.setDate(3, Date.valueOf(plano.getDataInicio()));
            stmt.setDate(4, Date.valueOf(plano.getDataFinal()));
            stmt.setDouble(5, plano.getValor());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long idPlano = generatedKeys.getLong(1);

                for (Produto produto : plano.getListaProdutos()) {
                    produto.setIdPlano(idPlano);
                    prodRepo.saveProduto(produto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePlano(Plano plano) {
        String sql = "UPDATE Plano SET nomeFantasia = ?, tipoPlano = ?, dataInicio = ?, dataFinal = ?, valor = ? WHERE id_plano = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, plano.getNomeFantasia());
            stmt.setString(2, plano.getTipoPlano().toString());
            stmt.setDate(3, Date.valueOf(plano.getDataInicio()));
            stmt.setDate(4, Date.valueOf(plano.getDataFinal()));
            stmt.setDouble(5, plano.getValor());
            stmt.setLong(6, plano.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlano(Long idPlano) {
        String sqlDeletePlano = "DELETE FROM Plano WHERE id_plano = ?";

        try (PreparedStatement stmtDeletePlano = connection.prepareStatement(sqlDeletePlano)) {

            stmtDeletePlano.setLong(1, idPlano);
            stmtDeletePlano.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
