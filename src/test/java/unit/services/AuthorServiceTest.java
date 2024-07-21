package unit.services;

import dao.AuthorDAOImpl;
import models.AuthorModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import services.AuthorService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorDAOImpl authorDAO;

    @InjectMocks
    private AuthorService authorService;

    static Stream<AuthorModel> provideAuthors() {
        return Stream.of(
                new AuthorModel(1, "Author One"),
                new AuthorModel(2, "Author Two"),
                new AuthorModel(3, "Author Three")
        );
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    List<AuthorModel> expected = Arrays.asList(
            new AuthorModel(1, "John Doe"),
            new AuthorModel(2, "Jane Smith")
    );

    @Test
    void testGetAllAuthors() throws SQLException {

        Mockito.when(authorDAO.getAll()).thenReturn(expected);
        List<AuthorModel> result = authorService.getAuthors();
        Assertions.assertEquals(expected.size(), result.size());
        Mockito.verify(authorDAO).getAll();
        Mockito.verify(authorDAO, Mockito.times(1)).getAll();
    }

    void testInsertAuthor(int id, String name) throws SQLException {
        AuthorModel authorModel = new AuthorModel(id, name);
        Mockito.when(authorDAO.insert(Mockito.any(AuthorModel.class))).thenReturn(1);
        authorService.addAuthor(authorModel);
        List<AuthorModel> result = authorService.getAuthors();
        Assertions.assertEquals(1, result.size());
        Mockito.verify(authorDAO, Mockito.times(1)).insert(Mockito.any(AuthorModel.class));
    }

//    @ParameterizedTest
//    @ArgumentsSource(provideAuthors)
//    void testInsertAuthor(AuthorModel authorModel) throws SQLException {
//        Mockito.when(authorDAO.insert(Mockito.any(AuthorModel.class))).thenReturn(1);
//
//        authorService.addAuthor(authorModel);
//
//        List<AuthorModel> result = authorService.getAuthors();
//        Assertions.assertEquals(1, result.size());
//        Mockito.verify(authorDAO, Mockito.times(1)).insert(Mockito.any(AuthorModel.class));
//    }


}
