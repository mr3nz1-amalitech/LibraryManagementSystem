package integration.dao;

import dao.AuthorDAOImpl;
import models.AuthorModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class AuthorDAOImplTest {
    AuthorDAOImpl authorDAO = new AuthorDAOImpl();


    @Test
    public void testInsertAuthor() throws SQLException {
        int result = authorDAO.insert(new AuthorModel(1, "Test Author"));
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testGetAllAuthors() throws SQLException {
        List<AuthorModel> result = authorDAO.getAll();
        assert (!result.isEmpty());
    }

    @Test
    public void testGetAuthorByID() throws SQLException {
        List<AuthorModel> authors = authorDAO.getAll();

        AuthorModel result = authorDAO.get(authors.get(0).getId());
        assert (result != null);
        Assertions.assertEquals(result.getId(), authors.get(0).getId());
    }
}
