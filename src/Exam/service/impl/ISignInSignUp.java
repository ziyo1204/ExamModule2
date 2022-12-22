package Exam.service.impl;

import Exam.Storage;
import Exam.model.Role;
import Exam.model.User;
import Exam.service.implInterface.SignInSignUp;

import java.util.List;
import java.util.Scanner;

public class ISignInSignUp implements SignInSignUp {
    private static SignInSignUp signInSignUp;

    public static SignInSignUp getInstance() {
        if (signInSignUp == null) {
            signInSignUp = new ISignInSignUp();
        }
        return signInSignUp;
    }

    @Override
    public User signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Loginizni kiriting: ");
        String login = scanner.nextLine();
        System.out.print("Passwordizni kiriting: ");
        String password = scanner.nextLine();
        User user = Storage.users.stream().filter(user1 ->
                        user1.getLogin().equals(login) && user1.getPassword().equals(password))
                .findFirst().orElse(null);
        return user;
    }

    @Override
    public void signUp() {
        System.out.print("Login kiriting: ");
        Scanner scanner = new Scanner(System.in);
        String log = scanner.nextLine();
        User user = new User();
        if (isExist(Storage.users, log)) {
            System.out.print("Name kiriting: ");
            String name = scanner.nextLine();
            System.out.print("Password kiriting: ");
            String pass = scanner.nextLine();
            System.out.print("Balans kiriting: ");
            scanner = new Scanner(System.in);
            int bal = scanner.nextInt();
            user.setId(User.getCurrentId());
            user.setName(name);
            user.setLogin(log);
            user.setPassword(pass);
            user.setBalance(bal);
            user.setRole(Role.USER);
            Storage.users.add(user);
            System.out.println("Muvaffaqqiyatli ro'yxatdan o'tdingiz!");
            IMainMenu.getInstance().startProject();
        } else {
            System.out.println("Bu login ro'yxatdan o'tgan!");
            IMainMenu.getInstance().startProject();
        }

    }

    public boolean isExist(List<User> list, String log) {
        User checkUser = Storage.users.stream().filter(user1 ->
                user1.getLogin().equals(log)).findFirst().orElse(null);
        return checkUser == null;
    }
}
