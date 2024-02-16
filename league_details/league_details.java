package league_details;



import global_fuction.connection;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import match_details.match_details;
import java.time.format.DateTimeFormatter;

public class league_details extends match_details
{
connection c = new connection();

    public league_details() {
    }

    public league_details(int id, String team1_name, String team2_name, String t, String date, String referee, String stadium) {
        super(id, team1_name, team2_name, t, date, stadium, referee);
    }
//add the data that user add to the database
    public void add (TextField id ,TextField home, TextField away, TextField time, DatePicker date, TextField referee, TextField stadium)
    {
        c.update_data("INSERT INTO match_details(match_id,home,away,time,Date,referee_name,stadium_name) " +
                " VALUES ('"+Integer.parseInt(id.getText()) +"','"+home.getText()+"','"+away.getText()+"','"+time.getText()+"','"+date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"','"+referee.getText()+"','"+stadium.getText()+"');");
}
//delete the data that user delete from the database

public void Delete(TableView<league_details>table)

{
    league_details l = table.getSelectionModel().getSelectedItem();
    c.update_data("DELETE FROM match_details WHERE match_id ="+l.getId()+";");
    ObservableList<league_details>items,selected_items;
    items=table.getItems();
    selected_items=table.getSelectionModel().getSelectedItems();
    selected_items.forEach(items::remove);
}
//make the table editable if user login as admin
public void edit(TableView table , TableColumn<league_details, String> home, TableColumn<league_details, String> away, TableColumn<league_details, String> time, TableColumn<league_details, String> date, TableColumn<league_details, String> referee, TableColumn<league_details, String> stadium )
{
    table.setEditable(true);
    home.setCellFactory(TextFieldTableCell.forTableColumn());
    away.setCellFactory(TextFieldTableCell.forTableColumn());
    time.setCellFactory(TextFieldTableCell.forTableColumn());
    date.setCellFactory(TextFieldTableCell.forTableColumn());
    referee.setCellFactory(TextFieldTableCell.forTableColumn());
    stadium.setCellFactory(TextFieldTableCell.forTableColumn());

}

}
