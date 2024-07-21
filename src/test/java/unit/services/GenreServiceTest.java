package unit.services;

import dao.GenreDAOImpl;
import models.GenreModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import services.GenreService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class GenreServiceTest {
    @Mock
    private GenreDAOImpl genreDAO;

    @InjectMocks
    private GenreService genreService;

    List<GenreModel> expected = Arrays.asList(
            new GenreModel(1, "Drama"),
            new GenreModel(1, "Action")
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllGenres() throws SQLException {
        Mockito.when(genreDAO.getAll()).thenReturn(expected);
        List<GenreModel> result = genreService.getGenres();
        Assertions.assertEquals(expected.size(), result.size());
        Mockito.verify(genreDAO).getAll();
        Mockito.verify(genreDAO, Mockito.times(1)).getAll();
    }

}
