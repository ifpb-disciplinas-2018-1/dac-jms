package edu.ifpb.dac.web;

import edu.ifpb.dac.dao.ClienteDao;
import edu.ifpb.dac.dao.ClienteDaoSingleton;
import edu.ifpb.dac.models.Cliente;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CadastroUsuario", urlPatterns = "/cadastroUsuario")
public class CadastroUsuario extends HttpServlet {

    @Inject
    ClienteDaoSingleton clienteDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        if (nome == null || nome.isEmpty() || email == null || email.isEmpty() || !email.contains("@")) {
            response.setStatus(400);
            response.sendRedirect("index.html");
        }

        clienteDao.salvar(new Cliente(nome, email));
        response.setStatus(201);
        response.sendRedirect("produtos.html");
    }
}
