package edu.ifpb.dac.web.jsf;

import edu.ifpb.dac.models.Produto;
import edu.ifpb.dac.services.ProdutoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ProdutoController implements Serializable {

    @Inject
    private ProdutoService produtoService;

    public List<Produto> listarTodos() {
        return this.produtoService.listarTodos();
    }

}
