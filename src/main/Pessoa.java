package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Pessoa p1 = new Pessoa(1L, "NOME1", "BRASILEIRO", 16);
        Pessoa p2 = new Pessoa(2L, "NOME2", "BRASILEIRO", 20);
        Pessoa p3 = new Pessoa(3L, "NOME3", "BRASILEIRO", 25);
        Pessoa p4 = new Pessoa(4L, "ANOME4", "ARGENTINO", 10);

        List<Pessoa> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        lista.add(p4);

        return lista;
    }


    public static Map<String, Pessoa> populaPessoasMap(){
        Pessoa p1 = new Pessoa(1L, "NOME1", "BRASILEIRO", 16);
        Pessoa p2 = new Pessoa(2L, "NOME2", "BRASILEIRO", 20);
        Pessoa p3 = new Pessoa(3L, "NOME3", "BRASILEIRO", 25);
        Pessoa p4 = new Pessoa(4L, "ANOME4", "ARGENTINO", 10);
        Pessoa p5 = new Pessoa(5L, "ANOME4", "ARGENTINO", 10);
        Pessoa p6 = new Pessoa(6L, "ANOME4", "ARGENTINO", 10);
        Pessoa p7 = new Pessoa(7L, "ANOME4", "ARGENTINO", 10);
        Pessoa p8 = new Pessoa(8L, "ANOME4", "ARGENTINO", 10);
        Pessoa p9 = new Pessoa(9L, "ANOME4", "ARGENTINO", 10);
        Pessoa p10 = new Pessoa(10L, "ANOME4", "ARGENTINO", 10);

        Map<String, Pessoa> mapPessoa = new HashMap<>();

        mapPessoa.put("Primeira", p1);
        mapPessoa.put("Segunda", p2);
        mapPessoa.put("Terceira", p3);
        mapPessoa.put("Quarta", p4);

        return mapPessoa;
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
