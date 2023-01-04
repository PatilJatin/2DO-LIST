package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import Database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Task;

public class AddItemFormController {

    private int userID;
    
    @FXML
    private AnchorPane AddFormScenePane;

    private DatabaseHandler databaseHandler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField description;

    @FXML
    private TextField taskField;

    @FXML
    private Button saveTaskButton;

    @FXML
    private Label successfulLabel;

    @FXML
    private Button todosButton;

    private Stage stage;
    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
        
        saveTaskButton.setOnAction(event -> {
            Task task = new Task();
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = taskField.getText().trim();
            String taskDesciption = description.getText().trim();

            if(!taskText.equals("")|| !taskDesciption.equals("")){
                 task.setUserID(AddItemController.userID);
                task.setDateCreated(timestamp);
                 task.setDescription(taskDesciption);
                 task.setTask(taskText);
            try {
                databaseHandler.insertTask(task);
                successfulLabel.setVisible(true);
                todosButton.setVisible(true);
                int taskNumber = databaseHandler.getAllTasks(AddItemController.userID);
                todosButton.setText("MY 2DO's: " + taskNumber); 
                taskField.setText("");
                description.setText("");
                
                todosButton.setOnAction(event1 ->{
                    // send users to list screen 
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/List.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                });
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }else{
                System.out.println("nothing adding to todo");
            }
            
        });
    }
    @FXML
    void LogOut(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("logout");
        alert.setHeaderText("you are about to log out");
        alert.setContentText("do you want to save before exiting");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage)AddFormScenePane.getScene().getWindow();
            System.out.println("logout pressed");
            stage.close();
        }
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
