package edu.ifpb.dac.web;

import edu.ifpb.dac.dao.ClienteDaoSingleton;
import edu.ifpb.dac.infra.jms.EnviarNovoPedido;
import edu.ifpb.dac.models.Cliente;
import edu.ifpb.dac.models.Pedido;
import edu.ifpb.dac.models.Produto;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "PedidoServlet", urlPatterns = "/pedido")
public class PedidoServlet extends HttpServlet {

    @Inject
    private ClienteDaoSingleton clienteDao;

    @Inject
    private EnviarNovoPedido produtor;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String numeroDoCartao = request.getParameter("numeroDoCartao");
        String codigoParaValidacao = request.getParameter("codigoParaValidacao");
        String dataDeValidade = request.getParameter("dataDeValidade");

        this.produtor.sender(new Pedido(numeroDoCartao, codigoParaValidacao, dataDeValidade));

        response.sendRedirect("index.html");
    }

}
