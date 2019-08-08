package main.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "descricao",
        "moeda",
        "valor",
        "categoria"
})
public class Pagamento {

    @JsonProperty("data")
    private String data;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("moeda")
    private String moeda;
    @JsonProperty("valor")
    private BigDecimal valor;
    @JsonProperty("categoria")
    private String categoria;

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

    @JsonSetter("moeda:")
    public void setMoedaWithDifferentValue(String moeda){
        setMoeda(moeda);
    }

    @JsonProperty("valor")
    public BigDecimal getValor() {
        return valor;
    }

    @JsonProperty("valor")
    public void setValor(String valor) {
        valor = valor.replace(",", ".");
        this.valor = new BigDecimal(valor);
    }

    @JsonProperty("categoria")
    public String getCategoria() {
        return categoria;
    }

    @JsonProperty("categoria")
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}