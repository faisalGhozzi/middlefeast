package controller.front.articles;

import controller.back.articles.AddArticleController;
import controller.back.articles.ConfirmDeleteArticleController;
import controller.front.wishlists.ConfirmDeleteFromWishlistController;
import entity.Article;
import entity.WishlistArticle;
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
import service.WishlistArticleService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class FindArticleByIdController implements Initializable {

    @FXML
    private Button btnFav;

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

    private WishlistArticleService wishlistArticleService = new WishlistArticleService();

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
            if(article.getFavid() != 0){
                btnFav.setText("Remove");
                btnFav.setStyle(
                        "-fx-background-color: #e71e1e;"
                );
            }
        });
    }

    @FXML
    void addToFav(ActionEvent event) throws SQLException, IOException {
        Parent root;
        if(article.getFavid() != 0){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../gui/front/wishlists/ConfirmDeleteFromWishlist.fxml"));
            root = (Parent)fxmlLoader.load();
            ConfirmDeleteFromWishlistController confirmDeleteFromWishlistController = fxmlLoader.<ConfirmDeleteFromWishlistController>getController();
            confirmDeleteFromWishlistController.setArticle(article);
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }else{
            wishlistArticleService.add(new WishlistArticle(1, article.getId()));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../gui/front/articles/InfoScreenArticle.fxml"));
            root = (Parent)fxmlLoader.load();
            InfoScreenArticleController infoScreenArticleController = fxmlLoader.<InfoScreenArticleController>getController();
            infoScreenArticleController.setMessage("Article added to favorites");
            Stage stage = new Stage();
            stage.setTitle("Info");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}