package controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class LoginController {

    private int userID;
    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUsername;

    @FXML
    private Button LoginSignUpButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
      
         
        LoginButton.setOnAction(event ->{
            String loginUsername = LoginUsername.getText().trim();
            String loginPassward = LoginPassword.getText().trim();
    
            User user = new User();
            user.setUserName(loginUsername);
            user.setPassword(loginPassward);

            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;
            try {
                while(userRow.next()){
                    counter++;
                    userID = userRow.getInt("userid");
                }
                if(counter == 1){
                    System.out.println("login sucessful");
                    showAddItemScreen();
                }
                else{
                    //login error
                    Shaker usernameShaker = new Shaker(LoginUsername);
                    usernameShaker.shake();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        LoginSignUpButton.setOnAction(event ->{
            //take user to signup scene
            //then we hide login scene and open signup scene
            LoginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Signup.fxml"));
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
        
    }
    private void showAddItemScreen(){
            LoginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AddItem.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            AddItemController addItemController = loader.getController();
            addItemController.setUserID(userID);
        
            stage.showAndWait();
    }

}
