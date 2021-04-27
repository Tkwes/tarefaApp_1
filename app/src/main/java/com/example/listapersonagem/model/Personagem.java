package com.example.listapersonagem.model;

import java.io.Serializable;

public class Personagem implements Serializable {

    //dedinindo variaves privadas, caracteres;
    private String nome;
    private String altura;
    private String nascimento;
    //dedinindo variavel inteira;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento) {

        //adiciona o nome a variavel alocada;
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    public Personagem() {

    }

    //Setter, Getter;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    //Sobreescrevendo retorno para nome

    @Override
    public String toString() {
        return nome;
    }

    //setar posicao na lista com id

    public void setId(int id) {
        this.id = id;
    }

    //retorna localizadao na lista

    public int getId() {
        return id;
    }

    //validando id

    public boolean IdValido() {
        return id > 0;
    }
}
