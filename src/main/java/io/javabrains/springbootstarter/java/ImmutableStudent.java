package io.javabrains.springbootstarter.java;

public final class ImmutableStudent {

    private final int id;
    private final String name;
    private final Age age;

    public ImmutableStudent(int id, String name, Age age) {
        this.id = id;
        this.name = name;

        this.age = new Age(age.getDay(), age.getMonth(), age.getYear());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Age getAge() {
        return new Age(age.getDay(), age.getMonth(), age.getYear());
    }

    @Override
    public String toString() {
        return "ImmutableStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Age {
    private int day;
    private int month;
    private int year;

    public Age(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Age{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}

class Calculator {
    private ImmutableValue currentValue = null;

    public ImmutableValue getValue() {
        return currentValue;
    }

    public void setValue(ImmutableValue newValue) {
        this.currentValue = newValue;
    }

    public void add(int newValue) {
        this.currentValue = this.currentValue.add(newValue);
    }
}

final class ImmutableValue {

    private final int value;

    ImmutableValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }


    public ImmutableValue add(int valueToAdd) {
        return new ImmutableValue(this.value + valueToAdd);
    }

}

class Main {

    public static void main(String[] args) {
        ImmutableValue value = new ImmutableValue(32);
        Calculator calculator = new Calculator();
        calculator.setValue(value);
        System.out.println(calculator.getValue().getValue());

    }
}
