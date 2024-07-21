package integration.dao;

import dao.BookDAOImpl;
import models.AuthorModel;
import models.BookModel;
import models.GenreModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImplTest {
    BookDAOImpl bookDAO = new BookDAOImpl();

    @Test
    void testInsertBook() throws SQLException {
        AuthorModel author = new AuthorModel(5, "Testing Author");
        GenreModel genre = new GenreModel(5, "Testing Genre");

        int result = bookDAO.insert(new BookModel(1, "Testing Book", author.getName(), genre.getName()));

        assert (result >= 0);
    }

    @Test
    void testGetAllBooks() throws SQLException {
        List<BookModel> result = bookDAO.getAll();

        assert (!result.isEmpty());
    }

    @Test
    void testGetBookById() throws SQLException {
        List<BookModel> books = bookDAO.getAll();

        BookModel book = bookDAO.get(books.getFirst().getId());

        assert (book != null);
    }

    @Test
    void getBorrowedBook() throws SQLException {
        List<BookModel> borrowedBooks = bookDAO.getUnBorrowed();

        assert (!borrowedBooks.isEmpty());
    }
}
