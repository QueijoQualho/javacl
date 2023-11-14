package com.javacl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javacl.classes.Contrato;
import com.javacl.classes.Empresa_cliente;
import com.javacl.classes.Plano;
import com.javacl.classes.Produto;
import com.javacl.classes.SistemaLogin;
import com.javacl.classes.interfaces.Nomeavel;
import com.javacl.classes.pessoa.Cliente;
import com.javacl.classes.pessoa.Funcionario;
import com.javacl.classes.pessoa.Usuario;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaLogin sistemaLogin = new SistemaLogin();
        List<Plano> listaPlanos = new ArrayList<Plano>();
        List<Empresa_cliente> listaEmpresas = new ArrayList<Empresa_cliente>();
        List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
        List<Contrato> listaContratos = new ArrayList<Contrato>();

        /* Exemplo produtos */
        Produto crm = new Produto(1, "CRM", 19.99);
        Produto cloud = new Produto(2, "Cloud Service", 29.99);
        Produto marketing = new Produto(3, "Marketing", 9.99);

        List<Produto> listaProdutos = new ArrayList<Produto>();
        listaProdutos.add(marketing);
        listaProdutos.add(crm);
        listaProdutos.add(cloud);

        /*
         * TODO
         * - Tratar exceçoes com try-catch
         * - utilizar metodo de autenticação do sistema login
         * - refatorar alguma partes do codigo como por exemplo o cadastroEmpresa
         */

        int opcao = 0;
        do {
            System.out.println("Selecione uma opção:\n" +
                    "1. Cadastrar Cliente\n" +
                    "2. Cadastrar Funcionario\n" +
                    "3. Montar plano\n" +
                    "4. Gerar contrato\n" +
                    "5. Lista de cadastros\n" +
                    "6. Sair");
            System.out.print("Digite o número da opção desejada: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Cliente novoCliente = sistemaLogin.cadastrarCliente(sc);
                    Empresa_cliente novaEmpresa = sistemaLogin.cadastroEmpresa(sc, novoCliente);
                    listaEmpresas.add(novaEmpresa);

                    System.out.println("Cadastro Cliente concluído");
                    break;
                case 2:
                    sistemaLogin.cadastrarFuncionario(sc);
                    System.out.println("Cadastro Funcionario concluído");
                    break;
                case 3:
                    System.out.println("Nome do plano:");
                    String nomePlano = sc.nextLine();

                    mostrarLista(listaProdutos);
                    String itens = sc.nextLine();

                    String[] listaItens = itens.split("[,\\s]+");

                    Plano newPlano = new Plano(nomePlano);

                    for (String e : listaItens) {
                        int num = Integer.parseInt(e);
                        switch (num) {
                            case 1:
                                newPlano.addProduto(marketing);
                                break;
                            case 2:
                                newPlano.addProduto(crm);
                                break;
                            case 3:
                                newPlano.addProduto(cloud);
                                break;
                        }
                    }

                    newPlano.calcValor();

                    System.out.println("Você quer um plano Anual ou Mensal?");
                    String tipoPlano = sc.nextLine();

                    newPlano.tipoPlano(tipoPlano);

                    System.out.println("Confira seu plano:"
                            + newPlano.toString()
                            + "Quer confimar esse plano? (Sim ou Nao)");
                    String escolha = sc.nextLine().toLowerCase();

                    if (escolha.equals("sim")) {
                        listaPlanos.add(newPlano);
                    } else {
                        System.out.println("Descartando plano...");
                    }

                    break;
                case 4:
                    for (Usuario usuario : sistemaLogin.getUsuarios()) {

                        if (usuario instanceof Funcionario) {
                            Funcionario funcionario = (Funcionario) usuario;

                            if (!listaFuncionarios.contains(funcionario)) {
                                listaFuncionarios.add(funcionario);
                            }
                        }
                    }

                    if (listaPlanos.isEmpty() || listaEmpresas.isEmpty() || listaFuncionarios.isEmpty()) {
                        System.out.println(
                                "É necessário ter planos, empresas e funcionários cadastrados para gerar um contrato.");
                    } else {
                        System.out.println("Selecione um plano disponível:");
                        mostrarLista(listaPlanos);
                        int planoSelecionado = sc.nextInt() - 1;
                        sc.nextLine();

                        System.out.println("Selecione uma empresa cliente:");
                        mostrarLista(listaEmpresas);
                        int empresaSelecionada = sc.nextInt() - 1;
                        sc.nextLine();

                        System.out.println("Selecione um funcionários disponíveis:");
                        mostrarLista(listaFuncionarios);
                        int funcionarioSelecionado = sc.nextInt() - 1;
                        sc.nextLine();

                        Funcionario funcionario = listaFuncionarios.get(funcionarioSelecionado);
                        Plano plano = listaPlanos.get(planoSelecionado);
                        Empresa_cliente empresa = listaEmpresas.get(empresaSelecionada);

                        Contrato contrato = new Contrato(funcionario, empresa, plano);

                        System.out.println("Escolha a forma de pagamento (credito ou boleto):");
                        String formaPagamento = sc.nextLine();

                        contrato.pagar(formaPagamento);

                        System.out.println("Contrato gerado com sucesso!");

                        listaContratos.add(contrato);
                    }
                    break;
                case 5:
                    System.out.println("Lista de Cadastro:"
                            + "Empresas:");
                    mostrarLista(listaEmpresas);
                    System.out.println("Funcionarios:");
                    mostrarLista(listaFuncionarios);
                    System.out.println("Planos:");
                    mostrarLista(listaPlanos);
                    System.out.println("Contratos:");
                    if (listaContratos.isEmpty()) {
                        System.out.println("A lista está vazia");
                    } else {
                        for (int i = 0; i < listaContratos.size(); i++) {
                            String nome = listaContratos.get(i).getEmpresa().getNome();
                            System.out.println((i + 1) + ". " + "Contrato - " + nome);
                        }
                    }

                    break;
                case 6:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 6);

    }

    static <T extends Nomeavel> void mostrarLista(List<T> data) {
        if (data.isEmpty()) {
            System.out.println("A lista está vazia");
            return;
        }

        for (int i = 0; i < data.size(); i++) {
            System.out.println((i + 1) + ". " + data.get(i).getNome());
        }
    }

}