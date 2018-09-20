package br.edu.ifpb.web;

import br.edu.ifpb.infra.jms.EnviarMensagens;
import br.edu.ifpb.infra.jms.ReceberMensagens;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "MensagemServlet", urlPatterns = {"/mensagem"})
public class MensagemServlet extends HttpServlet {

    @Inject
    private EnviarMensagens produtor;
    @Inject
    private ReceberMensagens consumidor;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mensagens</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Mensagens</h1>");
            out.println(
                    "<p> última mensagem: "
                    + this.consumidor.ler()
                    + "</p>"
            );
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem = request.getParameter("mensagem");
        this.produtor.enviarMensagem(mensagem);
        response.sendRedirect("index.html");
    }

}
