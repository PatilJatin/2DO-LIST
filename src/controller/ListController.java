package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import Database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Task;

public class ListController {

    @FXML
    private AnchorPane ScenePane;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Task> ListTask;

    @FXML
    private Label successfulLabel;

    @FXML
    private TextField ListDescription;

    @FXML
    private TextField ListTaskField;

    @FXML
    private Button ListSaveTaskButton;

    @FXML
    private ImageView ListRefresh;

    ObservableList<Task> tasks;
    private DatabaseHandler databaseHandler;
    private ObservableList<Task> refreshedTasks;
    private Stage stage;

    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList();
            databaseHandler = new DatabaseHandler();
            ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userID);
            while(resultSet.next()){
                Task task = new Task();
            task.setTaskID(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDateCreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            tasks.addAll(task);
            }
            
        

        

        ListTask.setItems(tasks);
        ListTask.setCellFactory(CellController -> new CellController());

        ListRefresh.setOnMouseClicked(event -> {
            try {
                refreshList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        ListSaveTaskButton.setOnAction(event -> {
            try {
                addNewTask();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
    }
    public void refreshList() throws SQLException {


        System.out.println("refreshList in ListCont called");

        refreshedTasks = FXCollections.observableArrayList();


        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userID);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskID(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDateCreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            refreshedTasks.addAll(task);

        }


        ListTask.setItems(refreshedTasks);
        ListTask.setCellFactory(CellController -> new CellController());

    }
    public void addNewTask() throws ClassNotFoundException {

        if (!ListTaskField.getText().equals("")
                || !ListDescription.getText().equals("")) {
            Task myNewTask = new Task();



            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());


            myNewTask.setUserID(AddItemController.userID);
            myNewTask.setTask(ListTaskField.getText().trim());
            myNewTask.setDescription(ListDescription.getText().trim());
            myNewTask.setDateCreated(timestamp);

            databaseHandler.insertTask(myNewTask);

            ListTaskField.setText("");
            ListDescription.setText("");

            try {
                initialize();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


}

@FXML
void LogOut(ActionEvent event) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("logout");
    alert.setHeaderText("you are about to log out");
    alert.setContentText("do you want to save before exiting");
    if(alert.showAndWait().get() == ButtonType.OK){
        stage = (Stage)ScenePane.getScene().getWindow();
        System.out.println("logout pressed");
        stage.close();
    }
}
}
