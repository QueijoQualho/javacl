package com.javacl;

import java.util.Scanner;

import com.javacl.menus.ClienteMenu;
import com.javacl.menus.ContratoMenu;
import com.javacl.menus.EmpresaMenu;
import com.javacl.menus.FuncionarioMenu;
import com.javacl.menus.PlanoMenu;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            printMenu();
            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    ClienteMenu.menuCliente(sc);
                    break;
                case 2:
                    FuncionarioMenu.menuFuncionario(sc);
                    break;
                case 3:
                    PlanoMenu.menuPlano(sc);
                    break;
                case 4:
                    EmpresaMenu.menuEmpresa(sc);
                    break;
                case 5:
                    ContratoMenu.menuContrato(sc);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (true);
    }

    private static void printMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Menu Cliente");
        System.out.println("2 - Menu Funcionário");
        System.out.println("3 - Menu Plano");
        System.out.println("4 - Menu Empresa");
        System.out.println("5 - Menu Contrato");
        System.out.println("0 - Sair");
    }

}
