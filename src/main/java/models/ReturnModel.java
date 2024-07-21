package models;

import java.sql.Date;



public class ReturnModel {
    private int id;
    private String bookName;
    private String authorName;
    private String genreName;
    private String borrowerName;
    private String librarianName;
    private Date borrowedAt;
    private Date returnedAt;

    public ReturnModel(int id, String authorName, String bookName, Date borrowedAt, String borrowerName, String genreName, String librarianName, Date returnedAt) {
        this.authorName = authorName;
        this.bookName = bookName;
        this.borrowedAt = borrowedAt;
        this.borrowerName = borrowerName;
        this.genreName = genreName;
        this.id = id;
        this.librarianName = librarianName;
        this.returnedAt = returnedAt;
    }

    @Override
    public String toString() {
        return "ReturnModel{" +
                "authorName='" + authorName + '\'' +
                ", id=" + id +
                ", bookName='" + bookName + '\'' +
                ", genreName='" + genreName + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", librarianName='" + librarianName + '\'' +
                ", borrowedAt=" + borrowedAt +
                ", returnedAt=" + returnedAt +
                '}';
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public Date getBorrowedAt() {
        return borrowedAt;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getGenreName() {
        return genreName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibrarianName() {
        return librarianName;
    }

    public Date getReturnedAt() {
        return returnedAt;
    }
}
