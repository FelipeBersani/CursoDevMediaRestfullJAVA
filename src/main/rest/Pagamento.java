package main.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import joptsimple.internal.Strings;

import java.math.BigDecimal;

public class Pagamento extends Transacao{

    public static Pagamento buildPagamento(String data, String descricao, String moeda, BigDecimal valor, String categoria){
        Pagamento pagamento = new Pagamento();

        return pagamento;
    }

}