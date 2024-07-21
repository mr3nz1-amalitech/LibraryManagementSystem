package services;

import dao.GenreDAOImpl;
import models.GenreModel;

import java.sql.SQLException;
import java.util.List;

public class GenreService {
    GenreDAOImpl genreDAO = new GenreDAOImpl();

    public List<GenreModel> getGenres() throws SQLException {
        return genreDAO.getAll();
    }
}
