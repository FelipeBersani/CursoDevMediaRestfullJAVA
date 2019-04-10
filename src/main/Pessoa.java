package main;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {

    private Long id;
    private String nome;
    private String nacionalidade;
    private int idade;

    public Pessoa(){}

    public Pessoa(Long id, String nome, String nacionalidade, int idade){
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
    }

    public static List<Pessoa> populaPessoas(){
        Pessoa p1 = new Pessoa((long) 1, "NOME1", "BRASILEIRO", 16);
        Pessoa p2 = new Pessoa((long) 2, "NOME2", "BRASILEIRO", 20);
        Pessoa p3 = new Pessoa((long) 3, "NOME3", "BRASILEIRO", 25);
        Pessoa p4 = new Pessoa((long) 4, "ANOME4", "ARGENTINO", 10);

        List<Pessoa> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        lista.add(p4);

        return lista;
    }

    public String toString(){
        return this.id+"/"+this.nome+"/"+this.nacionalidade+"/"+this.idade;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


}
