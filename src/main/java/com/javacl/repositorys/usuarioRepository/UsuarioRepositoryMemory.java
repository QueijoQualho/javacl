package com.javacl.repositorys.usuarioRepository;

import java.util.ArrayList;
import java.util.List;

import com.javacl.model.pessoa.Usuario;

public class UsuarioRepositoryMemory extends UsuarioRepository {
    private static List<Usuario> LISTA_USUARIOS = new ArrayList<>();

    @Override
    public List<Usuario> getUsuarios() {
        return UsuarioRepositoryMemory.LISTA_USUARIOS;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        UsuarioRepositoryMemory.LISTA_USUARIOS.add(usuario);
    }

}
