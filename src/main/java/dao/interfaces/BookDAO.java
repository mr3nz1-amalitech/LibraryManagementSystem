package dao.interfaces;

import models.AuthorModel;
import models.BookModel;
import models.GenreModel;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO<B> extends DAO<BookModel> {
    int insert(BookModel bookModel, AuthorModel authorModel, GenreModel genreModel) throws SQLException;

    List<BookModel> getUnBorrowed() throws SQLException;
}
