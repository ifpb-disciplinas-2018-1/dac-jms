package br.edu.ifpb.cartaocredito;

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
import javax.json.JsonObject;
import net.minidev.json.JSONObject;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/09/2018, 08:04:30
 */

//./asadmin create-jms-resource --restype javax.jms.Topic jms/pedido
@Stateless
@JMSDestinationDefinition(
        interfaceName = "javax.jms.Topic",
        name = "java:global/jms/pedido",
        resourceAdapter = "jmsra",
        destinationName = "jms/pedido"
//        resourceAdapter = "activemq-rar-5.12.0"
)
public class EnviarMensagens {

    @Resource(lookup = "java:global/jms/pedido")
//    @Resource(lookup = "jms/demoQueue")
    private Queue queue; // Destination

    @Inject
    private JMSContext context;

    public void enviarMensagem(String mensagem) {
        JMSProducer producer = this.context.createProducer();
       

        
        Message message = context.createTextMessage(mensagem);
        try {
            message.setStringProperty("typeMessage", " cardcredit");
        } catch (JMSException ex) {
            Logger.getLogger(EnviarMensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
