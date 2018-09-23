package edu.ifpb.dac.dao;

import edu.ifpb.dac.models.Pedido;
import edu.ifpb.dac.models.Produto;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProdutoDaoSingleton {

    Produto produto;

    @PostConstruct
    public void init() {
        this.produto = null;
    }

    public void salvar(Produto produto) {
        System.out.println("Chegou produto " + produto);
        this.produto = produto;
        System.out.println("Salvou produto " + this.produto);
    }

    public Produto recuperar() {
        System.out.println("Recuperou produto " + this.produto);
        return this.produto;
    }

}
