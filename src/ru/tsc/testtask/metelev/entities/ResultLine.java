package ru.tsc.testtask.metelev.entities;

public class ResultLine extends Line {
    private String secondValue;

    public ResultLine(Line first, Line second) {
        super(first.getId(),first.getValue());
        this.secondValue = second.getValue();
    }

    public String getSecondValue() {
        return secondValue;
    }

    @Override
    public String toString() {
        return "ResultLine{" +"Id=" + this.getId() +
                ", value='" + this.getValue() + '\'' +
                "secondValue='" + secondValue + '\'' +
                '}';
    }
}
