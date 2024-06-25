package models;

public class BorrowerModel {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Borrower{" + "id=" + id + ", name=" + name + '}';
    }

    public BorrowerModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
