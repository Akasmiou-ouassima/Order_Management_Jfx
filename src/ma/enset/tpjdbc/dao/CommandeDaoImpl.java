package ma.enset.tpjdbc.dao;

import ma.enset.tpjdbc.dao.entities.Client;
import ma.enset.tpjdbc.dao.entities.Commande;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDaoImpl implements CommandeDao{
    public List<Commande> findAll(){
        Connection connection = SingletonConnexionDb.getConnection();
        List<Commande> commandes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select CM.ID,CM.DATE_COMMANDE,CM.ID_CLIENT,CL.NOM as 'NOM_CL',CL.PRENOM as 'PRENOM_CL' from COMMANDES CM, CLIENTS CL where CM.ID_CLIENT=CL.ID");
            ResultSet rs=null;

            rs = statement.executeQuery();


            while (rs.next()) {
                Commande c = new Commande();
                c.setId(rs.getInt("ID"));
                c.setDateCommande(rs.getString("DATE_COMMANDE"));
                Client cl=new Client();
                cl.setId(rs.getInt("ID_CLIENT"));
                cl.setNom(rs.getString("NOM_Cl"));
                cl.setPrenom(rs.getString("PRENOM_CL"));
                c.setClient(cl);
               commandes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;

    }

    @Override
    public Commande findOne(int id) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        Commande commande = null;
        try {
            statement = connection.prepareStatement("select * from COMMANDES where ID = ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                commande.setId(rs.getInt("ID"));
               commande.setDateCommande(rs.getString("DATE_COMMANDE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }

    @Override
    public Commande save(Commande o) {
        Connection connection = SingletonConnexionDb.getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into COMMANDES(DATE_COMMANDE,ID_CLIENT)" + "values (?,?)");
            preparedStatement.setString(1, o.getDateCommande());
            preparedStatement.setInt(2,o.getClient().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(Commande o) {
        try {
            Connection connection = SingletonConnexionDb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from COMMANDES where ID =?");
            preparedStatement.setInt(1, o.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            return false;
        }
        return true;
    }

    @Override
    public Commande update(Commande o) {

        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update COMMANDES set DATE_COMMANDE =? where ID =?");

            preparedStatement.setString(1, o.getDateCommande());

            preparedStatement.setInt(2,o.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception in update commande :"+e.getMessage());
        }
        return o;
    }
    public Commande findCommandeByDate(String date) {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = null;
        Commande commande = null;
        try {
            statement = connection.prepareStatement("select * from COMMANDES where DATE_COMMANDE = ?");

            statement.setString(1, date);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                commande.setId(rs.getInt("ID"));
                commande.setDateCommande(rs.getString("DATE_COMMANDE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }
}
