package ma.enset.tpjdbc.dao;

import ma.enset.tpjdbc.dao.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao{

    @Override
    public Client findClientByNom(String nom) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        Client client = null;
        try {
            statement = connection.prepareStatement("select * from CLIENTS where NOM = ?");

            statement.setString(1, nom);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                client.setId(rs.getInt("ID"));
                client.setNom(rs.getString("NOM"));
                client.setPrenom(rs.getString("PRENOM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        Connection connection = SingletonConnexionDb.getConnection();
        List<Client> clients = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from CLIENTS");
            ResultSet rs=statement.executeQuery();
            while (rs.next()) {
                Client c = new Client();
                c.setId(rs.getInt("ID"));
                c.setNom(rs.getString("NOM"));
                c.setPrenom(rs.getString("PRENOM"));
               clients.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client findOne(int id) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        Client client = null;
        try {
            statement = connection.prepareStatement("select * from CLIENTS where ID = ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                client.setId(rs.getInt("ID"));
                client.setNom(rs.getString("NOM"));
                client.setPrenom(rs.getString("PRENOM"));
                   }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client save(Client o) {
        Connection connection = SingletonConnexionDb.getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into CLIENTS(NOM,PRENOM)" + "values (?,?)");
            preparedStatement.setString(1, o.getNom());
            preparedStatement.setString(2, o.getPrenom());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(Client o) {
        try {
            Connection connection = SingletonConnexionDb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from CLIENTS where ID =?");
            preparedStatement.setInt(1, o.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            return false;
        }
        return true;
    }

    @Override
    public Client update(Client o) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update CLIENTS set NOM =?,PRENOM=? where ID =?");

            preparedStatement.setString(1,o.getNom());
            preparedStatement.setString(2, o.getPrenom());

            preparedStatement.setInt(3, o.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }
}
