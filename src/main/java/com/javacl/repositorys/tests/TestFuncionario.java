package com.javacl.repositorys.tests;

import java.util.ArrayList;
import java.util.List;

import com.javacl.model.Endereco;
import com.javacl.model.pessoa.Funcionario;
import com.javacl.model.pessoa.Usuario;
import com.javacl.repositorys.FuncionarioRepository;
import com.javacl.repositorys.UsuarioRepository;

public class TestFuncionario {
    public static void main(String[] args) {
        // Criando uma instância do repositório de funcionários
        UsuarioRepository funcionarioRepository = new FuncionarioRepository();
        
        List<Usuario> funcionarios = funcionarioRepository.getUsuarios();

        System.out.println("Funcionarios");
        for (Usuario usuario : funcionarios) {
            System.out.println(usuario.getNome());
        }

        List<Endereco> enderecos = new ArrayList<>();

        // Adicionar alguns endereços à lista
        enderecos.add(new Endereco(1L, "Rua A", "123", "Cidade A", "Estado A", "12345-678"));
        enderecos.add(new Endereco(2L,  "Rua B", "456", "Cidade B", "Estado B", "98765-432"));
        enderecos.add(new Endereco(3L, "Rua C", "789", "Cidade C", "Estado C", "54321-876"));

        // Testando a função de salvar um novo funcionário
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome("João Silva");
        novoFuncionario.setTelefone("(11) 91234-5678");
        novoFuncionario.setEmail("joao.silva@example.com");
        novoFuncionario.setCpf("123.a24.89-00");
        novoFuncionario.setCargo("Gerente");
        novoFuncionario.setSenha("sadsad");
        novoFuncionario.setSalario(5000.0); 
        novoFuncionario.setListaEnderecos(enderecos);

        funcionarioRepository.saveUsuario(novoFuncionario);
        System.out.println("Novo funcionário salvo com sucesso!");

        // Testando a função de buscar um funcionário pelo ID
        Long idFuncionarioNovo = novoFuncionario.getId();
        Funcionario funcionarioRecuperado = (Funcionario) funcionarioRepository.getUsuarioById(idFuncionarioNovo);
        if (funcionarioRecuperado != null) {
            System.out.println("Funcionário recuperado:");
            System.out.println(funcionarioRecuperado);
        } else {
            System.out.println("Funcionário não encontrado!");
        }

        // Testando a função de atualizar um funcionário
        funcionarioRecuperado.setNome("João da Silva");
        funcionarioRepository.updateUsuario(funcionarioRecuperado);
        System.out.println("Funcionário atualizado com sucesso!");

        // Testando a função de deletar um funcionário
        funcionarioRepository.deleteUsuario(idFuncionarioNovo);
        System.out.println("Funcionário deletado com sucesso!"); 
    }
}
