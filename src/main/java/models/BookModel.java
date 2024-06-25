package models;

public class BookModel {
    private int id;
    private String author;
    private String genre;

    public BookModel(int id, String name, String author, String genre) {
        this.author = author;
        this.genre = genre;
        this.id = id;
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", id=" + id +
                ", genre=" + genre +
                ", name='" + name + '\'' +
                '}';
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String name;
}
