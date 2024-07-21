package unit.services;

import dao.AuthorDAOImpl;
import dao.BookDAOImpl;
import models.AuthorModel;
import models.BookModel;
import models.BorrowerModel;
import models.GenreModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.listeners.MockitoListener;
import services.BookService;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class BookServiceTest {
    @Mock
    private BookDAOImpl bookDAO;

    @InjectMocks
    BookService bookService;

    List<BookModel> expected = Arrays.asList(
            new BookModel(1, "Ghosts in the wires", "Murenzi Paterne", "Drama"),
            new BookModel(1, "The art of deception", "John Doe", "Drama")
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() throws SQLException {
        AuthorModel authorModel = new AuthorModel(1, "John Doe");
        GenreModel genreModel = new GenreModel(1, "Drama");
        BookModel bookModel = new BookModel(1, "The art of intrusion", authorModel.getName(), genreModel.getName());
        Mockito.when(bookDAO.insert(Mockito.any(BookModel.class), Mockito.any(AuthorModel.class), Mockito.any(GenreModel.class))).thenReturn(1);
        int result = bookService.addBook(bookModel, authorModel, genreModel);
        Assertions.assertEquals(1, result);
        Mockito.verify(bookDAO).insert(bookModel, authorModel, genreModel);
        Mockito.verify(bookDAO, Mockito.times(1)).insert(bookModel, authorModel, genreModel);
    }

    @Test
    void testGetBooks() throws SQLException {
        Mockito.when(bookDAO.getAll()).thenReturn(expected);
        List<BookModel> result = bookService.getBooks();
        Assertions.assertEquals(expected, result);
        Mockito.verify(bookDAO).getAll();
        Mockito.verify(bookDAO, Mockito.times(1)).getAll();
    }

}
