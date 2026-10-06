package com.example.docesdodia.model;

public class Pedido {
    private String nomeCliente, descricao, endereco, data;

    public Pedido() { } // Construtor vazio para Firestore

    public Pedido(String nomeCliente, String descricao, String endereco, String data) {
        this.nomeCliente = nomeCliente;
        this.descricao = descricao;
        this.endereco = endereco;
        this.data = data;
    }

    public String getNomeCliente() { return nomeCliente; }
    public String getDescricao() { return descricao; }
    public String getEndereco() { return endereco; }
    public String getData() { return data; }
}

