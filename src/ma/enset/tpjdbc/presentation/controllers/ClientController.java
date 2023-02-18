package ma.enset.tpjdbc.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.tpjdbc.dao.ClientDaoImpl;
import ma.enset.tpjdbc.dao.CommandeDaoImpl;
import ma.enset.tpjdbc.dao.entities.Client;
import ma.enset.tpjdbc.dao.entities.Commande;
import ma.enset.tpjdbc.services.Service;
import ma.enset.tpjdbc.services.ServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable{
    @FXML
    private TextField textNom;
    @FXML
    private TextField textPrenom;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client,Integer> colId;
    @FXML
    private TableColumn<Client,String> colNom;
    @FXML
    private TextField textSearch;
    @FXML
    private TableColumn<Client,String> colPrenom;
    ObservableList<Client> clients= FXCollections.observableArrayList();
    private Service clientService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientService=new ServiceImpl(new ClientDaoImpl(), new CommandeDaoImpl());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tableClient.setItems(clients);
        clients.addAll(clientService.getAllClients());
        loadClients();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clients.clear();
                clients.addAll(clientService.getClientsParNom(newValue));
            }
        });
    }
    public void addClient(){
        String nom= textNom.getText();
        String prenom= textPrenom.getText();
        Client c=new Client();
        c.setNom(nom);
        c.setPrenom(prenom);
        clientService.AddClient(c);
        textNom.clear();
        textPrenom.clear();
        loadClients();
    }
    public void deleteClient(){
        Client c=tableClient.getSelectionModel().getSelectedItem();
        clientService.DeleteClient(c);
        loadClients();
    }
    private void loadClients(){
        clients.clear();
        clients.addAll(clientService.getAllClients());
    }
}