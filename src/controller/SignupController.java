package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signupFirstName;

    @FXML
    private TextField signupLastname;

    @FXML
    private TextField signupLocation;

    @FXML
    private TextField signupUsername;

    @FXML
    private CheckBox signupCheckboxMale;

    @FXML
    private CheckBox signCheckboxFemale;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private Button signupButton;

    @FXML
    void initialize() {
        signupButton.setOnAction(event ->{
            createUser();
        });
    }
    private void createUser(){
        String firstname = signupFirstName.getText();
        String lastname = signupLastname.getText();
        String username = signupUsername.getText();
        String password = signupPassword.getText();
        String location = signupLocation.getText();
        String gender = "";
        if(signupCheckboxMale.isSelected()){
            gender = "Male";
        }else{
            gender = "Female";
        }
        User newUser = new User(firstname , lastname , username , password , location ,gender);

        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            databaseHandler.signUpUser(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
