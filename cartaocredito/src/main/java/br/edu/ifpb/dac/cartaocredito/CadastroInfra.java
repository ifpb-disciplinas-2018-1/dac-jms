/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cartaocredito;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author IFPB
 */
//@ManagedBean

@Stateless
public class CadastroInfra<T,KEY> implements CadastroIF<T, KEY> {
    
    @PersistenceContext(unitName = "cartaocredito_PU")
    private EntityManager em;
    
    
    @Override
    public void salvar(T entity) {
        //Prepara a entidade para ser persistida na base de dados
        em.persist(entity);
    }

    
    @Override
    public T find(Class<T> tipo, KEY id) {
        //recupera uma entidade apartir de uma chave
        return em.find(tipo, id);
    }

    
    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    
    @Override
    public List<T> list(Class<T> tipo) {
        TypedQuery<T> Query
                // cria u,u objeto do tipo query que permite fazer consultas na base de dados. 
                = em.createQuery("FROM " + tipo.getSimpleName() + " O", tipo);
        return Query.getResultList();
    }

    
    @Override
    public T remove(T entity) {
         Optional<T> of = Optional.of(entity);
        try {
            em.remove(entity);
           
            
        } catch (Exception e) {
        }
        return of.get();
    }
    
}
