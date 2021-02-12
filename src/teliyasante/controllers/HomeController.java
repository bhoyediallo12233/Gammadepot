/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teliyasante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import teliyasante.databasemanagers.SQLiteManager;
import teliyasante.models.Staff;

/**
 *
 * @author bhoyediallo
 */
public class HomeController implements Initializable {
    
       @FXML
    private AnchorPane AnchorPane;

    @FXML
    private AnchorPane Adminform;
        @FXML
    private TableView<Staff> stafftable;

    @FXML
    private TableColumn<Staff,Integer> clid;

    @FXML
    private TableColumn<Staff, String> clfistname;

    @FXML
    private TableColumn<Staff, String> cllastname;

    @FXML
    private TableColumn<Staff, String> clphone;
    
        @FXML
    private JFXTextField txtfname;

    @FXML
    private JFXTextField txtlname;

    @FXML
    private JFXTextField txtphone;

    @FXML
    private JFXTextField txttype;

    @FXML
    private JFXButton btsubmit;

    @FXML
    void onAdminClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
        Adminform.setVisible(true);
        ObservableList <Staff> list = FXCollections.observableArrayList();
        SQLiteManager sql= new SQLiteManager();
        ResultSet re= sql.getStaff();
        //list = (ObservableList<Staff>) re;
        while(re.next()){
         Integer id = Integer.parseInt(re.getString(("id")));
         list.add(new Staff(id, re.getString("offcode"), re.getString("originId"), 
                 0, re.getString("title"), re.getString("firstname"), re.getString("lastname"), 
                 re.getString("type"), re.getString("phone"), re.getString("specialite"), 0,
                 re.getString("partnerName"), 0, re.getString("insuranceName"),
                 re.getString("status"),false));
        }
        clid.setCellValueFactory(new PropertyValueFactory<Staff,Integer>("id"));
        clfistname.setCellValueFactory(new PropertyValueFactory<Staff,String>("firstname"));
        cllastname.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastname"));
        clphone.setCellValueFactory(new PropertyValueFactory<Staff,String>("phone"));
        stafftable.setItems(list);
        System.out.println("hello!!!");
    }
    
    @FXML
    void onSubmit(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(dataComplete()){
            SQLiteManager data= new SQLiteManager();
            String fname=txtfname.getText();
            String lname=txtlname.getText();
            String phone=txtphone.getText();
           
            data.addStaffMember(fname, lname, phone);
            Canceldata();
        }else{
            Alert at= new Alert(Alert.AlertType.INFORMATION, "Informations incomplètes!!");
            at.showAndWait(); 
        }
            

    }
    
    public boolean dataComplete(){
        boolean test=true;
        if(txtfname.getText().isEmpty())
            test=false;
        if(txtlname.getText().isEmpty())
            test=false;
        if(txtphone.getText().isEmpty())
            test=false;
        
        
        return test;
    }
    
    public void Canceldata(){
        
        
        txtfname.setText("");
        txtlname.setText("");
        txtphone.setText("");
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Adminform.setVisible(false);
    }    
    
}
