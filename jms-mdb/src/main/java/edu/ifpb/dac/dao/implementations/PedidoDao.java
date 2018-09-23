package edu.ifpb.dac.dao.implementations;

import edu.ifpb.dac.dao.interfaces.PedidoDaoInterface;
import edu.ifpb.dac.models.Pedido;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class PedidoDao implements PedidoDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public PedidoDao() {
        this.entityManager = Persistence.createEntityManagerFactory("dac-mdb").createEntityManager();
    }

    @Override
    public boolean salvar(Pedido pedido) {
        try {

            entityManager.persist(pedido);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
