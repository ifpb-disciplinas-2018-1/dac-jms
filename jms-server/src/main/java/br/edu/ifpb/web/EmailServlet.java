package br.edu.ifpb.web;

import br.edu.ifpb.infra.jms.EnviarEmails;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ricardo Job
 */
@WebServlet(name = "EmailServlet", urlPatterns = {"/email"})
public class EmailServlet extends HttpServlet {

    @Inject
//    @EJB
    private EnviarEmails produtor;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem = request.getParameter("email");
        String destinatario = request.getParameter("destinatario");
        this.produtor.enviarEmailPara(mensagem, destinatario);
        response.sendRedirect("email.html");
    }

}
