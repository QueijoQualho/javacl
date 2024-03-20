package com.javacl.repositorys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.pessoa.Funcionario;
import com.javacl.model.pessoa.Usuario;

public class FuncionarioRepository extends UsuarioRepository {

    @Override
    public List<Usuario> getUsuarios() {
        String sql = "SELECT * FROM USUARIO ORDER BY NOME ASC";
        List<Usuario> usuarios = new ArrayList<>();

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));

                usuarios.add(funcionario);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        String sql = "SELECT * FROM Funcionario WHERE id_usuario = ?";
        Funcionario funcionario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario();
                    funcionario.setId(rs.getLong("id_usuario"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setTelefone(rs.getString("telefone"));
                    funcionario.setEmail(rs.getString("email"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setSalario(rs.getDouble("salario"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return funcionario;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, telefone, email, cpf, cargo, salario, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            Funcionario funcionario = (Funcionario) usuario;
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getTelefone());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getCargo());
            stmt.setDouble(6, funcionario.getSalario());
            stmt.setString(7, funcionario.getSenha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        if (!(usuario instanceof Funcionario)) {
            throw new IllegalArgumentException("O objeto passado não é um Funcionario válido.");
        }

        Funcionario funcionario = (Funcionario) usuario;

        String sql = "UPDATE Funcionario SET nome = ?, telefone = ?, email = ?, cpf = ?, cargo = ?, salario = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getTelefone());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getCargo());
            stmt.setDouble(6, funcionario.getSalario());
            stmt.setLong(7, funcionario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o funcionário no banco de dados!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        String sql = "DELETE FROM Funcionario WHERE id_usuario = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o funcionário do banco de dados!");
            e.printStackTrace();
        }

    }
}
