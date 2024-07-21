package integration.dao;

import dao.GenreDAOImpl;
import models.GenreModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class GenreDaoImplTest {
    GenreDAOImpl genreDAO = new GenreDAOImpl();

    @Test
    public void testInsertGenre() throws SQLException {
        int result = genreDAO.insert(new GenreModel(1, "Drama"));
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<GenreModel> result = genreDAO.getAll();
        assert (!result.isEmpty());
    }

    @Test
    public void getGenreById() throws SQLException {
        List<GenreModel> genres = genreDAO.getAll();

        GenreModel result = genreDAO.get(genres.getFirst().getId());
        assert (result != null);
        Assertions.assertEquals(result.getId(), genres.getFirst().getId());
    }
}
