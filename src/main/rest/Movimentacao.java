package main.rest;

import com.mysql.cj.core.io.BigDecimalValueFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Movimentacao {

    private String data;
    private String descricao;
    private BigDecimal valor;
    private String categoria;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(String valor) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        try {
            Number number = numberFormat.parse(valor);
            this.valor = new BigDecimal(number.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "data='" + data + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}