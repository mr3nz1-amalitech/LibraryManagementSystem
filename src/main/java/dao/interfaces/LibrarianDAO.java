package dao.interfaces;

import models.BookModel;
import models.BorrowModel;
import models.ReturnModel;

import java.sql.SQLException;
import java.util.List;

public interface LibrarianDAO<T> extends DAO<T> {
    int lendBook(int bookId, int borrowerId, int librarianId) throws SQLException;

    int receiveBook(int borrowingId) throws SQLException;

    List<BorrowModel> getBorrows() throws SQLException;

    BorrowModel getBorrow(int borrowsId) throws  SQLException;

    List<ReturnModel> getReturns() throws SQLException;
}
