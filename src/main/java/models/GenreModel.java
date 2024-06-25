package models;

public class GenreModel {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public GenreModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", name=" + name + '}';
    }

    public void setName(String name) {
        this.name = name;
    }

}
