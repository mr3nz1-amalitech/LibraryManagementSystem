package services;

import dao.AuthorDAOImpl;
import dao.LibrarianDAOImpl;
import models.AuthorModel;

import java.sql.SQLException;
import java.util.List;

public class AuthorService {
    AuthorDAOImpl authorDAO = new AuthorDAOImpl();

    public List<AuthorModel> getAuthors() throws SQLException {
        return authorDAO.getAll();
    }

    public int addAuthor(AuthorModel author) throws SQLException {
        return authorDAO.insert(author);
    }
}
