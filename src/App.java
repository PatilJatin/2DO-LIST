    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.image.Image;
    import javafx.stage.Stage;

    public class App extends Application{
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root , 600 , 400);
                primaryStage.setTitle("2DO");   
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                Image icon = new Image("/assets/icon_for_app.png");
                primaryStage.getIcons().add(icon);
                primaryStage.show();
                primaryStage.setOnCloseRequest(event -> {
                    event.consume();
                    LogOut(primaryStage);
                });
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        void LogOut(Stage stage) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("logout");
            alert.setHeaderText("you are about to log out");
            alert.setContentText("do you want to save before exiting");
            if(alert.showAndWait().get() == ButtonType.OK){
                System.out.println("logout pressed");
                stage.close();
            }
        }
}
