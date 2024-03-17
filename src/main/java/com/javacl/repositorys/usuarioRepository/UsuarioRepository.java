package com.javacl.repositorys.usuarioRepository;

import java.sql.SQLException;
import java.util.List;

import com.javacl.model.pessoa.Usuario;

public abstract class UsuarioRepository {
    
    public abstract List<Usuario> getUsuarios(); 
    public abstract void saveUsuario(Usuario usuario) throws SQLException; 
}
