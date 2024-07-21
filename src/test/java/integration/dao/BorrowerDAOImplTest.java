package integration.dao;

import dao.BorrowerDAOImpl;
import models.BorrowerModel;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BorrowerDAOImplTest {
    BorrowerDAOImpl borrowerDAO = new dao.BorrowerDAOImpl();

    @Test
    void testInsertBorrower() throws SQLException {
        int result = borrowerDAO.insert(new BorrowerModel(1, "Test Borrower"));

        assert (result == 1);
    }

    @Test
    void testGetAllBorrowers() throws SQLException {
        List<BorrowerModel> result = borrowerDAO.getAll();
        assert (!result.isEmpty());
    }

    @Test
    void testGetBorrowerByID() throws SQLException {
        List<BorrowerModel> borrowers = borrowerDAO.getAll();

        BorrowerModel result = borrowerDAO.get(borrowers.getFirst().getId());

        assert (result != null);
    }
}
