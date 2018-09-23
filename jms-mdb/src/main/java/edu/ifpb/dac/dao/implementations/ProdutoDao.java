package edu.ifpb.dac.dao.implementations;

import edu.ifpb.dac.dao.interfaces.ProdutoDaoInterface;
import edu.ifpb.dac.models.Produto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoDao implements ProdutoDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProdutoDao() {
        this.entityManager = Persistence.createEntityManagerFactory("dac-mdb").createEntityManager();
    }

    @Override
    public List<Produto> listarTodos() {
        return entityManager.createQuery("FROM Produto p").getResultList();
    }

}
