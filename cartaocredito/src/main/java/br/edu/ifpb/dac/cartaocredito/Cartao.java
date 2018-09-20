/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cartaocredito;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author IFPB
 */
public class Cartao implements Serializable{
    
    // variáveis
    private String numeroDoCartao;
    private String nomeDoCliente;
    private double valor;
    private int codigoParaValidacao;
    private LocalDate dataDeValidade;

    // construtor padrao
    public Cartao() {
    }

    // construtor com todos os atributos
    public Cartao(String numeroDoCartao, String nomeDoCliente, double valor, int codigoParaValidacao, LocalDate dataDeValidade) {
        this.numeroDoCartao = numeroDoCartao;
        this.nomeDoCliente = nomeDoCliente;
        this.valor = valor;
        this.codigoParaValidacao = codigoParaValidacao;
        this.dataDeValidade = dataDeValidade;
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
    
}
