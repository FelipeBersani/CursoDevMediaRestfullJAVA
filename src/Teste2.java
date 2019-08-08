import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jersey.repackaged.com.google.common.collect.MapDifference;
import jersey.repackaged.com.google.common.collect.Maps;
import main.Pessoa;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Timestamp timestamp = Timestamp.valueOf(teste);
        System.out.println("LocalDateTime to TimeStamp: "+timestamp+"\n");
        Long agora = Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))).toEpochMilli();
        String timestampp = agora.toString();
        System.out.println("Timestamp: "+agora+" \n");

        LocalDateTime testeVoltaLocalDateTime = timestamp.toLocalDateTime();
        System.out.println("TimeStamp to LocalDateTime: "+testeVoltaLocalDateTime+"\n");

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



    public void testOptional() {
        String valor = null;
        Optional<String> testeOptional = Optional.ofNullable(valor);
        System.out.println("Test null value using optional");
        System.out.println(testeOptional.isPresent() ? testeOptional.get() : Optional.empty());
        System.out.println("If has value false (orElse): " + Optional.ofNullable(valor).orElse("Error\n"));
        System.out.println("Using OrElseThrow for exceptions"+Optional.ofNullable(valor).orElseThrow(() -> new RuntimeException()));

        valor = "Teste";
        testeOptional = Optional.ofNullable(valor);
        System.out.println("Test string value using optional");
        System.out.println(testeOptional.isPresent() ? testeOptional.get() : Optional.empty());

        testeOptional.ifPresent(a -> System.out.println("Value from attribute value: " + a));
        System.out.println("If has value true: " + Optional.ofNullable(valor).orElse("Error"));


        List<Pessoa> pessoa = Pessoa.populaPessoas();

    }

    public void testEncoder (){
        CharsetEncoder charsetEncoder = Charset.forName("ISO-8859-1").newEncoder();

        String teste = "çãî";

        boolean testEncode = charsetEncoder.canEncode(teste);
        System.out.println("É ASCII: "+testEncode);

        charsetEncoder = Charset.forName("UTF-8").newEncoder();
        testEncode = charsetEncoder.canEncode(teste);
        System.out.println("É UTF-8: "+testEncode);


    }


    public void testCrypt(){
        //Cadastro
        String salt = "ABCD";
        String hashPassword = encrypt("senha", salt);

        //Validacao de senha
        boolean returnValid = validaSenha("senha", salt, hashPassword);
        System.out.println(returnValid);

    }

    public String encrypt(String password, String salt){

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(password.concat(salt).getBytes());
            String hash = String.format("%0128x", new BigInteger(1, digest.digest()));

            return hash;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean validaSenha(String password, String salt, String hashPassword) {
        String passwordCrypted = encrypt(password, salt);
        return hashPassword.equals(passwordCrypted);
    }


    public void testAnnotationAndReflection(){
        Class<Pessoa> classe = Pessoa.class;
        for(Field attribute: classe.getDeclaredFields()){
            System.out.println("AttributeName: "+attribute.getName()+", Attribute type: "+attribute.getType());
        }
        System.out.println("\n");

        for(Method method: classe.getDeclaredMethods()){
            System.out.println("MethodName: "+method.getName()+", Return type:"+method.getReturnType()+", Parameters: "+method.getParameterCount());

            if(method.getParameterCount()>0){
                for(Parameter parameter: method.getParameters()){
                    System.out.println("Parameter type: "+parameter.getParameterizedType().getTypeName());
                }
            }
        }


    }

    public void testOptionalException(){

        Map<Long, String> testMap = new HashMap<>();
        testMap.put(1L, "teste");
        testMap.put(2L, "teste2");
        testMap.put(3L, "teste3");
        testMap.entrySet().stream().filter(key -> key.getKey() > 2).forEach(System.out::println);
        testMap.entrySet().stream().filter(value -> value.getValue().equals("teste")).forEach(System.out::println);


        List<String> listTest = Arrays.asList("Geeks", "For", "Geeks", "A", "Computer", "Portal");
        Map<Integer, String> mapResult = new HashMap<>();
        listTest.stream().forEach(a -> mapResult.put(1 ,a.toString()));

        System.out.println(mapResult.size());

        Stream x =listTest.stream().map(n -> n.concat("aaa"));
        System.out.println(x.findFirst().get());

        List<Integer> integerList = new ArrayList<>();
        integerList.add(10);
        integerList.add(15);
        integerList.add(20);

        List<Integer> returnValue = integerList.stream()
                .filter(a -> a >= 15)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(returnValue);

        int[] test = integerList.stream().filter(a -> a>=15).mapToInt(value -> value).toArray();
        for(int integerValue : test){
            System.out.println(integerValue);
        }
        Arrays.stream(test).forEach(System.out::println);

    }



    public static void main(String[] args) {
        Teste2 t2 = new Teste2();

        //t2.testHashMap();
        //t2.testJson();
        //t2.testDates();
        //t2.ExercicioMeia();
        //t2.testMath();
        //t2.testCrypt();
        //t2.testEncoder();
        //t2.testAnnotationAndReflection();
        //t2.testOptionalException();

//        try{
//        t2.testOptional();}
//        catch (RuntimeException e) {
//            System.out.println("RuntimeError correct!");
//        }
    }

}
