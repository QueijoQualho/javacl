package com.javacl.repositorys.usuarioRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.javacl.model.pessoa.Usuario;

public class UsuarioRepositoryJDBC extends UsuarioRepository {
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
		return null;
	}

	@Override
	public void saveUsuario(Usuario usuario) throws SQLException {
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
		}
	}
}
