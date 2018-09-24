package edu.ifpb.dac.infra.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "Topic"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/pedido")
        }
)
public class ConsumirNovoPedido implements MessageListener {

    @Inject
    EnviarStatusPedido enviarStatusPedido;

    @Override
    public void onMessage(Message message) {
        try {

            System.out.printf("\n\nCHEGOU EM TEST CLASS: CONSUMIR NOVO PEDIDO");
            System.out.println("JSON: " + message.getBody(String.class));
            System.out.println("typeMessage = " + message.getStringProperty("typeMessage"));
            System.out.printf("\n\n\n");

        } catch (JMSException ex) {
            Logger.getLogger(ConsumirNovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
