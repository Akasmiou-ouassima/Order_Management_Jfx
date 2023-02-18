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
import java.util.Date;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {
    @FXML
    private TextField textDate;
    @FXML
    private ComboBox<Client> clientComboBox;
    @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TableColumn<Commande, Integer> colID;
    @FXML
    private TableColumn<Commande, String> colDate;
    @FXML
    private TableColumn<Commande, Client> colCl;

    @FXML
    private TextField textSearch;
    private ObservableList<Commande> commandes = FXCollections.observableArrayList();
    private Service commandeService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       commandeService = new ServiceImpl(new ClientDaoImpl(), new CommandeDaoImpl());
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("DateCommande"));
        colCl.setCellValueFactory(new PropertyValueFactory<>("client"));
        clientComboBox.getItems().addAll(commandeService.getAllClients());
        tableCommande.setItems(commandes);
        commandes.addAll(commandeService.getAllCommandes());
        loadCommandes();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                commandes.clear();
                commandes.addAll(commandeService.getCommandesParDate(newValue));
            }
        });
    }

    @FXML
    private void addCommande() {
        String date= textDate.getText();
        Client client = clientComboBox.getSelectionModel().getSelectedItem();
        if (date.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(" Veuillez saisir un nom !!!");
            alert.show();
        } else {
            Commande p= new Commande();
            p.setDateCommande(date);
            p.setClient(client);
            commandeService.AddCommande(p);
            textDate.clear();
            clientComboBox.getItems().clear();
            loadCommandes();


        }
    }

    @FXML
    private void deleteCommande() {
        MultipleSelectionModel<Commande> mp=tableCommande.getSelectionModel();

        if(mp!=null) {
            commandeService.DeleteCommande(mp.getSelectedItem());
            commandes.remove((mp.getSelectedIndex()));
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(" Veuillez sélectionner un élement!!!");
            alert.show();
        }
    }
    @FXML
   private void updateCommande(){
       Commande commande= this.tableCommande.getSelectionModel().getSelectedItem();
       if (commande == null) {
           Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez selectionner d'abord la ligne à modifier !!");
           alert.show();
       }
       else {
           commande = convertFormToCommande(commande);
           if (commande != null) {
               if (this.commandeService.updateCommande(commande)) {
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Produit modifée avec succée", ButtonType.OK);
                   alert.show();
                   loadCommandes();
                   this.clearForm();
               }
           }
       }

   }

    private Commande convertFormToCommande(Commande commande) {
        String date= textDate.getText();

        Client client = clientComboBox.getSelectionModel().getSelectedItem();

        if (!date.isEmpty() && !(client==null)) {
            commande.setDateCommande(date);
            commande.setClient(client);

            return commande;
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tout le formulaire !!", ButtonType.OK);
            alert.show();
        }

        return null;
    }
    private void clearForm() {
        this.textDate.clear();
        this.clientComboBox.getItems().clear();
    }
    private void loadCommandes() {
        commandes.clear();
        commandes.addAll(commandeService.getAllCommandes());

    }
}
