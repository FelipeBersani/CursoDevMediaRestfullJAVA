import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jersey.repackaged.com.google.common.collect.MapDifference;
import jersey.repackaged.com.google.common.collect.Maps;
import main.Pessoa;
import org.glassfish.jersey.internal.Errors;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Teste2 {

    public static void OrdenaHashMap(String[] args) {

    }

    public void testHashMap() {
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

        Map<String, Object> teste = new HashMap<>();
        Map<String, Object> teste2 = new HashMap<>();
        Map<String, String> testeInside = new HashMap<>();
        testeInside.put("cliente", "1");
        testeInside.put("usuario", null);

        teste.put("atualizadoPor",testeInside);
        teste2.put("atualizadoPor", null);

        System.out.println("Key testeAtualizadoPor: "+teste.containsKey("atualizadoPor"));
        System.out.println("Key teste2AtualizadoPor: "+teste2.containsKey("atualizadoPor"));
        System.out.println(teste2.entrySet().stream().filter(a -> a.getKey() == "atualizadoPor").findFirst());

    }


    public void testJson(){
        //JSON
        List<Pessoa> listaPessoas = Pessoa.populaPessoas();
        Gson gson = new Gson();

        String testeJson = gson.toJson(listaPessoas);

        System.out.println(testeJson);


        String pessoaJson = "{ \"id\": 1, \"nome\": \"Nome1\", \"nacionalidade\": \"BRASILEIRO\", \"idade\": 22}";
        String pessoaListJson = "[{ \"id\": 1, \"nome\": \"Nome1\", \"nacionalidade\": \"BRASILEIRO\", \"idade\": 22}, { \"id\": 2, \"nome\": \"Nome2\", \"nacionalidade\": \"BRASILEIRO2\", \"idade\": 23}]";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Pessoa pessoas = objectMapper.readValue(pessoaJson, Pessoa.class);

            System.out.println(pessoas.getNome());

            Pessoa[] pessoaArray = objectMapper.readValue(testeJson, Pessoa[].class);
            //List<Pessoa> pessoaList = objectMapper.readValue(testeJson, new TypeReference<List<Pessoa>>(){});
            List<Pessoa> pessoaList = objectMapper.readValue(testeJson, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Pessoa.class));
            Map<Long, Pessoa> mapPessoaJson = new HashMap<>();

            for(Pessoa p : pessoaArray){
                System.out.println("ARRAY - Pessoa "+p.getNome()+", nacionalidade "+p.getNacionalidade()+", idade "+p.getIdade()+", id "+p.getId());
            }


            for(Pessoa p : pessoaList){
                System.out.println("LIST - Pessoa "+p.getNome()+", nacionalidade "+p.getNacionalidade()+", idade "+p.getIdade()+", id "+p.getId());
                mapPessoaJson.put(p.getId(), p);
            }

            mapPessoaJson.entrySet()
                    .stream()
                    .filter(p -> p.getValue().getId() == 3)
                    .forEach(p -> System.out.println(p.getValue().getId()+", "+p.getValue().getNome()+", "+p.getValue().getNacionalidade()+", "+p.getValue().getIdade()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testDates(){
        LocalDateTime teste = LocalDateTime.now();
        System.out.println(teste.toLocalDate());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(new Locale("pt", "br"));

        System.out.println("Teste datetimeFormatter: "+teste.format(dateTimeFormatter));
        System.out.println("Teste datetimeFormatter only Date: "+teste.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Teste datetimeFormatter only Time Short: "+teste.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LocalDate teste2 = teste.toLocalDate();

        System.out.println("teste "+teste2);

        long diferenca = ChronoUnit.YEARS.between(LocalDate.of(1994, 06, 01), teste);
        System.out.println("Diferença entre nascimento e hoje: "+diferenca+" anos!");
    }

    public void ExercicioMeia(){
        List<String> listTamanhoMeia = Arrays.asList("41","41", "43", "44", "42", "42", "45", "41", "41", "42", "43", "45", "45", "45", "44", "45", "41", "42");
        System.out.println("Quantidade de itens 41 na lista: "+listTamanhoMeia.stream().filter(s -> s.equals("41")).count());

        Map<String, Integer> mapOrdenador = new HashMap<String, Integer>();

        for(String meiaSeparada :listTamanhoMeia){

            if(mapOrdenador.containsKey(meiaSeparada)){
                Integer qtdMeiaTamanho = mapOrdenador.get(meiaSeparada);
                qtdMeiaTamanho+=1;
                mapOrdenador.put(meiaSeparada, qtdMeiaTamanho);
            }else{
                mapOrdenador.put(meiaSeparada, 1);
            }
        }

        Map<String, Integer> mapFinal = new HashMap<>();
        mapOrdenador.entrySet().stream().forEach(a -> mapFinal.put(a.getKey(), (a.getValue())/2));

        mapFinal.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByKey())
                .forEach(k -> {if(k.getValue()==1){
                    System.out.println("Do tamanho "+k.getKey()+", existe: "+k.getValue()+" par de meia");
                }else {
                    System.out.println("Do tamanho "+k.getKey()+", existem: "+k.getValue()+" pares de meia");
                }});

        //        mapFinal.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey().reversed()).forEach(k -> System.out.println("OrderedDecreasingKEYFINAL "+k.getKey()+", VALUEFINAL "+k.getValue()));
//
//        mapFinal.entrySet().stream().forEach(k -> System.out.println("OrderedIncreasingKEYFINAL "+k.getKey()+", VALUEFINAL "+k.getValue()));

//        mapFinal.entrySet()
//                .stream()
//                .sorted(Map.Entry.<String, Integer>comparingByValue().thenComparing(Map.Entry.<String, Integer>comparingByKey().reversed()))
//                .forEach(k -> System.out.println("Do tamanho "+k.getKey()+", existe(m): "+k.getValue()+" par(es) de meia"));

    }

    public void testMath(){
        int a = 7;
        int b = 3;
        int c = 15;
        int d = 25;
        int e = 35;
        int f = 45;
        int g = 55;
        int h = 65;
        int i = 75;
        double aDouble = 10;
        double bDouble = 2.6;

        List<Integer> listaValores = Arrays.asList(a,b,c,d,e,f,g,h,i);

        System.out.println("Menor valor de uma lista: "+Collections.min(listaValores));
        System.out.println("Maior valor de uma lista: "+Collections.max(listaValores));

        System.out.println("Lista antes do embaralhamento: "+listaValores);
        Collections.shuffle(listaValores);
        System.out.println("Lista depois do embaralhamento: "+listaValores);

        Collections.swap(listaValores, 1, 3);
        System.out.println("Lista depois da troca de valores nas posições 1 e 3: "+listaValores);

        double total;
        total = Math.pow(Double.parseDouble(String.valueOf(a)), Double.parseDouble(String.valueOf(b)));
        System.out.println("Retorno do total de A elevado por B: "+total);

        total = Math.floorDiv(a,b);
        System.out.println("Retorno inteiro de a/b: "+total);

        total = Math.sqrt(d);
        System.out.println("Raiz quadrada de D: "+total);

        total = Math.PI;
        System.out.println("Pí: "+total);

    }

    public static void main(String[] args) {
        Teste2 t2 = new Teste2();
        //t2.testHashMap();
        //t2.testJson();
        //t2.testDates();
        //t2.ExercicioMeia();
        t2.testMath();

    }

}
