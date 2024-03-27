package com.javacl.model;

import com.javacl.model.enums.TipoPagamento;
import com.javacl.model.pessoa.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {
    private Long id;
    private Plano plano;
    private Funcionario funcionario;
    private EmpresaCliente empresa;
    private TipoPagamento tipoPagamento;

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", plano=" + plano +
                ", funcionario=" + funcionario +
                ", empresa=" + empresa +
                ", tipoPagamento=" + tipoPagamento +
                '}';
    }

}
