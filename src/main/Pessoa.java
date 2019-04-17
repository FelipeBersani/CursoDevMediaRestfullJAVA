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
        Pessoa p1 = new Pessoa(1L, "ANOME1", "BRASILEIRO", 15);
        Pessoa p2 = new Pessoa(2L, "ANOME2", "ARGENTINO", 20);
        Pessoa p3 = new Pessoa(3L, "ANOME3", "BRASILEIRO", 25);
        Pessoa p4 = new Pessoa(4L, "ANOME4", "ARGENTINO", 30);
        Pessoa p5 = new Pessoa(5L, "ANOME5", "BRASILEIRO", 35);
        Pessoa p6 = new Pessoa(6L, "BNOME6", "ARGENTINO", 40);
        Pessoa p7 = new Pessoa(7L, "BNOME7", "BRASILEIRO", 45);
        Pessoa p8 = new Pessoa(8L, "BNOME8", "ARGENTINO", 50);
        Pessoa p9 = new Pessoa(9L, "BNOME9", "BRASILEIRO", 55);
        Pessoa p10 = new Pessoa(10L, "BNOME10", "ARGENTINO", 60);
        Pessoa p11 = new Pessoa(11L, "BNOME9", "BRASILEIRO", 65);
        Pessoa p12 = new Pessoa(12L, "BNOME912", "ARGENTINO", 70);

        Map<String, Pessoa> mapPessoa = new HashMap<>();

        mapPessoa.put("Primeira", p1);
        mapPessoa.put("Segunda", p2);
        mapPessoa.put("Terceira", p3);
        mapPessoa.put("Quarta", p4);
        mapPessoa.put("Quinta", p5);
        mapPessoa.put("Sexta", p6);
        mapPessoa.put("Setima", p7);
        mapPessoa.put("Oitava", p8);
        mapPessoa.put("Nona", p9);
        mapPessoa.put("Decima", p10);
        mapPessoa.put("DecimaPrimeira", p11);
        mapPessoa.put("DecimaSegunda", p12);

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
