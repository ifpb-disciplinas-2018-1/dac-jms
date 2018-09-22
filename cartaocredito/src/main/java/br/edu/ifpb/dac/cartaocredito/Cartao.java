/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cartaocredito;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author IFPB
 */
@Entity
public class Cartao implements Serializable{
    
    // variáveis
    @Id
    @GeneratedValue
    private String numeroDoCartao;
    private String nomeDoCliente;
    private double valor;
    @Column(length = 3,nullable = false,unique = true)
    private int codigoParaValidacao;
    @Convert
    private LocalDate dataDeValidade;

    // construtor padrao
    public Cartao() {
        LocalDate now = LocalDate.now();
        dataDeValidade = now;
        dataDeValidade.ofYearDay(now.getYear()+3, now.getDayOfMonth());
         Random random = new Random();
         codigoParaValidacao = random.nextInt(999)+1;
    }

    // construtor com todos os atributos
    public Cartao(String numeroDoCartao, String nomeDoCliente, double valor) {
        this();
        this.numeroDoCartao = numeroDoCartao;
        this.nomeDoCliente = nomeDoCliente;
        this.valor = valor;
        
       
    }

    // getters e setters
    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public void setNomeDoCliente(String nomeDoCliente) {
        this.nomeDoCliente = nomeDoCliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCodigoParaValidacao() {
        return codigoParaValidacao;
    }

    public void setCodigoParaValidacao(int codigoParaValidacao) {
        this.codigoParaValidacao = codigoParaValidacao;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.numeroDoCartao);
        hash = 13 * hash + Objects.hashCode(this.nomeDoCliente);
        hash = 13 * hash + this.codigoParaValidacao;
        hash = 13 * hash + Objects.hashCode(this.dataDeValidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cartao other = (Cartao) obj;
        if (this.codigoParaValidacao != other.codigoParaValidacao) {
            return false;
        }
        if (!Objects.equals(this.numeroDoCartao, other.numeroDoCartao)) {
            return false;
        }
        if (!Objects.equals(this.nomeDoCliente, other.nomeDoCliente)) {
            return false;
        }
        if (!Objects.equals(this.dataDeValidade, other.dataDeValidade)) {
            return false;
        }
        return true;
    }
    
    
}
