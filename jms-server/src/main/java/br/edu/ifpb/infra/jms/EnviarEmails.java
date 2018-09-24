package br.edu.ifpb.infra.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;

import javax.jms.Queue;
import javax.jms.Topic; 
import org.json.JSONObject;

import javax.jms.Topic; 

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/09/2018, 08:04:30
 */
@Stateless
@JMSDestinationDefinition(
        interfaceName = "javax.jms.Topic",
        name = "java:global/jms/pedido",
        resourceAdapter = "jmsra",
        destinationName = "EmailExemplo"
)
public class EnviarEmails {

    @Resource(lookup = "java:global/jms/pedido")
//    @Resource(lookup = "jms/demoQueue")
 
    private Topic queue; // Destination

    @Inject
//    @JMSConnectionFactory("jms/__defaultConnectionFactory")
    private JMSContext context;

    public void enviarEmailPara(String mensagem, String destinatario) {
        try {
            JMSProducer producer = this.context.createProducer();
            
            JSONObject jobj = new JSONObject();
            jobj.put("destinatario", "flaviohenrique638@gmail.com");
            jobj.put("titulo", "flaviohenrique638@gmail.com");
            jobj.put("corpo", "flaviohenrique638@gmail.com");
            String men = jobj.toString();
            Message message = context.createTextMessage(men);
            message.setStringProperty("typeMessage", "email");
            
            producer.send(queue, message);
        } catch (JMSException ex) {
            Logger.getLogger(EnviarEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
