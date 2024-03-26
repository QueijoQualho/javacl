package com.javacl.menus;

import java.util.Scanner;

import com.javacl.model.Endereco;

public class EnderecoMenu {

    public static Endereco cadastroEndereco(Scanner sc) {
        System.out.println("Informações de Endereço:");
        System.out.print("Rua: ");
        String rua = sc.nextLine();

        System.out.print("Numero:");
        String num = sc.nextLine();

        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        System.out.print("Estado: ");
        String estado = sc.nextLine();

        System.out.print("CEP: ");
        String cep = sc.nextLine();

        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setNumero(num);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);

        return endereco;
    }

}
