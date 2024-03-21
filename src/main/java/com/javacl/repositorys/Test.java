package com.javacl.repositorys;

import java.time.LocalDate;
import java.util.List;

import com.javacl.model.Plano;
import com.javacl.model.enums.TipoPlano;

public class Test {
    public static void main(String[] args) {
        PlanoRepository planoRepo = new PlanoRepository();

/*         // Teste para obter todos os planos
        System.out.println("Todos os planos:");
        List<Plano> planos = planoRepo.getPlano();
        for (Plano plano : planos) {
            System.out.println(plano);
        } */

         // Teste para obter um plano por ID
        Long idPlano = 6L; // Suponha que o ID 1 exista no banco de dados
        System.out.println("\nPlano com ID " + idPlano + ":");
        Plano planoById = planoRepo.getPlanoById(idPlano);
        System.out.println(planoById); 

        // Teste para salvar um novo plano
       /*  Plano novoPlano = new Plano();
        novoPlano.setNomeFantasia("Novo Plano");
        novoPlano.setTipoPlano(TipoPlano.ANUAL);
        novoPlano.setDataInicio(LocalDate.now());
        novoPlano.setDataFinal(LocalDate.now().plusYears(1));
        novoPlano.setValor(100.0);
        // Adicione produtos ao novo plano, se necessário

        System.out.println("\nSalvando novo plano:");
        planoRepo.savePlano(novoPlano);
 */
/*          // Teste para atualizar um plano existente
        Long idPlanoParaAtualizar = 7L; // Suponha que o ID 2 exista no banco de dados
        Plano planoParaAtualizar = planoRepo.getPlanoById(idPlanoParaAtualizar);
        if (planoParaAtualizar != null) {
            planoParaAtualizar.setNomeFantasia("Plano Atualizado");
            planoParaAtualizar.setValor(200.0);

            System.out.println("\nAtualizando plano com ID " + idPlanoParaAtualizar + ":");
            planoRepo.updatePlano(planoParaAtualizar);
        } else {
            System.out.println("\nPlano com ID " + idPlanoParaAtualizar + " não encontrado para atualização.");
        } */

        // Teste para deletar um plano
        System.out.println("\nDeletando plano com ID " + idPlano + ":");
        planoRepo.deletePlano(idPlano);

        // Verifique se o plano foi realmente deletado, se necessário
        Plano planoDeletado = planoRepo.getPlanoById(idPlano);
        if (planoDeletado == null) {
            System.out.println("Plano com ID " + idPlano + " foi deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar o plano com ID " + idPlano + ".");
        } 
    }
}
