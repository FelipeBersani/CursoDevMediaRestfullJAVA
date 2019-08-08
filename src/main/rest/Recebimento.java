package main.rest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Recebimento {

    @JsonProperty("data")
    private String data;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("moeda")
    private String moeda;
    @JsonProperty("valor")
    private BigDecimal valor;

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    @JsonProperty("descricao")
    public String getDescricao() {
        return descricao;
    }

    @JsonProperty("descricao")
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonProperty("moeda")
    public String getMoeda() {
        return moeda;
    }

    @JsonProperty("moeda")
    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    @JsonProperty("valor")
    public BigDecimal getValor() {
        return valor;
    }

    @JsonProperty("valor")
    public void setValor(String valor) {
        valor = valor.replace(",", ".");
        valor = valor.replace(" ", "");
        this.valor = new BigDecimal(valor);
    }

    @Override
    public String toString() {
        return "Recebimento{" +
                "data='" + data + '\'' +
                ", descricao='" + descricao + '\'' +
                ", moeda='" + moeda + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}