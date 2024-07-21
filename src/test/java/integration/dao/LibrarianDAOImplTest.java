package integration.dao;

import dao.AuthorDAOImpl;
import dao.BookDAOImpl;
import dao.GenreDAOImpl;
import dao.LibrarianDAOImpl;
import dao.interfaces.BookDAO;
import models.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class LibrarianDAOImplTest {
    BookDAOImpl bookDAO = new BookDAOImpl();
    AuthorDAOImpl authorDAO = new AuthorDAOImpl();
    GenreDAOImpl genreDAO = new GenreDAOImpl();
    LibrarianDAOImpl librarianDAO = new LibrarianDAOImpl();

    @Test
    void testLendBook() throws SQLException {
        List<BookModel> book = bookDAO.getAll();
        List<AuthorModel> author = authorDAO.getAll();
        List<GenreModel> genre = genreDAO.getAll();

        int result = librarianDAO.lendBook(book.get(0).getId(), author.get(0).getId(), genre.get(0).getId());

        assert (result == 1);
    }

    @Test
    void getBorrows() throws SQLException {
        List<BorrowModel> borrows = librarianDAO.getBorrows();

        assert (!borrows.isEmpty());
    }

    @Test
    void getBorrow() throws SQLException {
        List<BorrowModel> borrows = librarianDAO.getBorrows();

        BorrowModel result = librarianDAO.getBorrow(borrows.get(0).getId());

        assert (borrows != null);
    }

    @Test
    void testReceiveBook() throws SQLException {
        List<BorrowModel> borrows = librarianDAO.getBorrows();

        int result = librarianDAO.receiveBook(borrows.get(0).getId());
        assert (result == 1);
    }

    @Test
    void testGetReturns() throws SQLException {
        List<ReturnModel> result = librarianDAO.getReturns();

        assert (!result.isEmpty());
    }

    @Test
    void testGetReturnById() throws SQLException {
        List<LibrarianModel> librarians = librarianDAO.getAll();
        LibrarianModel result = librarianDAO.get(librarians.get(0).getId());

        assert (result != null);
    }

    @Test
    void testGetAllLibrarians() throws SQLException {
        List<LibrarianModel> librarians = librarianDAO.getAll();
        assert (!librarians.isEmpty());
    }

    @Test
    void testGetLibrarianById() throws SQLException {
        List<LibrarianModel> librarians = librarianDAO.getAll();

        LibrarianModel result = librarianDAO.get(librarians.get(0).getId());

        assert (result != null);
    }

    @Test
    void insertLibrarian() throws SQLException {
        int result = librarianDAO.insert(new LibrarianModel(1, "Murenzi Paterne"));
        assert (result == 1);
    }
}
