package services;

import dao.LibrarianDAOImpl;
import models.BorrowModel;
import models.ReturnModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LibrarianService {
    LibrarianDAOImpl librarianDAO = new LibrarianDAOImpl();

    public List<BorrowModel> getBorrows() throws SQLException {
        return librarianDAO.getBorrows();
    }

    public int lendBook(int bookId, int borrowerId, int librarianId) throws SQLException {
        return librarianDAO.lendBook(bookId, borrowerId, librarianId);
    }

    public BorrowModel getBorrow(int id) throws SQLException {
        return librarianDAO.getBorrow(id);
    }

    public int receiveBook(int borrowingId) throws SQLException {
        return librarianDAO.receiveBook(borrowingId);
    }

    public List<ReturnModel> getReturns() throws SQLException {
        return librarianDAO.getReturns();
    }
}
