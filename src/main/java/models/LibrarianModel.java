package models;

public class LibrarianModel {
    private int id;
    private String name;

    public LibrarianModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Librarian{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    public String getName() {
        return name;
    }
}
