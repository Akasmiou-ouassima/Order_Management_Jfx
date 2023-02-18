package ma.enset.tpjdbc.services;

import ma.enset.tpjdbc.dao.ClientDao;
import ma.enset.tpjdbc.dao.CommandeDao;
import ma.enset.tpjdbc.dao.entities.Client;
import ma.enset.tpjdbc.dao.entities.Commande;

import java.util.List;

public class ServiceImpl implements Service{
    private ClientDao clientDao;
    private CommandeDao commandeDao;

    public ServiceImpl(ClientDao clientDao, CommandeDao commandeDao) {
        this.clientDao = clientDao;
        this.commandeDao = commandeDao;
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeDao.findAll();
    }

    @Override
    public Commande getCommande(int id) {
        return commandeDao.findOne(id);
    }

    @Override
    public void AddCommande(Commande c) {
          commandeDao.save(c);
    }

    @Override
    public void DeleteCommande(Commande c) {
  commandeDao.delete(c);
    }

    @Override
    public Commande getCommandesParDate(String date){
        return commandeDao.findCommandeByDate(date);
    }
    @Override
    public boolean updateCommande(Commande commande) {
        return this.commandeDao.update(commande) != null;
    }
    @Override
    public List<Client> getAllClients() {
        return clientDao.findAll();
    }

    @Override
    public void AddClient(Client c) {
clientDao.save(c);
    }

    @Override
    public void DeleteClient(Client c) {
      clientDao.delete(c);
    }

    @Override
    public Client getClientsParNom(String nom) {
        return clientDao.findClientByNom(nom);
    }
}
