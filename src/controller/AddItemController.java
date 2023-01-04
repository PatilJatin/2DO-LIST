    package controller;

    import java.io.IOException;
    import java.net.URL;
    import java.util.ResourceBundle;

    import javafx.animation.FadeTransition;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.control.Label;
    import javafx.scene.image.ImageView;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.AnchorPane;
    import javafx.util.Duration;

    public class AddItemController {

        public static int userID;
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private ImageView addButton;

        @FXML
        private Label noTaskLabel;
        
        @FXML
        private AnchorPane rootAnchorPane;

        @FXML
        void initialize() {
            addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
                //animation while clicking add task button
                Shaker buttShaker = new Shaker(addButton);
                buttShaker.shake();            
                try {
                    AnchorPane formPane  = FXMLLoader.load(getClass().getResource("/view/AddItemForm.fxml"));
                    
                    AddItemController.userID = getUserId();

                    FadeTransition rootTransition = new FadeTransition(Duration.millis(2000),formPane);
                    rootTransition.setFromValue(0f);
                    rootTransition.setToValue(1f);
                    rootTransition.setCycleCount(1);
                    rootTransition.setAutoReverse(false);
                    rootTransition.play();
                    
                    rootAnchorPane.getChildren().setAll(formPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            
        }

        public void setUserID(int userID) {
            AddItemController.userID = userID;
            System.out.println("User Id is " + userID);
        }
        
        public int getUserId(){
            return AddItemController.userID;    
        }
    }
