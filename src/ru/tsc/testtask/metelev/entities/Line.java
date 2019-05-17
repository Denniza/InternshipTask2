package ru.tsc.testtask.metelev.entities;

public class Line{
    private int Id;
    private String value;

    @Override
    public String toString() {
        return "Line{" +
                "Id=" + Id +
                ", value='" + value + '\'' +
                '}';
    }

    public Line(int id, String value) {
        Id = id;
        this.value = value;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
