package br.edu.ifpb.emailpedido;

public interface Email {

    void enviar(String destinatario, String titulo, String corpo);
}
