package Exam.service.impl;

import Exam.Storage;
import Exam.model.*;
import Exam.service.implInterface.ManagerConsole;

import java.util.Scanner;

public class IManagerConsole implements ManagerConsole {
    private static ManagerConsole managerConsole;

    public static ManagerConsole getInstance() {
        if (managerConsole == null) {
            managerConsole = new IManagerConsole();
        }
        return managerConsole;
    }

    @Override
    public void openManagerConsole(User user) {
        System.out.println("Welcome " + user.getName() + "!");
        while (true) {
            System.out.println("'1'-\"Kino seans qo'shish\" '2'- \"Bilet qo'shish\" '3'-\"Biletlar holatini ko'rish\" '0'-\"Chiqish\"");
            Scanner scanner = new Scanner(System.in);
            int com = scanner.nextInt();
            if (com == 1) {
                newMovieAdd(user);
            } else if (com == 2) {
                addTicket(user);
            } else if (com == 3) {
                viewTicketStatus(user);
            } else if (com == 0) {
                break;
            } else {
                System.out.println("Error!");
            }
        }
    }

    void newMovieAdd(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("= - = - =\"Kino seans qo'shish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if (com == 0) {
                break;
            } else {
                if (Storage.cinemas.size() == 0) {
                    System.out.println("Cinemalar mavjud emas, Admin Cinema qo'shishini kuting!");
                    break;
                } else {
                    System.out.println("Cinemas: ");
                    for (Cinema cinema : Storage.cinemas) {
                        System.out.println(cinema);
                        System.out.println("= - = - = - = - = - = - = - = - = - = - =");
                    }
                    System.out.print("Kerakli Cinema Id si: ");
                    int cinemId = scanner.nextInt();
                    for (Cinema cinema : Storage.cinemas) {
                        if (cinemId != cinema.getId()) {
                            System.out.println("Mavjud bo'lmagan Id kiritildi!");
                            break;
                        } else{
                            scanner = new Scanner(System.in);
                            System.out.print("Movie name:");
                            String name = scanner.nextLine();
                            System.out.print("Date: ");
                            String date = scanner.nextLine();
                            System.out.print("Description:");
                            String description = scanner.nextLine();
                            scanner = new Scanner(System.in);
                            System.out.print("Price for per seat:");
                            int priceForPerSeat = scanner.nextInt();
                            Movie movie = new Movie(Movie.getCurrentId(), name, date, description, cinema, priceForPerSeat);
                            Storage.movies.add(movie);
                            System.out.println("Kino seans muvaffaqqiyatli qo'shildi!");
                        }
                    }
                }
            }
        }
    }

    void addTicket(User user){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("= - = - =\"Bilet qo'shish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if (com == 0) {
                break;
            } else {
                if(Storage.movies.size()==0){
                    System.out.println("Kinolar mavjud emas!");
                    break;
                }else {
                    System.out.println("Movies: ");
                    for (Movie movie : Storage.movies) {
                        System.out.println(movie);
                        System.out.println("= - = - = - = - = - = - = - = - = - = - =");
                    }
                    System.out.print("Bilet qo'shmoqchi bo'lgan kino Id si: ");
                    int id = scanner.nextInt();
                    for (Movie movie : Storage.movies) {
                        if(movie.getId()!=id){
                            System.out.println("Mavjud bo'lmagan Id kiritildi!");
                            break;
                        }else {
                            System.out.print("Ticket price: ");
                            int price = scanner.nextInt();
                            Ticket ticket = new Ticket(Ticket.getCurrentId(), movie, price, Ticket.getCurrentId(), Status.AVAILABLE);
                            Storage.tickets.add(ticket);
                            System.out.println("Bilet muvaffaqqiyatli qo'shildi!");
                        }
                    }
                }
            }
        }
    }
    void viewTicketStatus(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("= - = - =\"Biletlar holatini ko'rish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if (com == 0) {
                break;
            } else {
                if(Storage.movies.size()==0){
                    System.out.println("Kinolar mavjud emas!");
                    break;
                }else {
                    System.out.println("Movies: ");
                    for (Movie movie : Storage.movies) {
                        System.out.println(movie);
                        System.out.println("= - = - = - = - = - = - = - = - = - = - =");
                    }
                    System.out.print("Movie Id: ");
                    int id = scanner.nextInt();
                    Movie movie = Storage.movies.stream().filter(movie1 -> movie1.getId() == id).findFirst().orElse(null);
                    if(movie==null){
                        System.out.println("Mavjud bo'lmagan Id kiritildi!");
                        break;
                    }else {
                        for (Ticket ticket : Storage.tickets) {
                            if(ticket.getMovie().equals(movie)){
                                System.out.println(ticket);
                            }
                        }
                    }
                }
            }
        }
    }
}
