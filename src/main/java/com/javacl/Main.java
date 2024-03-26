package com.javacl;

import java.util.List;
import java.util.Scanner;

import com.javacl.menus.ClienteMenu;
import com.javacl.menus.EmpresaMenu;
import com.javacl.menus.FuncionarioMenu;
import com.javacl.menus.PlanoMenu;
import com.javacl.model.EmpresaCliente;
import com.javacl.model.Plano;
import com.javacl.model.pessoa.Funcionario;
import com.javacl.model.pessoa.Usuario;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            printMenu();
            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    menuCliente(sc);
                    break;
                case 2:
                    menuFuncionario(sc);
                    break;
                case 3:
                    menuPlano(sc);
                    break;
                case 4:
                    menuEmpresa(sc);
                    break;
                case 5:
                    // Implementar menu do contrato
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

    private static void menuCliente(Scanner sc) {
        System.out.println("Menu Cliente:");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Atualizar Cliente");
        System.out.println("3 - Excluir Cliente");
        System.out.println("4 - Visualizar Clientes");
        System.out.println("0 - Voltar ao menu principal");

        int opcaoCliente = Integer.parseInt(sc.nextLine());
        switch (opcaoCliente) {
            case 1:
                ClienteMenu.cadastrarCliente(sc);
                break;
            case 2:
                ClienteMenu.atualizarCliente(sc);
                break;
            case 3:
                ClienteMenu.deletarCliente(sc);
                break;
            case 4:
                List<Usuario> clientes = ClienteMenu.pegarCliente();
                for (Usuario usuario : clientes) {
                    System.out.println(usuario.toString());
                }
                break;
            case 0:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void menuFuncionario(Scanner sc) {
        System.out.println("Menu Funcionário:");
        System.out.println("1 - Cadastrar Funcionário");
        System.out.println("2 - Atualizar Funcionário");
        System.out.println("3 - Excluir Funcionário");
        System.out.println("4 - Visualizar Funcionários");
        System.out.println("0 - Voltar ao menu principal");

        int opcaoFuncionario = Integer.parseInt(sc.nextLine());
        switch (opcaoFuncionario) {
            case 1:
                FuncionarioMenu.cadastrarFuncionario(sc);
                break;
            case 2:
                FuncionarioMenu.atualizarFuncionario(sc);
                break;
            case 3:
                FuncionarioMenu.deletarFuncionario(sc);
                break;
            case 4:
                List<Usuario> funcionarios = FuncionarioMenu.pegarFuncionario(sc);
                for (Usuario usuario : funcionarios) {
                    if (usuario instanceof Funcionario) {
                        Funcionario funcionario = (Funcionario) usuario;
                        System.out.println(funcionario.toString());
                    }
                }
                break;
            case 0:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void menuPlano(Scanner sc) {
        System.out.println("Menu Plano:");
        System.out.println("1 - Cadastrar Plano");
        System.out.println("2 - Atualizar Plano");
        System.out.println("3 - Excluir Plano");
        System.out.println("4 - Visualizar Planos");
        System.out.println("0 - Voltar ao menu principal");

        int opcaoPlano = Integer.parseInt(sc.nextLine());
        switch (opcaoPlano) {
            case 1:
                PlanoMenu.cadastrarPlano(sc);
                break;
            case 2:
                PlanoMenu.atualizarPlano(sc);
                break;
            case 3:
                PlanoMenu.deletarPlano(sc);
                break;
            case 4:
                PlanoMenu.pegarPlanos();
                List<Plano> planos = PlanoMenu.pegarPlanos();

                for (Plano plano : planos) {
                    System.out.println(plano.toString());
                }

                break;
            case 0:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void menuEmpresa(Scanner sc) {
        System.out.println("Menu Empresa:");
        System.out.println("1 - Cadastrar Empresa");
        System.out.println("2 - Atualizar Empresa");
        System.out.println("3 - Excluir Empresa");
        System.out.println("4 - Visualizar Empresas");
        System.out.println("0 - Voltar ao menu principal");

        int opcaoEmpresa = Integer.parseInt(sc.nextLine());
        switch (opcaoEmpresa) {
            case 1:
                EmpresaMenu.cadastrarEmpresa(sc);
                break;
            case 2:
                EmpresaMenu.atualizarEmpresa(sc);
                break;
            case 3:
                EmpresaMenu.deletarEmpresa(sc);
                break;
            case 4:
                List<EmpresaCliente> empresas = EmpresaMenu.pegarEmpresas(sc);

                for (EmpresaCliente empresaCliente : empresas) {
                    System.out.println(empresaCliente);
                }
                break;
            case 0:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

}
