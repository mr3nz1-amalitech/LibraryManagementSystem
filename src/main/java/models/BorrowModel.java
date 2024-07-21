package models;

import java.sql.Date;

public class BorrowModel {
    private int id;
    private String bookName;
    private String borrowerName;
    private String librarianName;
    private Date borrowed_at;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Borrow{" + "id=" + id + ", bookName=" + bookName + ", borrowerName=" + borrowerName + ", librarianName=" + librarianName + ", borrowed_at=" + borrowed_at + '}';
    }

    public BorrowModel(int id, String bookName, String borrowerName, String librarianName, Date borrowed_at) {
        this.id = id;
        this.bookName = bookName;
        this.borrowerName = borrowerName;
        this.librarianName = librarianName;
        this.borrowed_at = borrowed_at;
    }

    public String getBookName() {
        return bookName;
    }

    public Date getBorrowed_at() {
        return borrowed_at;
    }

    public String getBorrowerName() {
        return borrowerName;
    }


    public String getLibrarianName() {
        return librarianName;
    }
}
