package Exam.service.impl;

import Exam.Storage;
import Exam.model.Cinema;
import Exam.model.CinemaManager;
import Exam.model.Role;
import Exam.model.User;
import Exam.service.implInterface.AdminConsole;

import java.util.List;
import java.util.Scanner;

public class IAdminConsole implements AdminConsole {
    private static AdminConsole adminConsole;

    public static AdminConsole getInstance() {
        if (adminConsole == null) {
            adminConsole = new IAdminConsole();
        }
        return adminConsole;
    }

    @Override
    public void openAdminConsole(User user) {
        System.out.println("Welcome " + user.getName() + "!");
        while (true) {
            System.out.println("'1'-\"Yangi Kinoteatr yaratish\" '2'-\"Yangi manager yaratish\" " +
                    "'3'-\"Kinoteatrga manager biriktirish\" '0'-\"Chiqish\"");
            Scanner scanner = new Scanner(System.in);
            int com = scanner.nextInt();
            if (com == 1) {
                newCinemaCreat();
            } else if (com == 2) {
                newManagerCreat();
            } else if (com == 3) {
                managerAddedToCinema();
            } else if (com == 0) {
                break;
            } else {
                System.out.println("Error!");
            }
        }
    }

    void newCinemaCreat() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("= - = - =\"Yangi Kinoteatr yaratish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if (com == 0) {
                break;
            } else {
                Cinema cinema = new Cinema();
                scanner = new Scanner(System.in);
                System.out.print("Cinema name: ");
                String name = scanner.nextLine();
                System.out.print("Cinema address: ");
                String address = scanner.nextLine();
                System.out.print("Number of seats: ");
                String numberOfSeats = scanner.nextLine();
                cinema.setId(Cinema.getCurrentId());
                cinema.setName(name);
                cinema.setAddress(address);
                cinema.setNumberOfSeats(numberOfSeats);
                Storage.cinemas.add(cinema);
                System.out.println("Yangi kinoteatr muvaffaqqiyatli yaratildi!");
            }
        }
    }

    void newManagerCreat() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("= - = - =\"Yangi manager yaratish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if (com == 0) {
                break;
            } else {
                scanner = new Scanner(System.in);
                System.out.print("User name: ");
                String name = scanner.nextLine();
                System.out.print("Login: ");
                String login = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                User user = new User(User.getCurrentId(), name, login, password, Role.MANAGER, 0);
                Storage.users.add(user);
                System.out.println("Yangi manager muvaffaqqiyatli yaratildi!");
            }
        }
    }

    void managerAddedToCinema() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("= - = - =\"Kinoteatrga manager biriktirish menyusi.\"= - = - =");
            System.out.println("'1'- \"Davom etish\" '0'- \"Chiqish\"");
            int com = scanner.nextInt();
            if (com == 0) {
                break;
            } else {
                List<User> users = Storage.users.stream().filter(user1 ->
                        user1.getRole().equals(Role.MANAGER)).toList();
                if(users.size()==0){
                    System.out.println("Managerlar ro'yxati bo'sh, oldin manager qo'shing!");
                    break;
                }else {
                    for (User user : users) {
                        System.out.println(user);
                        System.out.println("= - = - = - = - = - = - = - = - = - = - =");
                    }
                    System.out.println("Kerakli manager id si:");
                    int id = scanner.nextInt();
                    User manager = users.stream().filter(user1 ->
                            user1.getId() == id).findFirst().orElse(null);
                    if(manager==null){
                        System.out.println("Mavjud bo'lmagan Id kiritildi!");
                        break;
                    }else {
                        if(Storage.cinemas.size()==0){
                            System.out.println("Cinemalar ro'yxati bo'sh, oldin Cinema qo'shing!");
                            break;
                        }else {
                            for (Cinema cinema : Storage.cinemas) {
                                System.out.println(cinema);
                                System.out.println("= - = - = - = - = - = - = - = - = - = - =");
                            }
                            System.out.println("Kerakli Cinema id si:");
                            int cinemaId = scanner.nextInt();
                            Cinema cinema = Storage.cinemas.stream().filter(cinema1 ->
                                    cinema1.getId() == cinemaId).findFirst().orElse(null);
                            if(cinema==null){
                                System.out.println("Mavjud bo'lmagan Id kiritildi!");
                                break;
                            }else {
                                CinemaManager cinemaManager = new CinemaManager();
                                cinemaManager.setId(CinemaManager.getCurrentId());
                                cinemaManager.setCinema(cinema);
                                cinemaManager.setManager(manager);
                                Storage.cinemaManagers.add(cinemaManager);
                                System.out.println("Tanlangan manager Cinemaga muvaffaqqiyatli biriktirildi!");
                            }
                        }
                    }
                }
            }
        }
    }
}
