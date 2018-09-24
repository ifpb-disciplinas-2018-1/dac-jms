package edu.ifpb.dac.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Pedido implements Serializable {

    @Id
    private int id;
    private String numeroDoCartao;
    private String codigoParaValidacao;
    private String dataDeValidade;

    public Pedido() {

    }

    public Pedido(String numeroDoCartao, String codigoParaValidacao, String dataDeValidade) {
        this.numeroDoCartao = numeroDoCartao;
        this.codigoParaValidacao = codigoParaValidacao;
        this.dataDeValidade = dataDeValidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getCodigoParaValidacao() {
        return codigoParaValidacao;
    }

    public void setCodigoParaValidacao(String codigoParaValidacao) {
        this.codigoParaValidacao = codigoParaValidacao;
    }

    public String getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        if (id != pedido.id) return false;
        if (numeroDoCartao != null ? !numeroDoCartao.equals(pedido.numeroDoCartao) : pedido.numeroDoCartao != null)
            return false;
        if (codigoParaValidacao != null ? !codigoParaValidacao.equals(pedido.codigoParaValidacao) : pedido.codigoParaValidacao != null)
            return false;
        return dataDeValidade != null ? dataDeValidade.equals(pedido.dataDeValidade) : pedido.dataDeValidade == null;
    }

    @Override
    public int hashCode() {

        int result = id;
        result = 31 * result + (numeroDoCartao != null ? numeroDoCartao.hashCode() : 0);
        result = 31 * result + (codigoParaValidacao != null ? codigoParaValidacao.hashCode() : 0);
        result = 31 * result + (dataDeValidade != null ? dataDeValidade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Pedido{");
        sb.append("id=").append(id);
        sb.append(", numeroDoCartao='").append(numeroDoCartao).append('\'');
        sb.append(", codigoParaValidacao='").append(codigoParaValidacao).append('\'');
        sb.append(", dataDeValidade='").append(dataDeValidade).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
