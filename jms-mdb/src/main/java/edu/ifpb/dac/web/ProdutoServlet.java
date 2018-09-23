package edu.ifpb.dac.web;

import edu.ifpb.dac.dao.ProdutoDaoSingleton;
import edu.ifpb.dac.models.Produto;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProdutoServlet", urlPatterns = "/produto")
public class ProdutoServlet extends HttpServlet {

    @Inject
    ProdutoDaoSingleton produtoDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        Integer preco = Integer.valueOf(request.getParameter("preco"));

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/pedido");
//        dispatcher.forward(request, response);

        produtoDao.salvar(new Produto(nome, preco));
        response.sendRedirect("pedido.html");
    }
}
