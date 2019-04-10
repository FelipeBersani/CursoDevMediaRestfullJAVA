package main;

import com.mysql.cj.core.util.StringUtils;
import jersey.repackaged.com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.ClassUtils;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Teste {

    public Map<String,String> getMapa(){
        Map<String,String> teste = new HashMap<>();
        teste.put("SP", "São Paulo");
        teste.put("SPT", "São Paulo");
        teste.put("SP2T", "São Paulo");
        teste.put("SP42T", "São Paulo");
        teste.put("RJ", "Rio de Janeiro");
        teste.put("BA", "BAHIA");
        teste.put("PR", "Curitiba");
        return teste;
    }

    public static Map<String, String> ordenaMap(Map<String, String> map){
        map = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return map;
    }

    public static void main(String[] args) {
        Map<String, String> testeMap;
        Teste teste = new Teste();
        testeMap = teste.getMapa();

        Map<String, String> mapTeste = ordenaMap(testeMap);
        //mapTeste.forEach((key, value) -> System.out.println("KEY: "+key+", "+ value));
        List<String> listaValores = new ArrayList(mapTeste.keySet());

        Long countt = listaValores
                .stream()
                .filter(cidade -> cidade.startsWith("S"))
                .count();

        System.out.println("Total cidades começam com S: " +countt);


        //foreach key value
        for(Map.Entry<String, String> entrada : testeMap.entrySet()){
            System.out.println("FOREACH KEY VALUE|key "+entrada.getKey()+", Value "+entrada.getValue());
        }

        //foreach value
        for(String valor : testeMap.values()){
            System.out.println("FOREACH VALUE|Value "+ valor);

        }

        List<String> listValues = new ArrayList<>(testeMap.values());
        Collections.sort(listValues);
        listValues.forEach(val -> System.out.println("ListValue| "+val));


        testeMap.values().stream().forEach(val -> System.out.println("VALUE| "+val));
        testeMap.keySet().stream().forEach(key -> System.out.println("KEY| "+key));


        List<String> testeStream = new ArrayList<>();
        testeStream.add("S1");
        testeStream.add("S2");
        testeStream.add("S3");
        testeStream.add("3");
        testeStream.add("2");

        Stream<String> stream = testeStream.stream();
        Long conta = stream.filter(c -> c.startsWith("S"))
                .count();
        System.out.println("SomaStream: "+conta);

        Long conta2 = testeStream.stream()
                .filter(c -> c.startsWith("S"))
                .count();
        System.out.println("SomaStreamSimples: "+conta2);

        //JAVA LAMBDA
        Function<Integer, Integer> function = (x) -> x*3 + 2;
        Integer resultado = function.apply(1);
        System.out.print("O resultado do lambda é: "+resultado);


        //TESTE PESSOA
        List<Pessoa> listaPessoa = new Pessoa().populaPessoas();
        Long testaQtde = listaPessoa.stream()
                            .filter(p -> p.getNacionalidade().startsWith("BRASILEIRO"))
                            .count();

        System.out.println("\nQtde brasileiros: "+ testaQtde);


        List<Pessoa> pessoas = new Pessoa().populaPessoas();
        Stream<Integer> streamTest = pessoas.stream()
                .filter(p -> p.getNome().startsWith("NOME"))
                .sorted(Comparator.comparing(Pessoa::getIdade).reversed())
                .limit(2)
                .map(Pessoa::getIdade);

        streamTest.forEach(c-> System.out.println("Idade: "+c));

        pessoas.stream()
                .filter(p-> p.getNome().startsWith("NOME"))
                .sorted(Comparator.comparing(Pessoa::getIdade).reversed())
                .limit(2)
                .forEach(p-> System.out.println("IdadePessoaStream: "+p.getIdade()));

        double media = pessoas.stream()
                .filter(p-> p.getNacionalidade().equals("BRASILEIRO"))
                .mapToInt(pessoa -> pessoa.getIdade())
                .average()
                .getAsDouble();

        System.out.println("Media: "+media);


        OptionalDouble somaIdades = pessoas.stream()
                .filter(p -> p.getNacionalidade().equals("BRASILEIRO"))
                .mapToDouble(pessoa -> pessoa.getIdade())
                .average();

        if(somaIdades.isPresent()){
            System.out.println("SomaIdades: "+somaIdades.getAsDouble());
        }else{
            System.out.println("SomaVazia");
        }


        OptionalInt maiorIdade = pessoas.stream()
                .filter(p -> p.getNacionalidade().equals("BRASILEIRO"))
                .mapToInt(Pessoa::getIdade)
                .max();

        System.out.println("Maior idade: "+maiorIdade.getAsInt());


        List<Pessoa> qtdPessoasComMenosDe19Anos = pessoas.stream()
                .filter(p -> p.getIdade()<19)
                .sorted(Comparator.comparing(Pessoa::getIdade).reversed())
                .collect(Collectors.toList());

        System.out.println(qtdPessoasComMenosDe19Anos.size());
        for(Pessoa p : qtdPessoasComMenosDe19Anos){
            System.out.println(p.getNome()+" tem "+p.getIdade()+" anos.");
        }

        Map<Long, String> qtdPessoasComMenosDe19AnosMap = pessoas.stream()
                .filter(p -> p.getIdade()<19)
                .sorted(Comparator.comparing(Pessoa::getIdade).reversed())
                .collect(Collectors.toMap(Pessoa::getId, Pessoa::getNacionalidade));

        for(Map.Entry<Long, String> maap : qtdPessoasComMenosDe19AnosMap.entrySet()){
            System.out.println("KeyMAP: "+maap.getKey()+", ValueMAP,: "+maap.getValue());
        }


        Optional<Pessoa> pessoa = pessoas.stream()
                .filter(p -> p.getNacionalidade().equals("BRASILEIRO"))
                .findFirst();

        System.out.println("Primeira pessoa: "+pessoa.get().getNome());


        Predicate<Pessoa> p1 = p -> p.getIdade() < 15;
        boolean boolTeste = pessoas.stream()
                .anyMatch(p1);

        System.out.println("ReturnBOOL: "+boolTeste);


        StringBuffer sb = new StringBuffer();
        Integer i = 0;
        for(String value: qtdPessoasComMenosDe19AnosMap.values()){
            sb = i==0 ? sb.append(value.toString()+"-") : sb.append(value.toString());
            i++;
        }
        System.out.println("NACIONALIDADE| "+sb);


        List<String> listPessoa = Arrays.asList("Pessoa", "Pessoa2", "Pessoa3");
        String result = listPessoa.stream()
                .collect(Collectors.joining("-", "[", "]"));
        System.out.println("STREAM COM TRAÇO| "+result);


        listPessoa = Arrays.asList("Pessoa", "Pessoa2", "Pessoa3", "Arnaldo");
        result = listPessoa.stream()
                .collect(Collectors.joining(", "));
        System.out.println("STREAM COM VIRGULA| "+result);


        List<Pessoa> listDePessoas = Pessoa.populaPessoas();
        Map<Long, List<Pessoa>> pessoaMap = listDePessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getId));
        System.out.println("MAP| "+pessoaMap);

        List<String> primeiraPessoa = listPessoa.stream()
                .filter(p-> p.startsWith("Pessoa"))
                .collect(Collectors.toList());

        System.out.println("ListPessoa| "+primeiraPessoa);



        Optional<Pessoa> pessoaS = pessoas.stream()
                .filter(p -> p.getId()<5)
                .sorted(Comparator.comparing(Pessoa::getNome).reversed())
                .limit(1)
                .findFirst();

        System.out.println("GET NOME ID<5| "+pessoaS.get().getNome());



        String nome = pessoas.stream()
                .sorted(Comparator.comparing(Pessoa::getNome).reversed())
                .map(Pessoa::getNome)
                .limit(1)
                .collect(Collectors.joining());

        System.out.println("GET NOME POR COLLECT| "+nome);


        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dddd");
        Long resultadoMaisQueTresDigitos = givenList.stream()
                .filter(g -> g.length()>=3)
                .count();
        System.out.println("QTDE MAIS DE 3 DIGITOS| "+resultadoMaisQueTresDigitos);


        String nomeResultadoMaisQueTresDigitos = givenList.stream()
                .filter(g -> g.length()>=3)
                .collect(Collectors.joining(", "));
        System.out.println("NOME MAIS DE 3 DIGITOS| "+nomeResultadoMaisQueTresDigitos);


        List<Integer> listaNumeros = Arrays.asList(3, 4, 6, 7);
        Double somaNumeros = listaNumeros.stream()
                .mapToDouble(p -> p.doubleValue())
                .sum();
        System.out.println("SOMA NUMEROS| "+somaNumeros);


        OptionalDouble mediaNumeros = listaNumeros.stream()
                .mapToDouble(p -> p.doubleValue())
                .average();
        System.out.println("MEDIA NUMEROS| "+mediaNumeros.getAsDouble());


        Set<String> setTeste = new HashSet<>();
        setTeste.add("Teste");
        setTeste.add("Teste2");
        setTeste.add("Teste3");

        Iterator<String> it = setTeste.iterator();
        i = 0;
        String valor= "";
        while (it.hasNext()){
            if(i==0){
                valor = it.next();
            }else{
                it.next();
            }
            i++;
        }
        System.out.println("ValorIterator| "+valor);





    }
}
