package headcouch;

import Team.Team;
import global_fuction.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import person.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Head_couch extends person {

    private String team;
    private Button button ;
    connection c = new connection();

    public Head_couch() {
    }

    public Head_couch(String full_name, double height, double weight, String team, Button button) {
        super(full_name, height, weight);
        this.team = team;
        this.button = button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Button getButton() {
        return button;
    }


    public void transfer(ActionEvent event , String path) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(parent, 800, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public  void ButtonSetonAction(ActionEvent e ,Button button)
    {

        Team.setMatchListbuttonid(button.getId());


        try {
            transfer(e,"../match_details/match_details.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
    public ObservableList<Head_couch> getData() throws SQLException {     ObservableList<Head_couch> list = FXCollections.observableArrayList();
        ResultSet res = c.getConnection("SELECT * FROM head_couch");
        while (res.next())
        {
            Button button=new Button("show match list");
            button.setPrefWidth(200);

            Head_couch h = new Head_couch(res.getString("name"),res.getDouble("height"),res.getDouble("weight"),res.getString("team"),button);
            button.setId(h.getTeam());
            button.setOnAction(e -> ButtonSetonAction(e,button));
            list.add(h);

        }

        return list;
    }
    public void addButtonClicked(TextField name, TextField team,TextField height, TextField weight)
    {/*
        Head_couch head_couch = new Head_couch();
        head_couch.setFull_name(name.getText());
        head_couch.setTeam(team.getText());
        head_couch.setHeight(height.getText());
        head_couch.setWeight(weight.getText());
        Button button=new Button("show match list");
        button.setPrefWidth(200);
        head_couch.setButton(button);
        button.setId(head_couch.getTeam());
        button.setOnAction(e -> {
            ButtonSetonAction(e,button);
        } );
        name.clear();
        height.clear();
        weight.clear();
        return head_couch;
    */}
}
