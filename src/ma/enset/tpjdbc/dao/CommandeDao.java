package ma.enset.tpjdbc.dao;

import ma.enset.tpjdbc.dao.entities.Client;
import ma.enset.tpjdbc.dao.entities.Commande;

public interface CommandeDao extends Dao<Commande>{
    Commande findCommandeByDate(String date);
}
