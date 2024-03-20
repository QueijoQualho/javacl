package com.javacl.repositorys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.pessoa.Cliente;
import com.javacl.model.pessoa.Usuario;

public class ClienteRepository extends UsuarioRepository {
    private Connection getConnection() {
        String url = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";
        String usuario = "rm553912";
        String senha = "141204";

        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        String sql = "SELECT * FROM USUARIO ORDER BY NOME ASC";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection con = this.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setCargo(rs.getString("cargo"));

                usuarios.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        String sql = "SELECT * FROM Cliente WHERE id_usuario = ?";
        Cliente cliente = null;

        try (Connection con = this.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getLong("id_usuario"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setCargo(rs.getString("cargo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, telefone, email, cpf, cargo, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = this.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getCargo());
            stmt.setString(6, usuario.getSenha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        if (!(usuario instanceof Cliente)) {
            throw new IllegalArgumentException("O objeto passado não é um Cliente válido.");
        }

        Cliente cliente = (Cliente) usuario;

        String sql = "UPDATE Cliente SET nome = ?, telefone = ?, email = ?, cpf = ?, cargo = ? WHERE id_usuario = ?";

        try (Connection con = this.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getCargo());
            stmt.setLong(6, cliente.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o cliente no banco de dados!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        String sql = "DELETE FROM Cliente WHERE id_usuario = ?";

        try (Connection con = this.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o cliente do banco de dados!");
            e.printStackTrace();
        }

    }
}
