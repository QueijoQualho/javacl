package com.javacl.model;

public class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String rua, String numero, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco(String enderecoString) {
        String[] partes = enderecoString.split(",");
        if (partes.length == 5) {
            this.rua = partes[0].trim();
            this.numero = partes[1].trim();
            this.cidade = partes[2].trim();
            this.estado = partes[3].trim();
            this.cep = partes[4].trim();
        } else {
            throw new IllegalArgumentException("Formato de endereço inválido: " + enderecoString);
        }
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return "Endereco [rua=" + rua + ", numero=" + numero + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + "]";
    }
}

