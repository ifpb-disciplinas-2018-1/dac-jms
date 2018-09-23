package edu.ifpb.dac.dao.implementations;

import edu.ifpb.dac.dao.interfaces.ClienteDaoInterface;
import edu.ifpb.dac.models.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class ClienteDao implements ClienteDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public ClienteDao() {
        this.entityManager = Persistence.createEntityManagerFactory("dac-mdb").createEntityManager();
    }

    @Override
    public boolean salvar(Cliente cliente) {

        try {

            entityManager.persist(cliente);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
