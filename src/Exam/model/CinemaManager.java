package Exam.model;

import java.util.Objects;

public class CinemaManager {
    private int id;
    private Cinema cinema;
    private User manager;
    public static int currentId=0;
    {
        currentId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public static int getCurrentId() {
        return currentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaManager that = (CinemaManager) o;
        return id == that.id && Objects.equals(cinema, that.cinema) && Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cinema, manager);
    }

    @Override
    public String toString() {
        return "CinemaManager{" +
                "id=" + id +
                ", cinema=" + cinema +
                ", manager=" + manager +
                '}';
    }
}
