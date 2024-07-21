package unit.services;

import dao.BorrowerDAOImpl;
import models.BorrowerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import services.BorrowerService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowerServiceTest {
    @Mock
    BorrowerDAOImpl borrowerDAO;

    @InjectMocks
    BorrowerService borrowerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    List<BorrowerModel> expected = Arrays.asList(
            new BorrowerModel(1, "Murenzi Paterne"),
            new BorrowerModel(2, "John Doe")
    );

    @Test
    void testGetAllBorrowers() throws SQLException {
        Mockito.when(borrowerDAO.getAll()).thenReturn(expected);
        List<BorrowerModel> actual = borrowerService.getAll();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void testInsertBorrower() throws Exception {
        BorrowerModel newBorrower = new BorrowerModel(1, "Test Borrower");
        Mockito.when(borrowerDAO.insert(Mockito.any(BorrowerModel.class))).thenReturn(1);
        int result = borrowerService.insert(newBorrower);
        Assertions.assertEquals(1, result);
        Mockito.verify(borrowerDAO, Mockito.times(1)).insert(newBorrower);
    }

    @Test
    void testInsertWithNotAllowedValues() throws Exception {
        BorrowerModel newBorrower = new BorrowerModel(0, "");
        Assertions.assertThrows(Exception.class, () -> borrowerService.insert(newBorrower));
        Mockito.verify(borrowerDAO, Mockito.times(0)).insert(newBorrower);
    }
}
