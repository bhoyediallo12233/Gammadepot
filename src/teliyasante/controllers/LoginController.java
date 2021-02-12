/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teliyasante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import teliyasante.databasemanagers.SQLiteManager;
import teliyasante.outils.Global;

/**
 *
 * @author bhoyediallo
 */
public class LoginController implements Initializable {
    @FXML
    private Pane content;
   
    @FXML
    private TextField txt_user_name;

    @FXML
    private PasswordField txt_user_passw;

    @FXML
    private JFXButton bt_login;

    @FXML
    private JFXComboBox<String> cb_kind_user;

    @FXML
    private Label lb_version;
    
    Connection conn=null;
    ResultSet result=null;
    Statement statement=null;
    private int ind;
    
    @FXML
    void Saisie(MouseEvent event) {
            ind = cb_kind_user.getSelectionModel().getSelectedIndex();
            if(ind ==-1){
             Alert at= new Alert(Alert.AlertType.INFORMATION, "SVP, Selectionner le type d'utilisateur!!!");
             at.showAndWait();
            }
        
    }
    
    @FXML
     void Activer_les_champs(ActionEvent event) {
        ind = cb_kind_user.getSelectionModel().getSelectedIndex();
        if(ind!=-1){
          txt_user_name.setEditable(true);
          txt_user_passw.setEditable(true);
        }else{
             Alert at= new Alert(Alert.AlertType.INFORMATION, "Informations incorrectes, veillez recommencer");
             at.showAndWait();
        }
    }
     
     
     public boolean isNotEmpty(){
         
        return !(txt_user_name.getText().isEmpty()||txt_user_passw.getText().isEmpty());
     }
     
    @FXML
    void login_user(ActionEvent event) throws Exception {

       if (isNotEmpty()) 
       {
            if(cb_kind_user.getSelectionModel().getSelectedItem().equals("Clinic")) SQLiteManager.dbName = "santeClinic.db";
            else if(cb_kind_user.getSelectionModel().getSelectedItem().equals("Pharmacie")) SQLiteManager.dbName = "santePharmacy.db";
            else if(cb_kind_user.getSelectionModel().getSelectedItem().equals("Laboratoire")) SQLiteManager.dbName = "santeLaboratory.db";
            else SQLiteManager.dbName = "teliyaPartner.db";
             Global glob= new Global();
             SQLiteManager sqlit= new SQLiteManager();
             if(sqlit.dbaseCreated()){
                 System.out.println("loacal");
                 if(sqlit.longinUser(txt_user_name.getText(), txt_user_passw.getText())){
                     bt_login.getScene().getWindow().hide();
                     goToHome();
                 }else {
                   Alert at= new Alert(Alert.AlertType.INFORMATION, "Ces données sont incorrectes!!");
                   at.showAndWait();  
                 }
             }else if (glob.IsConnectionAvailable()){
                 System.out.println("online");
                 if(glob.loginOnLine(txt_user_name.getText(), txt_user_passw.getText())){
                     bt_login.getScene().getWindow().hide();
                     goToHome();
                     sqlit.insertUserdata();
                     sqlit.insertStaffdata();
                 }else{
                    Alert at= new Alert(Alert.AlertType.INFORMATION, "Ces données sont incorrectes!!");
                    at.showAndWait(); 
                 }
                 
             }else{
                 Alert at= new Alert(Alert.AlertType.INFORMATION, "You need a connection!!!");
                 at.showAndWait(); 
             }
           
            
        
       }else{
           Alert at= new Alert(Alert.AlertType.INFORMATION, "Saissisez le nom et le mot de passe de l'utilisateur!!!");
           at.showAndWait(); 
       }
       

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
          
          cb_kind_user.getItems().add("Assureur");
          cb_kind_user.getItems().add("Clinic");
          cb_kind_user.getItems().add("Pharmacie");
          cb_kind_user.getItems().add("Laboratoire");
//          SQLiteManager sl= new SQLiteManager();
//          sl.insertUserdata();
          txt_user_name.setEditable(false);
          txt_user_passw.setEditable(false);
         
         
        
      
        
    }
    
    public void goToHome() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/teliyasante/views/Home.fxml"));
        Scene scene= new Scene(root);
        Stage st = new Stage();
        st.setScene(scene);
        st.show();
        
    }
    
}
