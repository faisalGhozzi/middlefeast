package controller.front.tutorials;

import controller.front.articles.InfoScreenArticleController;
import entity.Article;
import entity.Tutorial;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class FindTutorialByIdController implements Initializable {

    @FXML
    private Button btnCart;

    @FXML
    private ImageView imgDisplay;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblTitle;

    private Tutorial tutorial;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Image image = new Image("http://127.0.0.1:8000/uploads/"+tutorial.getImage());
            imgDisplay.setImage(image);
            lblDescription.setText(tutorial.getDescription());
            lblTitle.setText(tutorial.getTitre());
            lblPrice.setText(tutorial.getPrix() > 0 ? String.valueOf(tutorial.getPrix()) + " TND" : "Free");
            lblCategory.setText(tutorial.getCategory());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            lblDate.setText(simpleDateFormat.format(tutorial.getDateTuto()));
            if( !(tutorial.getPrix() > 0)){
                btnCart.setText("Get now!");
            }
        });
    }

    @FXML
    void addToCart(ActionEvent event) {

    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }
}