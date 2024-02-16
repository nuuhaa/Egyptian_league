package headcouch;


import global_fuction.File_handler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Head_couchController implements Initializable {
    private ObservableList<Head_couch>list;
    Head_couch h = new Head_couch();
    File_handler f = new File_handler();
    @FXML
    private TableView<Head_couch> table;

    @FXML
    private TableColumn<Head_couch, String> name;

    @FXML
    private TableColumn<Head_couch, String> team;

    @FXML
    private TableColumn<Head_couch, String> height;

    @FXML
    private TableColumn<Head_couch, String> weight;

    @FXML
    private TableColumn<Head_couch, Button> ListofMatchs;
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField teamTextfield;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize_columns();
    }
    private void initialize_columns()
    {
        name.setCellValueFactory(new PropertyValueFactory<Head_couch,String>("name"));
        team.setCellValueFactory(new PropertyValueFactory<Head_couch,String>("team"));
        height.setCellValueFactory(new PropertyValueFactory<Head_couch,String>("height"));
        weight.setCellValueFactory(new PropertyValueFactory<Head_couch,String>("weight"));
        ListofMatchs.setCellValueFactory(new PropertyValueFactory<Head_couch,Button>("button"));
        table.setItems(initialize_table());
    }
    private ObservableList<Head_couch> initialize_table()
    {
        try {
            list=h.getData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  list;
    }
    @FXML
    private void sign_out(javafx.event.ActionEvent event ) throws IOException {
        h.transfer(event,"../login/login.fxml" );
    }
    @FXML
    private void home(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../league_details/league_details .fxml" );
    }
    @FXML
    private void teams(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../Team/team.fxml" );
    }
    @FXML
    private void referee(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../Referee/referee.fxml" );
    }
    @FXML
    private void couch(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../headcouch/Head_couch.fxml" );
    }
    @FXML
    private void stadiums(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../stadium/stadium.fxml" );
    }
    @FXML
    private void standings(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../league_details/league_details .fxml" );
    }
    @FXML
    private void homeAdmin(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../league_details/league_detailsAdmin.fxml" );
    }
    @FXML
    private void teamsAdmin(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../Team/team_admin.fxml" );
    }
    @FXML
    private void refereeAdmin(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../Referee/refereeAdmin.fxml" );
    }
    @FXML
    private void couchAdmin(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../headcouch/Head_couchAdmin.fxml" );
    }
    @FXML
    private void stadiumsAdmin(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../stadium/stadiumAdmin.fxml" );
    }
    @FXML
    private void standingsAdmin(javafx.event.ActionEvent event) throws IOException {
        h.transfer(event,"../league_details/league_detailsAdmin.fxml" );
    }

    public void store_data()
    {/*
       f.openFile("C:\\Users\\mgrha\\IdeaProjects\\final - Copy\\src\\headcouch\\head_couch.txt" ,false);
        for(int i=0;i<list.size();i++)
        {
            f.addData(list.get(i).getFull_name() + ";" +list.get(i).getTeam() + ";" +  list.get(i).getHeight() + ";" + list.get(i).getWeight() );
        }
        f.closeFile();
    */
    }
    public void addButtonClicked()
    {/*
        list.add(h.addButtonClicked(nameTextField,teamTextfield,heightTextField,weightTextField));
        store_data();
    */}
    public void deleteButtonClicked( )
    {/*
        ObservableList<Head_couch> selectedItems;
        selectedItems=table.getSelectionModel().getSelectedItems();
        selectedItems.forEach(list::remove);
        store_data();
    */
    }

}
