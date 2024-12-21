import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene1Controller implements Initializable{

    @FXML private Button balancingButton, postfixButton;
    @FXML private ImageView cross1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(balancingButton);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByY(10);
        translate.setAutoReverse(true);
        translate.play();

        RotateTransition rotate = new RotateTransition();
        rotate.setNode(balancingButton);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setToAngle(-1);
        rotate.setInterpolator(Interpolator.EASE_IN);
        rotate.setAutoReverse(true);
        rotate.play();

        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(postfixButton);
        translate2.setDuration(Duration.millis(1000));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setByY(10);
        translate2.setAutoReverse(true);
        translate2.play();

        RotateTransition rotate2 = new RotateTransition();
        rotate2.setNode(postfixButton);
        rotate2.setDuration(Duration.millis(1000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate2.setToAngle(1);
        rotate2.setInterpolator(Interpolator.EASE_IN);
        rotate2.setAutoReverse(true);
        rotate2.play();
    }

    public void switchToScene2(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene3(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
