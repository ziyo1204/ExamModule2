package Exam.service.impl;

import Exam.Storage;
import Exam.model.CinemaManager;
import Exam.model.Role;
import Exam.model.User;
import Exam.service.implInterface.MainMenu;

import java.util.Scanner;

public class IMainMenu implements MainMenu {
    private static MainMenu mainMenu;

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new IMainMenu();
        }
        return mainMenu;
    }

    @Override
    public void startProject() {
        Storage.users.add(new User(User.getCurrentId(), "Adminbek", "admin", "admin", Role.ADMIN, 0));
        System.out.println("Xush kelibsiz!");
        while (true) {
            System.out.println("'1'-\"Kirish\" '2'-\"Ro'yxatdan o'tish\" '0'-\"Chiqish\"");
            Scanner scanner = new Scanner(System.in);
            int com = scanner.nextInt();
            if (com == 1) {
                User user = ISignInSignUp.getInstance().signIn();
                if (user == null) {
                    System.out.println("Kirish uchun registratsiyadan o'ting!");
                } else {
                    if (user.getRole().equals(Role.ADMIN)) {
                        IAdminConsole.getInstance().openAdminConsole(user);
                    }
                    if (user.getRole().equals(Role.MANAGER)) {
                        IManagerConsole.getInstance().openManagerConsole(user);
                    }
                    if (user.getRole().equals(Role.USER)) {
                        IUserConsole.getInstance().openUserConsole(user);
                    }
                }
            } else if (com == 2) {
                ISignInSignUp.getInstance().signUp();
            } else if (com == 0) {
                break;
            } else {
                System.out.println("Error!");
            }
        }
    }
}
