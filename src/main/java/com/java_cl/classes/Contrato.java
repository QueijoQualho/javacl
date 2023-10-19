package com.java_cl.classes;

import com.java_cl.classes.pessoa.Funcionario;

public class Contrato {
    private Funcionario funcio;
    private Empresa_cliente empresa;
    private Plano plano;

    public Contrato(Funcionario funcio, Empresa_cliente empresa, Plano plano) {
        this.funcio = funcio;
        this.empresa = empresa;
        this.plano = plano;
    }

    public Funcionario getFuncio() {
        return funcio;
    }

    public Empresa_cliente getEmpresa() {
        return empresa;
    }

    public Plano getPlano() {
        return plano;
    }

    /* Methods */

}
