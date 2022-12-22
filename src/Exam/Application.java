package Exam;

import Exam.service.implInterface.MainMenu;
import Exam.service.impl.IMainMenu;

public class Application {
    public static void main(String[] args) {
        MainMenu instance = IMainMenu.getInstance();
        instance.startProject();
    }
}