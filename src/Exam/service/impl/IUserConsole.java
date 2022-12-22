package Exam.service.impl;

import Exam.Storage;
import Exam.model.*;
import Exam.service.implInterface.UserConsole;

import java.util.Scanner;

public class IUserConsole implements UserConsole {
    private static UserConsole userConsole;

    public static UserConsole getInstance() {
        if (userConsole == null) {
            userConsole = new IUserConsole();
        }
        return userConsole;
    }

    @Override
    public void openUserConsole(User user) {
        System.out.println("Welcome " + user.getName() + "!");
        while (true) {
            System.out.println("'1'-\"Ro'yxatdan o'tish\" '2'-\"Seansga bilet sotib olish\" " +
                    "'3'-\"Biletlar tarixi\" '0'-\"Chiqish\"");
            Scanner scanner = new Scanner(System.in);
            int com = scanner.nextInt();
            if (com == 1) {
                signUpCinema(user);
            } else if (com == 2) {
                buyTicketToSeans(user);
            } else if (com == 3) {
                istoryTicket(user);
            } else if (com == 0) {
                break;
            } else {
                System.out.println("Error!");
            }
        }
    }

    void signUpCinema(User user){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("= - = - =\"Ro'yxatdan o'tish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if(com==0){
                break;
            }else {
                ISignInSignUp.getInstance().signUp();
            }
        }
    }

    void buyTicketToSeans(User user){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("= - = - =\"Seansga bilet sotib olish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if(com==0){
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
                        if(ticket.getMovie().equals(movie)&&ticket.getStatus().equals(Status.AVAILABLE)){
                            System.out.println(ticket);
                        }
                    }
                    System.out.print("Ticket id: ");
                    int id1 = scanner.nextInt();
                    Ticket ticket1 = Storage.tickets.stream().filter(ticket -> ticket.getId() == id1).findFirst().orElse(null);
                    if(ticket1==null){
                        System.out.println("Mavjud bo'lmagan Id kiritildi!");
                        break;
                    }else {
                        Order order = new Order(id1, user, ticket1, ticket1.getPrice());
                        Storage.orders.add(order);
                        System.out.println("Bilet muvaffaqqiyatli sotib olindi!");
                    }
                }
            }
        }
    }

    void istoryTicket(User user){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("= - = - =\"Biletlar tarixini ko'rish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if(com==0){
                break;
            }else {
                Order order1 = Storage.orders.stream().filter(order -> order.getUser().equals(user)).findFirst().orElse(null);
                if(order1==null){
                    System.out.println("Sizda bilet mavjud emas!");
                    break;
                }else {
                    System.out.println(order1);
                }
            }
        }
    }
}

