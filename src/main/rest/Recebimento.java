package main.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Recebimento extends Transacao {

    public static Recebimento buildRecebimento(String data, String descricao, BigDecimal valor, String moeda){
        Recebimento recebimento = new Recebimento();
        recebimento.setData(data);
        recebimento.setDescricao(descricao);
        recebimento.setValor(valor.toString());
        recebimento.setMoeda("R$");

        return recebimento;
    }

}