### Atividade

Order - Lucas e Alexa
    Formato: String (JSON)
        - idDoPedido
        - status (APROVADO, REPROVADO)
Email - Flavio, Laerton e Matheus
    Formato: String (JSON)
        - destinatario
        - titulo
        - corpo     
CardCredit - José e Julierme
    Formato: String (JSON)
        - numeroDoCartao
        - nomeDoCliente
        - valor
        - codigoParaValidacao
        - dataDeValidade

Destination
    Formato: Topic
        - jms/pedido // Novos pedidos       
            - typeMessage (email ou cardcredit)
        - jms/aprovado // Pedidos aprovados/reprovados






create-domain dac-exemplo
start-domain dac-exemplo
create-jms-resource --restype javax.jms.ConnectionFactory jms/aulaConnectionFactory
create-jms-resource --restype javax.jms.Queue jms/demoQueue

list-jms-resources
list-jndi-entries --context jms

asadmin create-jms-resource --enabled=true --property=Name=notifierQueue --restype=javax.jms.queue jms/notifierQueue
asadmin notification-jms-configure --dynamic=true --enabled=true --contextFactoryClass=com.sun.enterprise.naming.SerialInitContextFactory --target=server-config --queueName=notifierQueue --url=localhost:7676 --connectionFactoryName=jms/_defaultConnectionFactory
get-jms-notifier-configuration

@MessageDriven(
mappedName = "java:global/jms/topic/aula",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic"),
                @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "email='job'")
        }
)

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/demoQueue") 
//      @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "email='job'")
    }
//        , mappedName = "jms/demoQueue"
)

@JMSDestinationDefinition(
        name = "java:global/jms/topic/aula",
        resourceAdapter = "jmsra",
        interfaceName = "javax.jms.Topic",
        destinationName = "topic",
        description = "Topic em aula"
)