package ma.enset.tpjdbc.services;

import ma.enset.tpjdbc.dao.entities.Client;
import ma.enset.tpjdbc.dao.entities.Commande;

import java.util.List;

public interface Service {
    //Commandes
    List<Commande> getAllCommandes();
    Commande getCommande(int id);
    void AddCommande(Commande c);
    void DeleteCommande(Commande c);
    Commande getCommandesParDate(String date);
    public boolean updateCommande(Commande commande);

    //Clients
    List<Client> getAllClients();
    void AddClient(Client c);
    void DeleteClient(Client c);
    Client getClientsParNom(String nom);
}
