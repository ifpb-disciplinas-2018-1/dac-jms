package br.edu.ifpb.emailpedido;

public class Loader {

    public static void main(String[] args) {

        Email email = new EnviarEmail();
        email.enviar(
                "flaviohenrique638@gmail.com",
                "testando javaMail",
                "teste"
        );
    }
}
