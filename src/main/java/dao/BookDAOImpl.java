package dao;

import DB.Database;
import dao.interfaces.BookDAO;
import models.AuthorModel;
import models.BookModel;
import models.GenreModel;

import java.sql.*;
import java.util.List;

public class BookDAOImpl implements BookDAO<BookModel> {

    @Override
    public BookModel get(int id) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "SELECT books.id, books.name, authors.name AS author, genres.name AS genre  FROM books INNER JOIN authors ON (books.author_id = authors.id) INNER JOIN genres ON (books.genre_id = genres.id) WHERE books.id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        rs.next();

        BookModel bookModel = new BookModel(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("genre"));

        ps.close();
        con.close();

        return bookModel;
    }

    @Override
    public List<BookModel> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT books.id, books.name, authors.name AS author, genres.name AS genre  FROM books INNER JOIN authors ON (books.author_id = authors.id) INNER JOIN genres ON (books.genre_id = genres.id) ORDER BY books.name ASC";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<BookModel> bookModels = new java.util.ArrayList<>(List.of());

        while (rs.next()) {
            BookModel bookModel = new BookModel(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("genre"));
            bookModels.add(bookModel);
        }

        ps.close();
        rs.close();
        con.close();

        return bookModels;
    }

    @Override
    public int insert(BookModel bookModel) throws SQLException {
        return 0;
    }

    @Override
    public int insert(BookModel bookModel, AuthorModel authorModel, GenreModel genreModel) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int generatedId;

        try {
            con = Database.getConnection();
            String sql = "INSERT INTO books(name, author_id, genre_id) VALUES(?, ?, ?)";

            // Prepare the statement with RETURN_GENERATED_KEYS option
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bookModel.getName());
            ps.setInt(2, authorModel.getId());
            ps.setInt(3, genreModel.getId());

            // Execute the insert statement
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting book failed, no rows affected.");
            }

            // Retrieve the generated keys
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Retrieve the auto-generated ID
            } else {
                throw new SQLException("Inserting book failed, no ID obtained.");
            }

        } finally {
            // Close resources in reverse order of their creation
            ps.close();
            rs.close();
            con.close();
        }

        // Print the generated ID (optional)
        System.out.println("Generated ID: " + generatedId);
        
        return generatedId;
    }

    @Override
    public List<BookModel> getUnBorrowed() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT \n" +
                "    books.id, \n" +
                "    books.name, \n" +
                "    authors.name AS author, \n" +
                "    genres.name AS genre  \n" +
                "FROM \n" +
                "    books \n" +
                "    INNER JOIN authors ON books.author_id = authors.id\n" +
                "    INNER JOIN genres ON books.genre_id = genres.id\n" +
                "    INNER JOIN borrows ON borrows.book_id = books.id\n" +
                "    INNER JOIN returns ON returns.borrow_id = borrows.id\n" +
                "WHERE \n" +
                "    returns.borrow_id IS NOT NULL \n" +
                "ORDER BY \n" +
                "    books.name ASC;\n";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<BookModel> bookModels = new java.util.ArrayList<>(List.of());

        while (rs.next()) {
            BookModel bookModel = new BookModel(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("genre"));
            bookModels.add(bookModel);
        }

        ps.close();
        rs.close();
        con.close();

        return bookModels;
    }

}