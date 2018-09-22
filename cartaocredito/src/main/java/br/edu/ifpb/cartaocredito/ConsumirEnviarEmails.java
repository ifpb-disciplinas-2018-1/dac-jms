package br.edu.ifpb.cartaocredito;

import br.edu.ifpb.dac.cartaocredito.Cartao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.JsonbBuilder;
        

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/09/2018, 07:49:29
 */
@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/pedido"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/aula"), 
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "typeMansager='cardcredit'")
    }
//        , mappedName = "jms/demoQueue"
)
public class ConsumirEnviarEmails implements MessageListener { //Enviar o email

    private static final Logger LOG = Logger.getLogger(ConsumirEnviarEmails.class.getName());
    private List<Cartao> cartaos =new ArrayList<>();
    

    public ConsumirEnviarEmails() {
        cartaos.add(new Cartao("223", "jose", 100, 232, LocalDate.now()));
    }

    @Override
    public void onMessage(Message message) {
        try {
            LOG.log(Level.INFO, "Message {0}", message);
            String dados = message.getBody(String.class);
           
            Cartao Json = JsonbBuilder.create().fromJson(dados, Cartao.class);
            
            
                    
         
           
          

        } catch (JMSException ex) {
            Logger.getLogger(ConsumirEnviarEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
    }
}
