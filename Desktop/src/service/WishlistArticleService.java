package service;

import entity.Article;
import entity.WishlistArticle;
import service_interface.IService;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WishlistArticleService implements IService<WishlistArticle> {

    private Connection con;
    private Statement ste;

    public WishlistArticleService(){
        con = Database.getInstance().getConnection();
    }

    @Override
    public void add(WishlistArticle wishlistArticle) throws SQLException {
        PreparedStatement pre =
                con.prepareStatement(
                        "INSERT INTO  `userfavoris` " +
                                "(`article_id`, `user_id`) " +
                                "VALUES (?, ?)"
                );
        pre.setInt(1, wishlistArticle.getArticle_id());
        pre.setInt(2, wishlistArticle.getUser_id());
        pre.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement pre =
                con.prepareStatement(
                        "DELETE FROM `userfavoris` WHERE id = ?"
                );
        pre.setInt(1, id);
        pre.executeUpdate();
    }

    @Override
    public void update(WishlistArticle wishlistArticle) throws SQLException {
        PreparedStatement pre =
                con.prepareStatement(
                        "UPDATE `userfavoris` SET " +
                                "article_id = ?, " +
                                "user_id = ?, " +
                                "picture = ?, " +
                                "date = ?, " +
                                "recette = ?, " +
                                "vues = ? WHERE id = ?"
                );
        pre.setInt(1, wishlistArticle.getArticle_id());
        pre.setInt(2, wishlistArticle.getUser_id());
        pre.setInt(3, wishlistArticle.getId());
        pre.executeUpdate();
    }

    @Override
    public List<WishlistArticle> findAll() throws SQLException {
        List<WishlistArticle> wishlistArticleList = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM userfavoris");
        while (rs.next()){
            WishlistArticle wishlistArticle = new WishlistArticle(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3)
            );
            wishlistArticleList.add(wishlistArticle);
        }
        return wishlistArticleList;
    }

    @Override
    public WishlistArticle findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<WishlistArticle> searchBy(String column, String query) throws SQLException {
        return null;
    }

    @Override
    public List<WishlistArticle> sortBy(String column, boolean descending) throws SQLException {
        return null;
    }
}
