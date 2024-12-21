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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene3Controller implements Initializable{

    @FXML private TextField equivalenceField, infixField;
    @FXML private Label letterLabel, postfixLabel, resultLabel, titleLabel;
    @FXML private Button submitButton;

    private InfixToPostfixEval evaluator;
    private boolean allowInput = false;
    private int limit, base = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        evaluator = new InfixToPostfixEval();
        equivalenceField.setDisable(true);
        submitButton.setDisable(true);

        infixField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                
                if(infixField.getText().isEmpty()) {

                    postfixLabel.setText("↑ Please Input Infix ↑");
                    equivalenceField.setDisable(true);
                    submitButton.setDisable(true);

                    limit = 0;
                    base = 1;
                    equivalenceField.clear();
                    resultLabel.setText("↑ Please input letter equivalence ↑");
                    letterLabel.setText("");
                }

                else if(infixField.getText().matches("^[()\\[\\]{}]*$") || infixField.getText().matches("-?\\d+") || 
                        infixField.getText().matches(".*(\\+\\+|--|\\*\\*|//).*") || infixField.getText().matches(".*([A-Z]).*\\1.*") ||
                        infixField.getText().matches("^[\\)\\}\\]\\^]") || infixField.getText().matches(".*=.*")) {

                    postfixLabel.setText("Invalid Input");
                    equivalenceField.setDisable(true);
                    submitButton.setDisable(true);

                    limit = 0;
                    base = 1;
                    equivalenceField.clear();
                    resultLabel.setText("↑ Please input letter equivalence ↑");
                    letterLabel.setText("");
                }

                else {

                    postfixLabel.setText("Postfix: " + evaluator.toPostfix(infixField.getText()));
                    letterLabel.setText(String.valueOf((char)(64 + base)) + ":");

                    equivalenceField.setDisable(false);
                    submitButton.setDisable(false);

                    limit = evaluator.getOperands(infixField.getText()).length();
                    equivalenceField.clear();

                    allowInput = true;
                }
            }
        });
    }

    public void submitInput(ActionEvent e) {

        if(equivalenceField.getText().isEmpty()) {

            return;
        }

        if(allowInput) {

            evaluator.getKeyValue().addValue(Integer.parseInt(equivalenceField.getText()));

            if(base < limit) {

                base++;
                letterLabel.setText(String.valueOf((char)(64 + base)) + ":");
                equivalenceField.clear();
            }

            else {

                submitButton.setDisable(true);
                equivalenceField.setDisable(true);
                resultLabel.setText(String.valueOf(evaluator.evaluate(evaluator.toPostfix(infixField.getText()))));
                allowInput = false;
            }
        }

    }

    public void switchToScene1(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
