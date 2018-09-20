/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cartaocredito;

/**
 *
 * @author IFPB
 */
//@ManagedBean
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.json.Json;

@Named
@SessionScoped
public class CadastroService implements Cadastro, Serializable{
    
    private Cartao cartao = new Cartao();
    
    @Override
    public void cadastrar(Cartao cartao) {
//        this.cartao;
    }
    
    public Cartao Tojson(Json json){
        return cartao;
    }
    
}
