/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cartaocredito;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jose
 */
@Stateless
public class Service {

    @EJB
    private CadastroIF<Cartao, String> repositorio;

    public void salvar(Cartao cartao) {
        //Prepara a entidade para ser persistida na base de dados
        repositorio.salvar(cartao);
    }

    public Cartao buscar(String numeroCartao) {
        //Prepara a entidade para ser persistida na base de dados
        return repositorio.find(Cartao.class, numeroCartao);
    }

    public List<Cartao> buscarTodos() {
        return repositorio.list(Cartao.class);
    }

}
