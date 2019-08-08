package main;

import com.fasterxml.jackson.databind.*;
import main.rest.*;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

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
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Transacao transacao = objectMapper.readValue(sb.toString(), Transacao.class);

            System.out.println();

            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream("/home/fliguori/teste/file.log")));

            String a = "";
            int i = 0;
            while ((a = bufferedReader1.readLine()) != null) {
                if (i != 0) {
                    String[] b = a.split("\\s{2,}+");
                    NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
                    try {
                        Number number = numberFormat.parse(b[2]);
                        BigDecimal valor = new BigDecimal(number.toString());

                        if(valor.longValue() > 0){
                            Recebimento recebimento = new Recebimento();
                            recebimento.setData(b[0]);
                            recebimento.setDescricao(b[1]);
                            recebimento.setValor(b[2]);
                            recebimento.setMoeda("R$");
                            transacao.getRecebimentos().add(recebimento);
                        }else{
                            Pagamento pagamento = new Pagamento();
                            pagamento.setData(b[0]);
                            pagamento.setDescricao(b[1]);
                            pagamento.setValor(b[2]);
                            pagamento.setCategoria(b.length != 4 ? "" : b[3]);
                            pagamento.setMoeda("R$");
                            transacao.getPagamentos().add(pagamento);
                        }

                    }catch(ParseException e){
                        e.printStackTrace();

                    }
                } else {
                    i++;
                }
            }

            Map<String, Object> map = new HashMap();


            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
