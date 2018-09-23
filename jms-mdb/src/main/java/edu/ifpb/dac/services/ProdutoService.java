package edu.ifpb.dac.services;

import edu.ifpb.dac.dao.interfaces.ProdutoDaoInterface;
import edu.ifpb.dac.models.Produto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProdutoService {

    @Inject
    private ProdutoDaoInterface produtoDao;

    public List<Produto> listarTodos() {
        return this.produtoDao.listarTodos();
    }
}
