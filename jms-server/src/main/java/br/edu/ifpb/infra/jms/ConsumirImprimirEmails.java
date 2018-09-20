package br.edu.ifpb.infra.jms;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/09/2018, 07:49:29
 */
@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/aula"), 
      @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "destinatario='job'")
    }
//        , mappedName = "jms/demoQueue"
)
public class ConsumirImprimirEmails implements MessageListener { //Enviar o email

    private static final Logger LOG = Logger.getLogger(ConsumirImprimirEmails.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            String body = message.getBody(String.class);
            LOG.log(Level.INFO, "/tCorpo {0}", body);
            Enumeration propertyNames = message.getPropertyNames();
            while (propertyNames.hasMoreElements()) {
                Object nextElement = propertyNames.nextElement();
                LOG.log(Level.INFO, "Propriedade {0}", nextElement);
            }

        } catch (JMSException ex) {
            Logger.getLogger(ConsumirImprimirEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
