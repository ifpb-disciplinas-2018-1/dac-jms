package edu.ifpb.dac.dao;

import edu.ifpb.dac.models.Cliente;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Stateless
public class ClienteDao {

    public void salvar(final Cliente cliente) {
        Connection conn;

        try {
            conn = Conexao.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        String sql = "INSERT INTO Cliente(Email, Nome) VALUES (?,?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getNome());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
