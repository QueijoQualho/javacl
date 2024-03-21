package com.javacl.repositorys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javacl.model.Endereco;
import com.javacl.model.pessoa.Cliente;
import com.javacl.model.pessoa.Usuario;

public class ClienteRepository extends UsuarioRepository {
    private EnderecoRepository enderecoRepo = new EnderecoRepository();

    @Override
    public List<Usuario> getUsuarios() {
        String sql = "SELECT u.* FROM Usuario u INNER JOIN Cliente c ON u.id_usuario = c.id_cliente ORDER BY u.nome ASC";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente newCliente = new Cliente();

                newCliente.setId(rs.getLong("id_usuario"));
                newCliente.setNome(rs.getString("nome"));
                newCliente.setTelefone(rs.getString("telefone"));
                newCliente.setEmail(rs.getString("email"));
                newCliente.setCpf(rs.getString("cpf"));
                newCliente.setCargo(rs.getString("cargo"));

                List<Endereco> enderecos = enderecoRepo.getEnderecosByUsuarioId(rs.getLong("id_usuario"));
                newCliente.setListaEnderecos(enderecos);

                usuarios.add(newCliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        String sql = "SELECT u.* FROM Usuario u INNER JOIN Cliente c ON u.id_usuario = c.id_cliente WHERE id_cliente = ?";
        Cliente cliente = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

                    List<Endereco> enderecos = enderecoRepo.getEnderecosByUsuarioId(rs.getLong("id_usuario"));
                    cliente.setListaEnderecos(enderecos);
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
        String sql = "INSERT INTO Usuario (nome, telefone, email, cpf, cargo, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id_usuario"})) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getCargo());
            stmt.setString(6, usuario.getSenha());

            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long idCliente = generatedKeys.getLong(1);
                usuario.setId(idCliente);
                enderecoRepo.saveEnderecos(usuario.getListaEnderecos(), usuario.getId());


                // Salvando o cliente
                if (usuario instanceof Cliente) {
                    Cliente cliente = (Cliente) usuario;
                    saveCliente(cliente);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar o usuário213123 no banco de dados!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        if (!(usuario instanceof Cliente)) {
            throw new IllegalArgumentException("O objeto passado não é um Cliente válido.");
        }

        Cliente cliente = (Cliente) usuario;
        String sql = "UPDATE Usuario SET nome = ?, telefone = ?, email = ?, cargo = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCargo());
            stmt.setLong(5, cliente.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o cliente no banco de dados!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o cliente do banco de dados!");
            e.printStackTrace();
        }
    }

    private void saveCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (id_cliente) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o cliente no banco de dados!");
            throw e;
        }
    }

    public static void main(String[] args) {
        // Criar uma instância do ClienteRepository
        ClienteRepository clienteRepository = new ClienteRepository();

        // Testar o método getUsuarios()
        List<Usuario> usuarios = clienteRepository.getUsuarios();
        System.out.println("Lista de Usuários:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNome());
        }

        // Testar o método getUsuarioById()
        System.out.println("\nUsuário com ID 1:");
        Usuario usuarioById = clienteRepository.getUsuarioById(1L);
        System.out.println(usuarioById.getNome());

        // Testar o método saveUsuario()
        List<Endereco> enderecos = new ArrayList<>();

        // Adicionar alguns endereços à lista
        enderecos.add(new Endereco(1L, 1L, "Rua A", "123", "Cidade A", "Estado A", "12345-678"));
        enderecos.add(new Endereco(2L, 2L, "Rua B", "456", "Cidade B", "Estado B", "98765-432"));
        enderecos.add(new Endereco(3L, 1L, "Rua C", "789", "Cidade C", "Estado C", "54321-876"));

        System.out.println("Criando cliente");
        Cliente novoCliente = new Cliente();
        novoCliente.setId(1L);
        novoCliente.setNome("Novo Cliente");
        novoCliente.setTelefone("123456789");
        novoCliente.setEmail("novo_cliente@example.com");
        novoCliente.setCpf("98765432142");
        novoCliente.setCargo("Cliente");
        novoCliente.setSenha("senha123");
        novoCliente.setListaEnderecos(null);
       /*  clienteRepository.saveUsuario(novoCliente);
        System.out.println("\nNovo cliente adicionado com sucesso!") */;

        // Testar o método updateUsuario()
        novoCliente.setNome("Novo Nome Cliente");
        clienteRepository.updateUsuario(novoCliente);
        System.out.println("\nCliente atualizado com sucesso!");

        // Testar o método deleteUsuario()
        clienteRepository.deleteUsuario(novoCliente.getId());
        System.out.println("\nCliente excluído com sucesso!");
    }
}
