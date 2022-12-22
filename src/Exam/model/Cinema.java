package Exam.model;

import java.util.Objects;

public class Cinema {
    private int id;
    private String name;
    private String address;
    private String numberOfSeats;
    public static int currentId=0;
    {
        currentId++;
    }

    public Cinema() {
    }

    public Cinema(int id, String name, String address, String numberOfSeats) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfSeats = numberOfSeats;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public static int getCurrentId() {
        return currentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return id == cinema.id && Objects.equals(name, cinema.name) && Objects.equals(address, cinema.address) && Objects.equals(numberOfSeats, cinema.numberOfSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, numberOfSeats);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numberOfSeats='" + numberOfSeats + '\'' +
                '}';
    }
}
