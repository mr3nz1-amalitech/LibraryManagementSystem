package dao;

import DB.Database;
import dao.interfaces.DAO;
import models.AuthorModel;
import models.BookModel;
import models.GenreModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorDAOImpl implements DAO<AuthorModel> {
    @Override
    public AuthorModel get(int id) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "SELECT * FROM authors WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        rs.next();

        String name = rs.getString("name");

        return new AuthorModel(id, name);
    }

    @Override
    public List<AuthorModel> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT id, name FROM authors";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<AuthorModel> authorModels = new java.util.ArrayList<>(List.of());

        while (rs.next()) {
            AuthorModel authorModel = new AuthorModel(rs.getInt("id"), rs.getString("name"));
            authorModels.add(authorModel);
        }

        return authorModels;
    }

    @Override
    public int insert(AuthorModel authorModel) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO authors (name) VALUES (?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, authorModel.getName());

        int result = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return result;
    }


    @Override
    public int update(AuthorModel authorModel) throws SQLException {
        return 0;
    }

    @Override
    public int delete(AuthorModel authorModel) throws SQLException {
        return 0;
    }

}
