/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.cartaocredito;

import br.edu.ifpb.dac.cartaocredito.Cartao;
import br.edu.ifpb.dac.cartaocredito.Service;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@RequestScoped
@Named
public class Controle {
    @EJB
    private Service service;
    private Cartao cartao = new Cartao();
    public String cadastro(){
        service.salvar(cartao);
        cartao = new Cartao();
        return null;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
    public List<Cartao> getTodos(){
      return service.buscarTodos();
    }
}
