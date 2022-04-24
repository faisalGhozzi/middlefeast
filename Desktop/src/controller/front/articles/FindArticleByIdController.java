package controller.front.articles;

import controller.back.articles.AddArticleController;
import controller.back.articles.ConfirmDeleteArticleController;
import entity.Article;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class FindArticleByIdController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private ImageView imgDisplay;

    @FXML
    private Label lblViews;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblRecipe;

    @FXML
    private Label lblTitle;

    private Article article;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Image image = new Image("http://127.0.0.1:8000/uploads/"+article.getPicture());
            imgDisplay.setImage(image);
            lblDescription.setText(article.getDescription());
            lblTitle.setText(article.getName());
            lblRecipe.setText(String.valueOf(article.getRecette()));
            lblViews.setText(String.valueOf(article.getVues()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            lblDate.setText(simpleDateFormat.format(article.getDate()));
        });
    }

    @FXML
    void addToFav(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../gui/front/articles/InfoScreenArticle.fxml"));
            root = (Parent)fxmlLoader.load();
            InfoScreenArticleController infoScreenArticleController = fxmlLoader.<InfoScreenArticleController>getController();
            infoScreenArticleController.setMessage("Article added to favorites");
            Stage stage = new Stage();
            stage.setTitle("Info");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
