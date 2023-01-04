package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Task;

public class CellController extends ListCell<Task>{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView IconImageView;

    @FXML
    private Label TaskLabel;

    @FXML
    private Label DescriptionLabel;

    @FXML
    private Label DateLabel;

    @FXML
    private ImageView DeleteButton;

    private FXMLLoader fxmlLoader;
    
    private DatabaseHandler databaseHandler;
    @FXML
    void initialize() {
    
    }
    @Override
    public void updateItem(Task myTask, boolean empty) {
        databaseHandler = new DatabaseHandler();
        super.updateItem(myTask, empty);
        if(empty || myTask == null){
            setText(null);
            setGraphic(null);
        }
        else{
            if(fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/Cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            TaskLabel.setText(myTask.getTask());
            DateLabel.setText(myTask.getDateCreated().toString());
            DescriptionLabel.setText(myTask.getDescription());

            DeleteButton.setOnMouseClicked(event -> {
                int taskId = myTask.getTaskID();
                try {
                    databaseHandler.deleteTask(AddItemController.userID,taskId);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                getListView().getItems().remove(getItem());

            });

            setText(null);
            setGraphic(rootAnchorPane);
        }
        
    }
}
