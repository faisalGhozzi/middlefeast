import service.WishlistArticleService;

import java.sql.SQLException;

public class MainWishlist {
    public static void main(String[] args) throws SQLException {
        WishlistArticleService was = new WishlistArticleService();
        was.findAll().forEach(System.out::println);
    }
}
