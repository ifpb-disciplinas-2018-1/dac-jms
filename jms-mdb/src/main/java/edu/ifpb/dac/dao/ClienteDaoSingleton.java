package edu.ifpb.dac.dao;

import edu.ifpb.dac.models.Cliente;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteDaoSingleton {

    Cliente cliente;

    @PostConstruct
    public void init() {
        this.cliente = null;
    }

    public void salvar(Cliente cliente) {
        System.out.println("Chegou produto " + cliente);
        this.cliente = cliente;
        System.out.println("Salvou produto " + this.cliente);
    }

    public Cliente recuperar() {
        System.out.println("Recuperou produto " + this.cliente);
        return this.cliente;
    }

}
