package edu.ifpb.dac.infra.jms;

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
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/aula") 
//      @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "email='job'")
    }
//        , mappedName = "jms/demoQueue"
)
public class ConsumirEnviarEmails implements MessageListener { //Enviar o email

    private static final Logger LOG = Logger.getLogger(ConsumirEnviarEmails.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            LOG.log(Level.INFO, "Message {0}", message);
            String body = message.getBody(String.class);
            LOG.log(Level.INFO, "Body {0}", body);
            Enumeration propertyNames = message.getPropertyNames();
            while (propertyNames.hasMoreElements()) {
                Object nextElement = propertyNames.nextElement();
                LOG.log(Level.INFO, "Property {0}", nextElement);
            }

        } catch (JMSException ex) {
            Logger.getLogger(ConsumirEnviarEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
