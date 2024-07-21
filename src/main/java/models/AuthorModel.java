package models;

public class AuthorModel {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AuthorModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
