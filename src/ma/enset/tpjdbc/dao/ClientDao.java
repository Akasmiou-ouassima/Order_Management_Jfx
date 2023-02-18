package ma.enset.tpjdbc.dao;

import ma.enset.tpjdbc.dao.entities.Client;

public interface ClientDao extends Dao<Client>{
    Client findClientByNom(String nom);
}
