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
import models.BorrowModel;
import models.BorrowerModel;
import models.LibrarianModel;
import services.BookService;
import services.BorrowerService;
import services.LibrarianService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BorrowedBooksController {

    public ComboBox bookNameCombo;
    public ComboBox borrowerNameCombo;
    public Button lendBookBtn;
    public ComboBox borrowedBooksCombo;
    public Label bookBorrowerName;
    public Button borrowerAddBtn;
    public TextField borrowerNameField;

    @FXML
    private TableView<BorrowModel> allBooksTable;

    @FXML
    private TableColumn<BorrowModel, Integer> idColumn;

    @FXML
    private TableColumn<BorrowModel, String> bookNameColumn;

    @FXML
    private TableColumn<BorrowModel, String> borrowerNameColumn;

    @FXML
    private TableColumn<BorrowModel, String> librarianNameColumn;

    @FXML
    private TableColumn<BorrowModel, java.sql.Date> borrowedAtColumn;

    private ObservableList<BorrowModel> borrowList;

    @FXML
    private TextField book_name_input;

    @FXML
    private Button add_book_name_button;

    @FXML
    private void initialize() throws SQLException {
        LibrarianService librarianService = new LibrarianService();

        // Bind the columns to the BorrowModel properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        borrowerNameColumn.setCellValueFactory(new PropertyValueFactory<>("borrowerName"));
        librarianNameColumn.setCellValueFactory(new PropertyValueFactory<>("librarianName"));
        borrowedAtColumn.setCellValueFactory(new PropertyValueFactory<>("borrowed_at"));

        // Initialize the borrow list and set it to the TableView
        borrowList = FXCollections.observableArrayList(
                librarianService.getBorrows()
        );
        allBooksTable.setItems(borrowList);
        loadBooksAndBorrowers();
    }

    @FXML
    private void loadBooksAndBorrowers() throws SQLException {
        BookService bookService = new BookService();
        BorrowerService borrowerService = new BorrowerService();
        LibrarianService librarianService = new LibrarianService();

        List<BookModel> books = bookService.getBooks();
        List<BorrowerModel> borrowerModels = borrowerService.getAll();
        List<BorrowModel> borrowedBooks = librarianService.getBorrows();

        List<String> bookNames = books.stream().map(book -> book.getId() + " # " + book.getName()).toList();
        List<String> borrowerNames = borrowerModels.stream().map(borrowerModel -> borrowerModel.getId() + " # " + borrowerModel.getName()).toList();
        List<String> borrowedBookNames = borrowedBooks.stream().map(borrowedBook -> borrowedBook.getId() + " # " + borrowedBook.getBookName()).toList();

        bookNameCombo.getItems().clear();
        bookNameCombo.getItems().addAll(bookNames);

        borrowerNameCombo.getItems().clear();
        borrowerNameCombo.getItems().addAll(borrowerNames);

        borrowedBooksCombo.getItems().clear();
        borrowedBooksCombo.getItems().addAll(borrowedBookNames);
    }

    @FXML
    private void getBorrower() throws SQLException {
        int transactionId = Integer.valueOf(borrowedBooksCombo.getValue().toString().split(" # ")[0]);
        LibrarianService librarianService = new LibrarianService();

        BorrowModel borrowModel = librarianService.getBorrow(transactionId);

        bookBorrowerName.setText(borrowModel.getBorrowerName());
    }

    @FXML
    private void handleLendBook(ActionEvent event) throws SQLException {
        String[] bookName = bookNameCombo.getValue().toString().split((" # "));
        String[] borrowerName = borrowerNameCombo.getValue().toString().split(" # ");

        LibrarianService librarianService = new LibrarianService();

        librarianService.lendBook(Integer.valueOf(bookName[0]), Integer.valueOf(borrowerName[0]), 1);

        initialize();
    }

    public void returnBook(ActionEvent actionEvent) throws SQLException {
        int transactionId = Integer.valueOf(borrowedBooksCombo.getValue().toString().split(" # ")[0]);

        LibrarianService librarianService = new LibrarianService();

        librarianService.receiveBook(transactionId);
        initialize();
    }

    public void addBorrower(ActionEvent actionEvent) throws Exception {
        BorrowerService borrowerService = new BorrowerService();
        String borrowerName = borrowerNameField.getText();

        borrowerService.insert(new BorrowerModel(1, borrowerName));

        initialize();
    }

    public void goToAllBooks(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lms/librarymanagementsystem/hello-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            // Set the scene to the new view
            stage.setScene(new Scene(root));
            stage.setTitle("All Books");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
            // Optionally log the error or show a dialog to the user
        }
    }

}
