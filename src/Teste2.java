import jersey.repackaged.com.google.common.collect.MapDifference;
import jersey.repackaged.com.google.common.collect.Maps;
import main.Pessoa;
import main.Teste;

import java.util.*;
import java.util.stream.Collectors;

public class Teste2 {

    public static void OrdenaHashMap(String[] args) {

    }

    public void testaHashMap() {
        Map<String, Pessoa> mapPessoa = Pessoa.populaPessoasMap();
        Map<String, Pessoa> mapPesso2 = Pessoa.populaPessoasMap();
        mapPesso2.remove("Quinta");
        mapPesso2.remove("Sexta");
        mapPesso2.remove("Setima");
        mapPesso2.remove("Oitava");
        mapPesso2.remove("Nona");
        mapPesso2.remove("Decima");



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

        System.out.println("\n9- Valores iguais MAPDIFFERENCE");
        MapDifference<String, Pessoa> iguais = Maps.difference(mapPesso2, mapPessoa);
        System.out.println("9) "+ iguais.entriesDiffering()
                .values()
                .stream()
                .map(a -> a.rightValue())
                .sorted(Comparator.comparing(a -> a.getId()))
                .collect(Collectors.toList()));

        System.out.println("\n10- Valores diferentes MAPDIFFERENCE");
        MapDifference<String, Pessoa> diferenca = Maps.difference(mapPesso2, mapPessoa);
        System.out.println("10) "+ diferenca.entriesOnlyOnRight()
                .values()
                .stream()
                .sorted(Comparator.comparing(Pessoa::getId))
                .collect(Collectors.toList()));




    }



    public static void main(String[] args) {
        Teste2 t2 = new Teste2();
        t2.testaHashMap();


    }

}
