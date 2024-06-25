package dao;

import DB.Database;
import dao.interfaces.BorrowerDAO;
import models.BookModel;
import models.BorrowerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BorrowerDAOImpl implements BorrowerDAO<BorrowerModel> {
    @Override
    public BorrowerModel get(int id) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM borrowers WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        rs.next();

        BorrowerModel borrowerModel = new BorrowerModel(rs.getInt("id"), rs.getString("name"));

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return borrowerModel;
    }

    @Override
    public List<BorrowerModel> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM borrowers ORDER BY id ASC";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<BorrowerModel> borrowers = new java.util.ArrayList<>(List.of());

        while (rs.next()) {
            BorrowerModel bookModel = new BorrowerModel(rs.getInt("id"), rs.getString("name"));
            borrowers.add(bookModel);
        }

        return borrowers;
    }

    @Override
    public int insert(BorrowerModel borrowerModel) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO borrowers(name) VALUES(?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, borrowerModel.getName());


        int rs = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return rs;
    }

    @Override
    public int update(BorrowerModel borrowerModel) throws SQLException {
        return 0;
    }

    @Override
    public int delete(BorrowerModel borrowerModel) throws SQLException {
        return 0;
    }
}