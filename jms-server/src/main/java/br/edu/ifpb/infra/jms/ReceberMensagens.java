package br.edu.ifpb.infra.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/09/2018, 08:26:19
 */
@Stateless
public class ReceberMensagens {

//        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/TESTQ"),
//    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//    @ActivationConfigProperty(propertyName = "destination", propertyValue = "TESTQ"),
//    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.12.0")
//    @Resource(lookup = "jms/demoQueue")
    @Resource(lookup = "java:global/jms/aula")
    private Queue queue;

    @Inject
//    @JMSConnectionFactory("jms/myConnectionFactory")
    private JMSContext context;

//    @Resource(lookup = "java:global/jms/demoConnectionFactory")
//    @JMSConnectionFactory("jms/myConnectionFactory")
//    private ConnectionFactory factory;
    public String ler() {
//        LOG.log(Level.INFO, "Factory {0}", factory);
//        this.context = factory.createContext();
//        try {
        LOG.log(Level.INFO, "JMSContext {0}", context);
        JMSConsumer consumer = this.context.createConsumer(queue);
        LOG.log(Level.INFO, "Consumer {0}", consumer);
        String msg = consumer.receiveBody(String.class);

        return msg;//+" - "+ queue.getQueueName();
//        } catch (JMSException ex) {
//            Logger.getLogger(ReceberMensagens.class.getName()).log(Level.SEVERE, null, ex);
//            return ex.getMessage();
//        }
    }
    private static final Logger LOG = Logger.getLogger(ReceberMensagens.class.getName());
}
