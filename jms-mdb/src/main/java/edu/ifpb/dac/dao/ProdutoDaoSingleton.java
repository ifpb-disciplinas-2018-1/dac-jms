package edu.ifpb.dac.dao;

import edu.ifpb.dac.models.Pedido;
import edu.ifpb.dac.models.Produto;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Singleton
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProdutoDaoSingleton {

    Produto produto;

    @PostConstruct
    public void init() {
        this.produto = null;
    }

    public void salvar(Produto produto) {
        this.produto = produto;
    }

    public Produto recuperar() {
        return this.produto;
    }

}
