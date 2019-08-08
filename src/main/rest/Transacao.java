package main.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transacao {

    @JsonProperty("pagamentos")
    private List<Pagamento> pagamentos = null;

    @JsonProperty("recebimentos")
    private List<Recebimento> recebimentos = null;


    @JsonProperty("pagamentos")
    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    @JsonProperty("pagamentos")
    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    @JsonProperty("recebimentos")
    public List<Recebimento> getRecebimentos() {
        return recebimentos;
    }

    @JsonProperty("recebimentos")
    public void setRecebimentos(List<Recebimento> recebimentos) {
        this.recebimentos = recebimentos;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "pagamentos=" + pagamentos +
                ", recebimentos=" + recebimentos +
                '}';
    }
}