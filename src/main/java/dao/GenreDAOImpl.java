package dao;

import DB.Database;
import dao.interfaces.DAO;
import models.AuthorModel;
import models.GenreModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenreDAOImpl implements DAO<GenreModel> {
    @Override
    public GenreModel get(int id) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM genres WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        rs.next();

        String name = rs.getString("name");

        ps.close();
        rs.close();
        con.close();

        return new GenreModel(id, name);
    }

    @Override
    public List<GenreModel> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT id, name FROM genres";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<GenreModel> genreModels = new java.util.ArrayList<>(List.of());

        while (rs.next()) {
            GenreModel genreModel = new GenreModel(rs.getInt("id"), rs.getString("name"));
            genreModels.add(genreModel);
        }

        ps.close();
        rs.close();
        con.close();

        return genreModels;

    }

    @Override
    public int insert(GenreModel genreModel) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO genres (name) VALUES (?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, genreModel.getName());

        int rs = ps.executeUpdate();

        ps.close();
        con.close();

        return rs;
    }
}
