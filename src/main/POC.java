package main;

import com.fasterxml.jackson.databind.*;
import main.rest.*;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class POC {


    public static void main(String[] args) {
        URL urlRequest;
        StringBuilder sb = new StringBuilder();
        try {
//            urlRequest = new URL("https://my-json-server.typicode.com/cairano/backend-test/pagamentos");
//            InputStream reader = urlRequest.openStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));
//            String line = "";
//            while((line = bufferedReader.readLine()) != null){
//                sb.append(line);
//            }
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            List<Pagamento> listaPagamentos = objectMapper.readValue(sb.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Pagamento.class));


            urlRequest = new URL("https://my-json-server.typicode.com/cairano/backend-test/db");
            InputStream reader = urlRequest.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            TransacaoTipo transacaoTipo = objectMapper.readValue(sb.toString(), TransacaoTipo.class);

            System.out.println();

            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream("./file.log")));

            String a = "";
            int i = 0;
            while ((a = bufferedReader1.readLine()) != null) {
                if (i != 0) {
                    String[] b = a.split("\\s{2,}+");
                    NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
                    try {
                        Number number = numberFormat.parse(b[2]);
                        BigDecimal valor = new BigDecimal(number.toString());

                        if(valor.longValue() > 0){
                            Transacao transacao = Transacao.buildPagamento(b[0], b[1], "R$", valor, b.length!= 4 ? "NãoInformada": b[3]);
                            transacaoTipo.getRecebimentos().add(transacao);
                        }else{
                            Transacao transacao = Transacao.buildPagamento(b[0], b[1], "R$", valor, b.length!= 4 ? "NãoInformada": b[3]);

                             transacaoTipo.getPagamentos().add(transacao);
                        }

                    }catch(ParseException e){
                        e.printStackTrace();
                    }
                } else {
                    i++;
                }
            }


//            exibir o log de movimentações de forma ordenada;
            List<Transacao> objectList = new ArrayList<>();
            objectList.addAll(transacaoTipo.getPagamentos());
            objectList.addAll(transacaoTipo.getRecebimentos());

            objectList.stream()
                    .sorted(Comparator.comparing(Transacao::getData).reversed())
                .forEach(object -> System.out.println(object.toString()));

//            informar o total de gastos por categoria;
            Map<String, BigDecimal> mapGastoPorCategoria = new HashMap<>();
            for(Transacao transacao : transacaoTipo.getPagamentos()) {
                System.out.println(transacao.getCategoria());
                if(!mapGastoPorCategoria.containsKey(transacao.getCategoria())){
                    mapGastoPorCategoria.put(transacao.getCategoria(), transacao.getValor());
                }else{
                    BigDecimal valorTotal = mapGastoPorCategoria.get(transacao.getCategoria());
                    mapGastoPorCategoria.put(transacao.getCategoria(), transacao.getValor().add(valorTotal));
                }
            }

            mapGastoPorCategoria.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(abc -> abc.getValue().abs()))
                    .forEach(objeto -> System.out.println("O total de gastos da categoria "+objeto.getKey().toUpperCase()+" foi de R$"+objeto.getValue().abs()));


//            informar qual categoria cliente gastou mais;
            Map.Entry<String, BigDecimal> map = mapGastoPorCategoria.entrySet()
                    .stream()
                    .max(Comparator.comparing(ab -> ab.getValue().abs()))
                    .get();
            System.out.println("A categoria "+map.getKey().toUpperCase()+" teve o maior custo R$"+map.getValue().abs());


            //            informar qual foi o mês que cliente mais gastou;
            Map<String, BigDecimal> mapGastoPorMes = new HashMap<>();
            for(Transacao transacao : transacaoTipo.getPagamentos()) {
                if(!mapGastoPorMes.containsKey(retornaMesComDisplayDoBrasil(transacao.getData()))){
                    mapGastoPorMes.put(retornaMesComDisplayDoBrasil(transacao.getData()), transacao.getValor());
                }else{
                    BigDecimal valorTotal = mapGastoPorMes.get(retornaMesComDisplayDoBrasil(transacao.getData()));
                    mapGastoPorMes.put(retornaMesComDisplayDoBrasil(transacao.getData()), transacao.getValor().add(valorTotal));
                }
            }
            map = mapGastoPorMes.entrySet()
                    .stream()
                    .max(Comparator.comparing(ab -> ab.getValue().abs()))
                    .get();
            System.out.println("O mês "+map.getKey().toUpperCase()+" teve o maior gasto R$"+map.getValue().abs());

//            quanto de dinheiro o cliente gastou;
            BigDecimal totalGasto = BigDecimal.ZERO;
            for(Transacao transacao : transacaoTipo.getPagamentos()) {
                totalGasto = totalGasto.add(transacao.getValor().abs());
            }
            System.out.println("O total de dinheiro gasto foi de R$:"+totalGasto);

//            quanto de dinheiro o cliente recebeu;
            BigDecimal totalRecebido = BigDecimal.ZERO;
            for(Transacao transacao : transacaoTipo.getRecebimentos()) {
                totalRecebido = totalRecebido.add(transacao.getValor().abs());
            }
            System.out.println("O total de dinheiro recebido foi de R$:"+totalRecebido);


//            saldo total de movimentações do cliente.
            BigDecimal totalMovimentacoes = BigDecimal.ZERO;
            int contador = 0;
            for(Transacao transacao : objectList) {
                totalMovimentacoes = totalMovimentacoes.add(transacao.getValor().abs());
                contador++;
            }
            System.out.println("Foram encontradas "+contador+" movimentações, no valor de: R$:"+totalMovimentacoes);


            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String retornaMesComDisplayDoBrasil(LocalDate data){
        return data.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
    }
}
