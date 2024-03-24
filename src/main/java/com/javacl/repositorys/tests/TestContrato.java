package com.javacl.repositorys.tests;

import com.javacl.model.Contrato;
import com.javacl.model.enums.TipoPagamento;
import com.javacl.repositorys.ContratoRepository;

public class TestContrato {
    public static void main(String[] args) {
        // Crie uma instância do ContratoRepository
        ContratoRepository contratoRepository = new ContratoRepository();

        // Criar e salvar um novo contrato


        // Buscar todos os contratos e imprimir
        System.out.println("Todos os contratos:");
        contratoRepository.getAllContratos().forEach(System.out::println);

        // Buscar um contrato pelo ID e imprimir
        long contratoId = 1; // Suponha que 1 é o ID do contrato que você deseja buscar
        System.out.println("\nContrato com ID " + contratoId + ":");
        Contrato contratoEncontrado = contratoRepository.getContratoById(contratoId);
        System.out.println(contratoEncontrado);

        // Atualizar um contrato existente
        Contrato contratoParaAtualizar = contratoRepository.getContratoById(contratoId);
        if (contratoParaAtualizar != null) {
            contratoParaAtualizar.setTipoPagamento(TipoPagamento.DEBITO);
            contratoRepository.updateContrato(contratoParaAtualizar);
        }

        // Deletar um contrato pelo ID
        long contratoIdParaDeletar = 2; // Suponha que 2 é o ID do contrato que você deseja deletar
        contratoRepository.deleteContrato(contratoIdParaDeletar);
    }
}
