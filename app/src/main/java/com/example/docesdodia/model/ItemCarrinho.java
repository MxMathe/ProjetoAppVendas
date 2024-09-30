package com.example.docesdodia.model;

public class ItemCarrinho {
    private String nome;
    private String descricao;
    private String valor;

    public ItemCarrinho() {
        // Construtor vazio necessário para a desserialização do Firestore
    }

    public ItemCarrinho(String nome, String descricao, String valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}