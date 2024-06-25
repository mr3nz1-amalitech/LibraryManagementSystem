package controllers;

import dao.AuthorDAOImpl;
import dao.BookDAOImpl;
import dao.GenreDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.AuthorModel;
import models.BookModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.ComboBox;
import models.GenreModel;

public class BookController {

    @FXML
    private TableView<BookModel> allBooksTable;

    @FXML
    private TableColumn<BookModel, Integer> idColumn;

    @FXML
    private TableColumn<BookModel, String> nameColumn;

    @FXML
    private TableColumn<BookModel, String> authorColumn;

    @FXML
    private TableColumn<BookModel, String> genreColumn;

    private ObservableList<BookModel> bookList;

    @FXML
    private TextField bookNameInput;

    @FXML
    private Button add_book_name_button;

    @FXML
    public ComboBox authorsCombobox;

    @FXML
    public ComboBox genresCombobox;


    @FXML
    private void initialize() throws SQLException {
        BookDAOImpl bookDAO = new BookDAOImpl();

        // Bind the columns to the Book properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        // Initialize the book list and set it to the TableView
        bookList = FXCollections.observableArrayList(bookDAO.getAll());
        allBooksTable.setItems(bookList);
        loadAuthorsAndGenres();
    }

    @FXML
    private void loadAuthorsAndGenres() throws SQLException {
        AuthorDAOImpl authorDAO = new AuthorDAOImpl();
        GenreDAOImpl genreDAO = new GenreDAOImpl();

        List<AuthorModel> authorModelList = authorDAO.getAll();
        List<String> authorNames = authorModelList.stream().map(author -> author.getId() + " # " + author.getName()).toList();

        List<GenreModel> genreModelList = genreDAO.getAll();
        List<String> genreNames = genreModelList.stream().map(genre -> genre.getId() + " # " + genre.getName()).toList();

        authorsCombobox.getItems().clear();
        authorsCombobox.getItems().addAll(authorNames);

        genresCombobox.getItems().clear();
        genresCombobox.getItems().addAll(genreNames);
    }

    @FXML
    private void handleAddBookButton(ActionEvent event) throws SQLException {
        String bookName = bookNameInput.getText().trim();

        String[] authorVals = authorsCombobox.getValue().toString().split(" # ");
        String[] genreVals = genresCombobox.getValue().toString().split(" # ");

        AuthorModel authorModel = new AuthorModel(Integer.valueOf(authorVals[0]), authorVals[1]);
        GenreModel genreModel = new GenreModel(Integer.valueOf(authorVals[0]), genreVals[1]);

        BookDAOImpl bookDAO = new BookDAOImpl();

        BookModel bookModel = new BookModel(1, bookName,  authorModel.getName(), genreModel.getName());
        bookDAO.insert(bookModel, authorModel, genreModel);

        bookNameInput.clear();
        initialize();
    }

    @FXML
    public void goToBorrowedBooks(ActionEvent actionEvent) throws IOException {
        navigateToView(actionEvent, "/com/lms/librarymanagementsystem/borrowed-books-view.fxml", "Borrowed Books");
    }

    @FXML
    public void goToReturnedBooks(ActionEvent actionEvent) throws IOException {
        navigateToView(actionEvent, "/com/lms/librarymanagementsystem/returned-books-view.fxml", "Returned Books");
    }

    private void navigateToView(ActionEvent actionEvent, String fxmlPath, String title) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
            // Optionally log the error or show a dialog to the user
        }
    }
}
