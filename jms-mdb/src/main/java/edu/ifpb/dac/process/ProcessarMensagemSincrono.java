package edu.ifpb.dac.process;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/09/2018, 08:12:16
 */
@Stateless
public class ProcessarMensagemSincrono {

    public void processar(String mensagem) {
        try {
            Thread.sleep(5000);
            System.out.println("-----Enviando mensagem------");
            System.out.println("mensagem = " + mensagem);
            System.out.println("-----Mensagem enviada------");
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcessarMensagemSincrono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
