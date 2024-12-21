import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;

public class Scene2Controller implements Initializable{

    @FXML private TextField myTextField;
    @FXML private Label resultLabel;

    private ParenthesesBalancing checker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        checker = new ParenthesesBalancing();

        myTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                
                if(myTextField.getText().isEmpty()) {

                    resultLabel.setText("Please Input");
                    myTextField.setStyle("");
                }

                else if(!myTextField.getText().matches("^[(){}\\[\\]]+$")){

                    resultLabel.setText("Invalid Input");
                    myTextField.setStyle("-fx-border-color: red;");
                }

                else if(checker.isBalance(myTextField.getText())) {
                    
                    resultLabel.setText("Parentheses Balanced");
                    myTextField.setStyle("-fx-border-color: green;");
                }

                else {
                    resultLabel.setText("Parentheses Imbalanced");
                    myTextField.setStyle("-fx-border-color: red;");
                }
            }
            
        });
    }

    public void switchToScene1(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
