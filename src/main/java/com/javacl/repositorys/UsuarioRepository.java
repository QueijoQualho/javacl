package com.javacl.repositorys;

import java.util.List;

import com.javacl.model.pessoa.Usuario;

public abstract class UsuarioRepository {
    public abstract List<Usuario> getUsuarios();
    public abstract Usuario getUsuarioById(Long id);
    public abstract void saveUsuario(Usuario usuario);
    public abstract void updateUsuario(Usuario usuario);
    public abstract void deleteUsuario(Long id);
}
