package br.edu.ifpb.infra.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/09/2018, 08:04:30
 */
//@Singleton
//@Startup
@Stateless
//@JMSDestinationDefinition(
//        interfaceName = "javax.jms.Queue",
//        name = "java:global/jms/email",
//        resourceAdapter = "jmsra",
//        destinationName = "exemploEmail"
//)
public class EnviarEmails {

    @Resource(lookup = "java:global/jms/aula")
//    @Resource(lookup = "jms/demoQueue")
    private Queue queue; // Destination

    @Inject
//    @JMSConnectionFactory("jms/__defaultConnectionFactory")
    private JMSContext context;

    public void enviarEmailPara(String mensagem, String destinatario) {
        try {
            JMSProducer producer = this.context.createProducer();
            Message message = context.createTextMessage(mensagem);
            message.setStringProperty("destinatario", destinatario);
            producer.send(queue, message);
        } catch (JMSException ex) {
            Logger.getLogger(EnviarEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
