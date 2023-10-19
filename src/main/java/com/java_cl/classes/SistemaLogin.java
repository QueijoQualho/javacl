package com.java_cl.classes;

import java.util.ArrayList;
import java.util.List;

import com.java_cl.classes.pessoa.Usuario;

public class SistemaLogin {
    public List<Usuario> usuarios = new ArrayList<Usuario>();    
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void Cadastro(Usuario pessoa) {
        this.usuarios.add(pessoa);
    }

    public boolean autenticarUser(String email, String senha){
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                System.out.println("Login realizado.");
                return true;
            }
        }
        System.out.println("Login falhou. Nome de usuário ou senha incorretos.");
        return false;
    }
}
