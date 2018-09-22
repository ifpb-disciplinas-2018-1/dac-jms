package br.edu.ifpb.cartaocredito;

import br.edu.ifpb.dac.cartaocredito.Cartao;
import br.edu.ifpb.dac.cartaocredito.Service;
import br.edu.ifpb.dac.cartaocredito.TipoResposta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
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
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/pedido"), 
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "typeMansager='cardcredit'")
    }
//        , mappedName = "jms/demoQueue"
)
public class ConsumirEnviarEmails implements MessageListener { //Enviar o email

    private static final Logger LOG = Logger.getLogger(ConsumirEnviarEmails.class.getName());
    @EJB
    private Service service;
    @EJB
    private EnviarMensagens enviarMensagens;
    

    public ConsumirEnviarEmails() {
        
    }

    @Override
    public void onMessage(Message message) {
        String resposta;
        try {
            LOG.log(Level.INFO, "Message {0}", message.toString());
            String dados = message.getBody(String.class);
           
            Cartao Json = JsonbBuilder.create().fromJson(dados, Cartao.class);
            Cartao cliente = service.buscar(Json.getNumeroDoCartao());
            if(cliente.equals(Json)&& cliente.getValor()<=Json.getValor()){
                resposta = TipoResposta.APROVADO.name();
            }
            resposta = TipoResposta.REPROVADO.name();
            this.enviarMensagens.enviarMensagem(resposta);
        } catch (JMSException ex) {
            Logger.getLogger(ConsumirEnviarEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
    }
}
