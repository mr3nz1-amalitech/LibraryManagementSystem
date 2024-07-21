package services;

import dao.BookDAOImpl;
import models.AuthorModel;
import models.BookModel;
import models.GenreModel;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    BookDAOImpl bookDAO = new BookDAOImpl();

    public List<BookModel> getBooks() throws SQLException {
        return bookDAO.getAll();
    }

    public int addBook(BookModel bookModel, AuthorModel authorModel, GenreModel genreModel) throws SQLException {
        return bookDAO.insert(bookModel, authorModel, genreModel);
    }
}
