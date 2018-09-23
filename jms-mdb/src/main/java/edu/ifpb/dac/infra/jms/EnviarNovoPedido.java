package edu.ifpb.dac.infra.jms;

import edu.ifpb.dac.dao.ClienteDaoSingleton;
import edu.ifpb.dac.dao.ProdutoDaoSingleton;
import edu.ifpb.dac.models.Cliente;
import edu.ifpb.dac.models.Pedido;
import edu.ifpb.dac.models.Produto;

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
public class EnviarNovoPedido {

    @Resource(lookup = "java:global/jms/pedido")
    private Topic topic;

    @Inject
    private JMSContext context;

    @Inject
    ClienteDaoSingleton clienteDao;

    @Inject
    ProdutoDaoSingleton produtoDao;

    public void sender(Pedido pedido) {
        try {
            JMSProducer producer = this.context.createProducer();

            Cliente cliente = clienteDao.recuperar();
            Produto produto = produtoDao.recuperar();

            producer.send(topic, createMessageEmail(cliente, produto));
            producer.send(topic, createMessageCreditCard(pedido, cliente.getNome(), produto.getPreco()));

        } catch (JMSException ex) {
            ex.printStackTrace();
            Logger.getLogger(EnviarNovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Message createMessageEmail(Cliente cliente, Produto produto) throws JMSException {
        JsonObject jsonEmail = Json
                .createObjectBuilder()
                .add("destinatario", cliente.getEmail())
                .add("titulo", "Seu Pedido está sendo processado")
                .add("corpo", String.format(
                        "Olá %s :)!!! \n Obrigado pelo seu pedido <3<3<3 \n\n " +
                                "Seu pedido para %s no valor de %d foi recebido e está sendo processado.",
                        cliente.getNome(), produto.getDescricao(), produto.getId()
                ))
                .build();

        Message message = context.createTextMessage(jsonEmail.toString());
        message.setStringProperty("typeMessage", "email");

        return message;
    }

    private Message createMessageCreditCard(Pedido pedido, String nomeCliente, int valorPedido) throws JMSException {
        JsonObject jsonCreditCard = Json
                .createObjectBuilder()
                .add("numeroDoCartao", pedido.getNumeroDoCartao())
                .add("codigoParaValidacao", pedido.getCodigoParaValidacao())
                .add("dataDeValidade", pedido.getDataDeValidade())
                .add("nomeDoCliente", nomeCliente)
                .add("valor", valorPedido)
                .build();

        Message message = context.createTextMessage(jsonCreditCard.toString());
        message.setStringProperty("typeMessage", "cardcredit");

        return message;
    }

}
