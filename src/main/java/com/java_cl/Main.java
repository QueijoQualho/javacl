package com.java_cl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java_cl.classes.Contrato;
import com.java_cl.classes.Empresa_cliente;
import com.java_cl.classes.Plano;
import com.java_cl.classes.Produto;
import com.java_cl.classes.SistemaLogin;
import com.java_cl.classes.pessoa.Cliente;
import com.java_cl.classes.pessoa.Funcionario;
import com.java_cl.classes.pessoa.Usuario;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaLogin sistemaLogin = new SistemaLogin();
        List<Plano> listaPlanos = new ArrayList<Plano>();
        List<Empresa_cliente> listaEmpresas = new ArrayList<Empresa_cliente>();
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        /* Exemplo produtos */
        Produto crm = new Produto(1, "CRM", 19.99);
        Produto cloud = new Produto(2, "Cloud Service", 29.99);
        Produto marketing = new Produto(3, "Marketing", 9.99);

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
                    "5. Sair");
            System.out.print("Digite o número da opção desejada: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Empresa_cliente novaEmpresa = cadastroEmpresa(sc, sistemaLogin.cadastrarCliente(sc));
                    listaEmpresas.add(novaEmpresa);

                    System.out.println("Cadastro Cliente concluído");
                    break;
                case 2:
                    sistemaLogin.cadastrarFuncionario(sc);
                    System.out.println("Cadastro Funcionario concluído");
                    break;
                case 3:
                    System.out.println("Quais serviços você vai querer: \n" +
                            "1. CRM \n" +
                            "2. Cloud services \n" +
                            "3. Marketing");
                    String itens = sc.nextLine();

                    /*
                     * Guia Regex:
                     * https://medium.com/xp-inc/regex-um-guia-pratico-para-expressões-regulares-
                     * 1ac5fa4dd39f
                     */
                    String[] listaItens = itens.split("[,\\s]+");

                    Plano newPlano = new Plano();

                    for (String e : listaItens) {
                        int num = Integer.parseInt(e);
                        switch (num) {
                            case 1:
                                newPlano.addProduto(crm);
                                break;
                            case 2:
                                newPlano.addProduto(cloud);
                                break;
                            case 3:
                                newPlano.addProduto(marketing);
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
                        /* https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Reference/Operators/instanceof */
                        if (usuario instanceof Funcionario) {
                            listaFuncionarios.add((Funcionario) usuario);
                        }
                    }

                    if (listaPlanos.isEmpty() || listaEmpresas.isEmpty() || listaFuncionarios.isEmpty()) {
                        System.out.println(
                                "É necessário ter planos, empresas e funcionários cadastrados para gerar um contrato.");
                    } else {
                        System.out.println("Selecione um plano disponível:");
                        for (int i = 0; i < listaPlanos.size(); i++) {
                            System.out.println(i + 1 + ". Plano " + (i + 1));
                        }
                        int planoSelecionado = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Selecione uma empresa cliente:");
                        for (int i = 0; i < listaEmpresas.size(); i++) {
                            System.out.println(i + 1 + ". " + listaEmpresas.get(i).getNomeFantasia());
                        }
                        int empresaSelecionada = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Funcionários disponíveis:");
                        for (int i = 0; i < listaFuncionarios.size(); i++) {
                            System.out.println((i + 1) + ". " + listaFuncionarios.get(i).getNome());
                        }
                        int funcionarioSelecionado = sc.nextInt();
                        sc.nextLine();

                        Funcionario funcionario = listaFuncionarios.get(funcionarioSelecionado - 1);
                        Plano plano = listaPlanos.get(planoSelecionado - 1);
                        Empresa_cliente empresa = listaEmpresas.get(empresaSelecionada - 1);

                        Contrato contrato = new Contrato(funcionario, empresa, plano);

                        System.out.println("Escolha a forma de pagamento (credito ou boleto):");
                        String formaPagamento = sc.nextLine();

                        contrato.pagar(formaPagamento);

                        System.out.println("Contrato gerado com sucesso!");
                    }
                    break;
                case 5:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);

    }

    static Empresa_cliente cadastroEmpresa(Scanner sc, Cliente representante) {
        System.out.println("Cadastre a sua empresa:");

        System.out.println("Digite o CNPJ: ");
        String cnpj = sc.nextLine();

        System.out.println("Digite o telefone: ");
        String telefone = sc.nextLine();

        System.out.println("Digite a razão social: ");
        String razaoSocial = sc.nextLine();

        System.out.println("Digite o nome fantasia: ");
        String nomeFantasia = sc.nextLine();

        System.out.println("Digite o numero de funcionarios: ");
        int tamanho = sc.nextInt();
        sc.nextLine();

        Empresa_cliente empresa = new Empresa_cliente(representante, cnpj, telefone, razaoSocial, nomeFantasia,
                tamanho);

        return empresa;

    }
}