package edu.ifpb.dac.dao;

import edu.ifpb.dac.models.Cliente;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Singleton
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteDaoSingleton {

    Cliente cliente;

    @PostConstruct
    public void init() {
        this.cliente = null;
    }

    public void salvar(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente recuperar() {
        return this.cliente;
    }

}
