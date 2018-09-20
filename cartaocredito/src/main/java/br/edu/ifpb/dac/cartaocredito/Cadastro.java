/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cartaocredito;

import javax.json.Json;

/**
 *
 * @author IFPB
 */
public interface Cadastro {
    
    public void cadastrar(Cartao cartao);
    public Cartao Tojson(Json json);
    
}
