package dao;

import DB.Database;
import dao.interfaces.LibrarianDAO;
import models.BorrowModel;
import models.GenreModel;
import models.LibrarianModel;
import models.ReturnModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LibrarianDAOImpl implements LibrarianDAO<LibrarianModel> {
    @Override
    public int lendBook(int bookId, int borrowerId, int librarianId) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO borrows(book_id,  borrower_id, librarian_id) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, bookId);
        ps.setInt(2, borrowerId);
        ps.setInt(3, librarianId);

        int rs = ps.executeUpdate();

        ps.close();
        con.close();

        return rs;
    }

    @Override
    public int receiveBook(int borrowingId) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO returns(borrow_id) VALUES (?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, borrowingId);

        int rs = ps.executeUpdate();

        ps.close();
        con.close();
        return rs;
    }

    @Override
    public List<BorrowModel> getBorrows() throws SQLException {
        Connection con = Database.getConnection();
        String sql = """
                SELECT\s
                    borrows.id AS id,\s
                    borrows.borrowed_at AS borrowed_at,\s
                    books.name AS book_name,\s
                    librarians.name AS librarian_name,\s
                    borrowers.name AS borrower_name\s
                FROM borrows
                INNER JOIN books ON borrows.book_id = books.id
                INNER JOIN librarians ON borrows.librarian_id = librarians.id
                INNER JOIN borrowers ON borrows.borrower_id = borrowers.id
                LEFT JOIN returns ON returns.borrow_id = borrows.id WHERE returns.borrow_id IS NULL;""";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<BorrowModel> borrows = new java.util.ArrayList<>(List.of());

        while (rs.next()) {
            BorrowModel borrow = new BorrowModel(rs.getInt("id"), rs.getString("book_name"), rs.getString("borrower_name"), rs.getString("librarian_name"), rs.getDate("borrowed_at"));
            borrows.add(borrow);
        }

        ps.close();
        rs.close();
        con.close();

        return borrows;
    }

    @Override
    public BorrowModel getBorrow(int id) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT \n" + "    borrows.id AS id, \n" + "    borrows.borrowed_at AS borrowed_at, \n" + "    books.name AS book_name, \n" + "    librarians.name AS librarian_name, \n" + "    borrowers.name AS borrower_name \n" + "FROM borrows\n" + "INNER JOIN books ON borrows.book_id = books.id\n" + "INNER JOIN librarians ON borrows.librarian_id = librarians.id\n" + "INNER JOIN borrowers ON borrows.borrower_id = borrowers.id WHERE borrows.id = ?\n";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        rs.next();
        BorrowModel borrow = new BorrowModel(rs.getInt("id"), rs.getString("book_name"), rs.getString("borrower_name"), rs.getString("librarian_name"), rs.getDate("borrowed_at"));

        ps.close();
        rs.close();
        con.close();

        return borrow;
    }

    @Override
    public List<ReturnModel> getReturns() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT returns.id AS id, borrows.borrowed_at AS borrowed_at, books.name AS book_name, librarians.name AS librarian_name,borrowers.name AS borrower_name, authors.name AS author_name, returns.returned_at AS returned_at, genres.name AS genre_name FROM returns JOIN borrows ON returns.borrow_id = borrows.id INNER JOIN books ON books.id = borrows.book_id INNER JOIN authors ON authors.id = books.author_id INNER JOIN librarians ON librarians.id = borrows.librarian_id INNER JOIN borrowers ON borrowers.id = borrows.borrower_id INNER JOIN genres ON genres.id = books.genre_id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<ReturnModel> returnModels = new java.util.ArrayList<>(List.of());

        while (rs.next()) {
            ReturnModel bookReturnModel = new ReturnModel(rs.getInt("id"), rs.getString("author_name"), rs.getString("book_name"), rs.getDate("borrowed_at"), rs.getString("borrower_name"), rs.getString("genre_name"), rs.getString("librarian_name"), rs.getDate("returned_at"));
            returnModels.add(bookReturnModel);
        }

        return returnModels;
    }

    @Override
    public LibrarianModel get(int id) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM librarians WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        rs.next();

        LibrarianModel librarianModel = new LibrarianModel(rs.getInt("id"), rs.getString("name"));

        ps.close();
        rs.close();
        con.close();

        return librarianModel;
    }

    @Override
    public List<LibrarianModel> getAll() throws SQLException {
        Connection con = Database.getConnection();
        String sql = "SELECT * FROM librarians";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<LibrarianModel> librarians = new java.util.ArrayList<>(List.of());

        while (rs.next()) {
            LibrarianModel librarian = new LibrarianModel(rs.getInt("id"), rs.getString("name"));
            librarians.add(librarian);
        }

        ps.close();
        rs.close();
        con.close();

        return librarians;
    }

    @Override
    public int insert(LibrarianModel librarianModel) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO librarians(name) VALUES(?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, librarianModel.getName());


        int rs = ps.executeUpdate();

        ps.close();
        con.close();

        return rs;
    }
}
