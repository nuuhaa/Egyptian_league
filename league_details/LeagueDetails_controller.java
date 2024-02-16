package league_details;

import global_fuction.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class LeagueDetails_controller implements Initializable {
    private connection c = new connection();
    private league_details  l = new league_details();
    private   ObservableList<league_details> list = FXCollections.observableArrayList();

    @FXML
    private TableView<league_details> league_detailsTableView;
    @FXML
    private TableColumn<league_details,String> id;
    @FXML
    private TableColumn<league_details,String> Home;
    @FXML
    private TableColumn<league_details,String> away;
    @FXML
    private TableColumn<league_details, String> TimeTableColumn;
    @FXML
    private TableColumn<league_details, String> dateTableColumn ;
    @FXML
    private TableColumn<league_details,String> referee;
    @FXML
    private TableColumn<league_details,String> stadium;
    @FXML
    private Label error_massage;
    @FXML
    private TextField entered_Home ;
    @FXML
    private TextField entered_Away;
    @FXML
    private TextField entered_time;
    @FXML
    private TextField entered_referee;
    @FXML
    private TextField entered_stadium;
    @FXML
    private DatePicker entered_date;
    @FXML
    private TextField entered_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private void loadData() throws SQLException {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Home.setCellValueFactory(new PropertyValueFactory<>("team1_name"));
        away.setCellValueFactory(new PropertyValueFactory<>("team2_name"));
        TimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("t"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        referee.setCellValueFactory(new PropertyValueFactory<>("referee"));
        stadium.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        league_detailsTableView.setItems(getData());
        if (login.login.isAdmin())
        {
            l.edit(league_detailsTableView,Home,away,TimeTableColumn,dateTableColumn,referee,stadium);
        }

    }


    private ObservableList <league_details> getData() throws SQLException {

         ResultSet res=c.getConnection("SELECT * from match_details;");
        while (res.next())
        {
            list.add(new league_details(res.getInt("match_id"),res.getString("home"),res.getString("away"),res.getString("time"),res.getString("Date"),res.getString("referee_name"),res.getString("stadium_name")));

        }

        return list;
    }
 /*   private boolean checkData()
    {boolean valid =true;
        if(entered_id.getText().isEmpty()|| entered_Home.getText().isEmpty()|| entered_Away.getText().isEmpty()|| entered_time.getText().isEmpty()|| entered_date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).isEmpty()|| entered_referee.getText().isEmpty()|| entered_stadium.getText().isEmpty())
        {
            valid=false;
            error_massage.setText("Please Enter all data");
            entered_id.clear();
            entered_Home.clear();
            entered_Away.clear();
            entered_time.clear();
            entered_referee.clear();
            entered_stadium.clear();
        }
        return  valid;
    }*/
    // check the validation of the data that user entered then add it to database
    @FXML
    private void add_data()
    {boolean valid=true;
        if(entered_id.getText().isEmpty()|| entered_Home.getText().isEmpty()|| entered_Away.getText().isEmpty()|| entered_time.getText().isEmpty()|| entered_date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).isEmpty()|| entered_referee.getText().isEmpty()|| entered_stadium.getText().isEmpty())
        {
            valid=false;
            error_massage.setText("Please Enter all data");
            entered_id.clear();
            entered_Home.clear();
            entered_Away.clear();
            entered_time.clear();
            entered_referee.clear();
            entered_stadium.clear();

        }

if (valid) {
    for (int i = 0; i < list.size(); i++) {
        if (Integer.parseInt(entered_id.getText()) == list.get(i).getId()) {
            valid = false;
            error_massage.setText("This id is already used");
            entered_id.clear();
            break;
        }
    }
}


        if (valid)
        {
        list.add(new league_details(Integer.parseInt(entered_id.getText()), entered_Home.getText(), entered_Away.getText(), entered_time.getText(), entered_date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), entered_referee.getText(), entered_stadium.getText()));
        l.add(entered_id,entered_Home, entered_Away, entered_time, entered_date, entered_referee, entered_stadium);
            entered_id.clear();
            entered_Home.clear();
            entered_Away.clear();
            entered_time.clear();
            entered_referee.clear();
            entered_stadium.clear();
        }
    }

    public void Delete()
    {
        l.Delete(league_detailsTableView);
    }
    //edit the column
     @FXML
    private void editHome(TableColumn.CellEditEvent<league_details, String> editEvent) {
  league_details l = league_detailsTableView.getSelectionModel().getSelectedItem();
  l.setTeam1_name(editEvent.getNewValue());
  c.update_data("Update  match_details set home ='"+editEvent.getNewValue()+"' WHERE match_id="+l.getId()+";");
    }
    //edit the column
    @FXML
    private void editAway(TableColumn.CellEditEvent<league_details, String> editEvent) {
        league_details l = league_detailsTableView.getSelectionModel().getSelectedItem();
        l.setTeam2_name(editEvent.getNewValue());
        c.update_data("Update  match_details set away ='"+editEvent.getNewValue()+"' WHERE match_id="+l.getId()+";");
    }
    //edit the column
    @FXML
    private void editTime(TableColumn.CellEditEvent<league_details, String> editEvent) {
        league_details l = league_detailsTableView.getSelectionModel().getSelectedItem();
        l.setT(editEvent.getNewValue());
        c.update_data("Update  match_details set time ='"+editEvent.getNewValue()+"' WHERE match_id="+l.getId()+";");
    }
    //edit the column
    @FXML
    private void editDate(TableColumn.CellEditEvent<league_details, String> editEvent) {
        league_details l = league_detailsTableView.getSelectionModel().getSelectedItem();
        l.setDate(editEvent.getNewValue());
        c.update_data("Update  match_details set Date ='"+editEvent.getNewValue()+"' WHERE match_id="+l.getId()+";");
    }
    //edit the column
    @FXML
    private void editStadium(TableColumn.CellEditEvent<league_details, String> editEvent) {
        league_details l = league_detailsTableView.getSelectionModel().getSelectedItem();
        l.setStadium(editEvent.getNewValue());
        c.update_data("Update  match_details set stadium_name ='"+editEvent.getNewValue()+"' WHERE match_id="+l.getId()+";");
    }
    //edit the column
    @FXML
    private void editReferee(TableColumn.CellEditEvent<league_details, String> editEvent) {
        league_details l = league_detailsTableView.getSelectionModel().getSelectedItem();
        l.setReferee(editEvent.getNewValue());
        c.update_data("Update  match_details set referee_name ='"+editEvent.getNewValue()+"' WHERE match_id="+l.getId()+";");
    }
    //change the scene
    @FXML
    private void sign_out(javafx.event.ActionEvent event ) throws IOException {
        l.transfer(event,"../login/login.fxml" );
    }
    //change the scene

    @FXML
    private void home(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../league_details/league_details .fxml" );
    }
    //change the scene

    @FXML
    private void teams(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../Team/team.fxml" );
    }
    @FXML
    private void referee(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../Referee/referee.fxml" );
    }
    @FXML
    private void couch(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../headcouch/Head_couch.fxml" );
    }
    @FXML
    private void stadiums(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../stadium/stadium.fxml" );
    }
    @FXML
    private void standings(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../league_details/league_details .fxml" );
    }
    @FXML
    private void homeAdmin(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../league_details/league_detailsAdmin.fxml" );
    }
    @FXML
    private void teamsAdmin(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../Team/team_admin.fxml" );
    }
    @FXML
    private void refereeAdmin(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../Referee/refereeAdmin.fxml" );
    }
    @FXML
    private void couchAdmin(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../headcouch/Head_couchAdmin.fxml" );
    }
    @FXML
    private void stadiumsAdmin(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../stadium/stadiumAdmin.fxml" );
    }
    @FXML
    private void standingsAdmin(javafx.event.ActionEvent event) throws IOException {
        l.transfer(event,"../league_details/league_detailsAdmin.fxml" );
    }

}