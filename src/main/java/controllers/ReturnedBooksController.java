package controllers;

import dao.BookDAOImpl;
import dao.BorrowerDAOImpl;
import dao.LibrarianDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.BookModel;
import models.BorrowerModel;
import models.ReturnModel;
import services.BookService;
import services.LibrarianService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ReturnedBooksController {
    public ComboBox borrowerNameCombo;
    public ComboBox bookNameCombo;
    @FXML
    private TableView<ReturnModel> allReturnedBooksTable;

    @FXML
    private TableColumn<ReturnModel, Integer> idColumn;

    @FXML
    private TableColumn<ReturnModel, String> nameColumn;

    @FXML
    private TableColumn<ReturnModel, String> authorColumn;

    @FXML
    private TableColumn<ReturnModel, String> genreColumn;

    @FXML
    private TableColumn<ReturnModel, java.sql.Date> borrowerColumn;

    @FXML
    private TableColumn<ReturnModel, java.sql.Date> librarianColumn;

    @FXML
    private TableColumn<ReturnModel, java.sql.Date> borrowedAtColumn;

    @FXML
    private TableColumn<ReturnModel, java.sql.Date> returnedAtColumn;

    private ObservableList<ReturnModel> returnList;

    @FXML
    private TextField book_name_input;

    @FXML
    private Button add_book_name_button;

    @FXML
    private void initialize() throws SQLException {
        try {
            LibrarianService librarianService = new LibrarianService();

            // Bind the columns to the ReturnModel properties
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));
            genreColumn.setCellValueFactory(new PropertyValueFactory<>("genreName"));
            borrowerColumn.setCellValueFactory(new PropertyValueFactory<>("borrowerName"));
            librarianColumn.setCellValueFactory(new PropertyValueFactory<>("librarianName"));
            borrowedAtColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedAt"));
            returnedAtColumn.setCellValueFactory(new PropertyValueFactory<>("returnedAt"));

            List<ReturnModel> returns = librarianService.getReturns();

            // Initialize the borrow list and set it to the TableView
            returnList = FXCollections.observableArrayList(
                    returns // Update this method to return ReturnModel instances
            );
            allReturnedBooksTable.setItems(returnList);
            loadBorrowedBooks();
        } catch (Exception err) {
            throw err;
        }
    }

    @FXML
    private void loadBorrowedBooks() throws SQLException {
        BookService bookService = new BookService();
        List<BookModel> books = bookService.getBooks();
        List<String> bookNames = books.stream().map(book -> book.getId() + " # " + book.getName()).toList();
        bookNameCombo.getItems().clear();
        bookNameCombo.getItems().addAll(bookNames);
    }

    public void loadBorrower(ActionEvent actionEvent) {
//        String bookId = bookNameCombo.getValue().toString().split(" # ")[0];
//
//        BorrowerDAOImpl borrowerDAO = new BorrowerDAOImpl();
//        BorrowerModel borrowerModel = borrowerDAO.get()
    }

    @FXML
    private void handleAddBookButton(ActionEvent event) throws SQLException {

    }

    public void goToBorrowedBooks(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lms/librarymanagementsystem/hello-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            // Set the scene to the new view
            stage.setScene(new Scene(root));
            stage.setTitle("Borrowed Books");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
            // Optionally log the error or show a dialog to the user
        }
    }

    public void goToAllBooks(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lms/librarymanagementsystem/hello-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            // Set the scene to the new view
            stage.setScene(new Scene(root));
            stage.setTitle("Borrowed Books");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
            // Optionally log the error or show a dialog to the user
        }
    }

    public void returnBook(ActionEvent actionEvent) {
    }

}
