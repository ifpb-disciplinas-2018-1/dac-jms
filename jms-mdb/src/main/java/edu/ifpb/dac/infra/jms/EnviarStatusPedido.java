package edu.ifpb.dac.infra.jms;

import edu.ifpb.dac.dao.ClienteDaoSingleton;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@JMSDestinationDefinition(
        interfaceName = "javax.jms.Topic",
        name = "java:global/jms/pedido",
        resourceAdapter = "jmsra"
)
public class EnviarStatusPedido {

    @Resource(lookup = "java:global/jms/pedido")
    private Topic topic;

    @Inject
    private ClienteDaoSingleton clienteDao;

    @Inject
    private JMSContext context;

    public void sender(JsonObject jsonPedido) {
        try {
            JMSProducer producer = this.context.createProducer();

            System.out.printf("\n\n CHEGOU ENVIAR STATUS PEDIDO");
            System.out.println(jsonPedido.toString());
            producer.send(topic, createMessageEmail(jsonPedido));

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(EnviarStatusPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Message createMessageEmail(JsonObject jsonPedido) throws JMSException {

        JsonObject jsonEmail = Json
                .createObjectBuilder()
//                .add("destinatario", jsonPedido.getString("email"))
                .add("destinatario", clienteDao.recuperar().getEmail())
                .add("titulo", "Seu Pedido foi " + jsonPedido.getString("status"))
                .add("corpo", String.format(
                        "Olá %s :)!!! \n" +
                                "Seu pedido %s foi %s.\n",
                        clienteDao.recuperar().getEmail(),
                        jsonPedido.getString("idDoPedido"),
                        jsonPedido.getString("status"))
                )
                .build();

        Message message = context.createTextMessage(jsonEmail.toString());
        message.setStringProperty("typeMessage", "email");

        return message;
    }

}
