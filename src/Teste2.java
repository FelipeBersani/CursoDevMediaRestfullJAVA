import main.Pessoa;
import main.Teste;

import java.util.*;
import java.util.stream.Collectors;

public class Teste2 {

    public static void OrdenaHashMap(String[] args) {

    }

    public void testaHashMap() {
        Map<String, Pessoa> mapPessoa = new HashMap<>();
        mapPessoa = Pessoa.populaPessoasMap();
        System.out.println("1- Ordena map por ID (STREAM)");
        mapPessoa.entrySet()
                .stream()
                .sorted(Comparator.comparing(a -> a.getValue().getId()))
                .forEach(a -> System.out.println("1) " + a));

        System.out.println("\n2- Filtra map por ID e ordena decrescente por ID(STREAM)");
        mapPessoa.values()
                .stream()
                .filter(pessoa -> pessoa.getId() <= 5)
                .sorted(Comparator.comparing(Pessoa::getId).reversed())
                .forEach(a -> System.out.println("2) +" + a));

        System.out.println("\n3- Filtra map por IDs pares e retorna como lista ordenado por NACIONALIDADE crescente (STREAM)");
        List<Pessoa> listaPessoasPares = mapPessoa.values()
                .stream()
                .filter(a -> a.getId() % 2 == 0)
                .sorted(Comparator.comparing(a -> a.getNacionalidade()))
                .collect(Collectors.toList());
        listaPessoasPares.forEach(a -> System.out.println("3) " + a));

        System.out.println("\n4- Filtra map por IDs impares e retorna como lista ordenado por NOME decrescente E ID crescente (STREAM)");
        List<Pessoa> listaPessoasImpares = mapPessoa.values()
                .stream()
                .filter(a -> a.getId() % 2 == 1)
                .sorted(Comparator.comparing(Pessoa::getNome).reversed().thenComparing(Pessoa::getId))
                .collect(Collectors.toList());

        listaPessoasImpares
                .forEach(a -> System.out.println("4) " + a));

        System.out.println("\n5- Ordena map por key decrescente");
        mapPessoa.keySet()
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(out -> System.out.println("5) " + out));

        System.out.println("\n6- Exibe o valor do elemento com key = Quinta");
        System.out.println("6) " + mapPessoa.get("Quinta"));

        System.out.println("\n7- Exibe a nacionalidade do elemento com key = Quinta");
        System.out.println("7) " + mapPessoa.get("Quinta").getNacionalidade());

        System.out.println("\n8- Conta a quantidade de brasileiros no map");
        System.out.println("8) " + mapPessoa.entrySet()
                .stream()
                .filter(a -> a.getValue().getNacionalidade().equals("BRASILEIRO"))
                .count()
                + " brasileiros");

    }



    public static void main(String[] args) {
        Teste2 t2 = new Teste2();
        t2.testaHashMap();


    }

}
