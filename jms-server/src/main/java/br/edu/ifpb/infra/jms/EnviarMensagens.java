package br.edu.ifpb.infra.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Topic;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/09/2018, 08:04:30
 */
@Stateless
@JMSDestinationDefinition(
        interfaceName = "javax.jms.Topic",
        name = "java:global/jms/aula",
        resourceAdapter = "jmsra",
        destinationName = "exemploAula"
//        resourceAdapter = "activemq-rar-5.12.0"
)
public class EnviarMensagens {

    @Resource(lookup = "java:global/jms/aula")
//    @Resource(lookup = "jms/demoQueue")
//    private Queue queue; // Destination
    private Topic queue; // Destination

    @Inject
    private JMSContext context;

    public void enviarMensagem(String mensagem) {
        JMSProducer producer = this.context.createProducer();
        producer.send(queue, mensagem);
    }
}
