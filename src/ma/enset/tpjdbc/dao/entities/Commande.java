package ma.enset.tpjdbc.dao.entities;

import java.io.Serializable;
import java.util.Date;

public class Commande implements Serializable {
    private int id;
    private String DateCommande;
    private Client client;

    public Commande() {
    }

    public Commande(String dateCommande, Client client) {
        DateCommande = dateCommande;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateCommande() {
        return DateCommande;
    }

    public void setDateCommande(String dateCommande) {
        DateCommande = dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", DateCommande=" + DateCommande +
                ", client=" + client +
                '}';
    }
}
