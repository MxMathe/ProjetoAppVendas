package com.example.docesdodia.model;

public class ItemCardapio {
    private int imagem;
    private String nome;
    private String descricao;
    private String valor;

    public ItemCardapio(int imagem, String nome, String descricao, String valor) {
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getImagem() {
        return imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getValor() {
        return valor;
    }
}
