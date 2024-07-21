package unit.services;

import dao.LibrarianDAOImpl;
import dao.interfaces.LibrarianDAO;
import models.BorrowModel;
import models.LibrarianModel;
import models.ReturnModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import services.LibrarianService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LibrarianServiceTest {
    @Mock
    LibrarianDAOImpl librarianDAO;

    @InjectMocks
    LibrarianService librarianService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    List<BorrowModel> expected = Arrays.asList(
            new BorrowModel(1, "Ghosts in the wires", "John Doe", "Mellisa Vigler", new Date(2024, 1, 3)),
            new BorrowModel(1, "The art of deception", "Tom Peter", "Frank Paul", new Date(2024, 7, 31)));

    @Test
    void testGetBorrows() throws SQLException {
        Mockito.when(librarianDAO.getBorrows()).thenReturn(expected);
        List<BorrowModel> result = librarianService.getBorrows();
        Mockito.verify(librarianDAO).getBorrows();
        Mockito.verify(librarianDAO, Mockito.times(1)).getBorrows();
    }

    @Test
    void testLendBook() throws SQLException {
        Mockito.when(librarianDAO.lendBook(1, 1, 1)).thenReturn(1);
        int result = librarianService.lendBook(1, 1, 1);
        Mockito.verify(librarianDAO).lendBook(1, 1, 1);
        Mockito.verify(librarianDAO, Mockito.times(1)).lendBook(1, 1, 1);
    }

    @Test
    void testGetBorrow() throws SQLException {
        Mockito.when(librarianDAO.getBorrow(1)).thenReturn(expected.getFirst());
        BorrowModel result = librarianService.getBorrow(1);
        Mockito.verify(librarianDAO).getBorrow(1);
        Mockito.verify(librarianDAO, Mockito.times(1)).getBorrow(1);
    }

    @Test
    void testReceiveBook() throws SQLException {
        Mockito.when(librarianDAO.receiveBook(1)).thenReturn(1);
        int result = librarianService.receiveBook(1);
        Mockito.verify(librarianDAO).receiveBook(1);
        Mockito.verify(librarianDAO, Mockito.times(1)).receiveBook(1);
    }

    @Test
    void testGetReturns() throws SQLException {
        Mockito.when(librarianDAO.getReturns()).thenReturn(Arrays.asList(new ReturnModel(1, "Kevin Mitnick", "Ghosts in the wires", new Date(2024, 7, 8), "John Doe", "Autobiography", "Paul Doe", new Date(2024, 8, 9))));
        List<ReturnModel> result = librarianService.getReturns();
        Mockito.verify(librarianDAO).getReturns();
        Mockito.verify(librarianDAO, Mockito.times(1)).getReturns();
    }
}

