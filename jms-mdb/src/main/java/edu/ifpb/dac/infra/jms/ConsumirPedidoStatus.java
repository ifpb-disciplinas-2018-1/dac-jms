package edu.ifpb.dac.infra.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/aprovado"),
                @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "typeMessage='card'")
        }
)
public class ConsumirPedidoStatus implements MessageListener {

    @Inject
    private EnviarStatusPedido enviarStatusPedido;

    @Override
    public void onMessage(Message message) {
        try {
            String body = message.getBody(String.class);


            JsonObject jsonPedido = Json
                    .createReader(
                            new StringReader(body)
                    )
                    .readObject();

            System.out.printf("\n\nCHEGOU EM CONSUMIR STATUS PEDIDO");
            System.out.println("Corpo : " + jsonPedido.toString());

            enviarStatusPedido.sender(jsonPedido);

        } catch (Exception ex) {
            Logger.getLogger(ConsumirPedidoStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
