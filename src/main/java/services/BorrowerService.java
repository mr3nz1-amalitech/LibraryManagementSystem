package services;

import dao.BorrowerDAOImpl;
import models.BorrowerModel;

import java.sql.SQLException;
import java.util.List;

public class BorrowerService {
    BorrowerDAOImpl borrowerDAO = new BorrowerDAOImpl();

    public List<BorrowerModel> getAll() throws SQLException {
        return borrowerDAO.getAll();
    }

    public int insert(BorrowerModel borrowerModel) throws Exception {
        if (!borrowerModel.isValid()) throw new Exception("Ensure the borrower model is of necessary type");
        return borrowerDAO.insert(borrowerModel);
    }
}
