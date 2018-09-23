package edu.ifpb.dac.dao.implementations;

import edu.ifpb.dac.dao.interfaces.ClienteDaoInterface;
import edu.ifpb.dac.dao.interfaces.LoginInterface;
import edu.ifpb.dac.models.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ClienteDao implements ClienteDaoInterface, LoginInterface {

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

    @Override
    public boolean login(String email) {

        Query query = entityManager.createQuery("SELECT c FROM Cliente WHERE c.email = :email");
        query.setParameter("email", email);

        if (query.getSingleResult() == null) return false;
        else return true;
    }
}
