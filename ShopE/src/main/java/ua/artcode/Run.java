package ua.artcode;

import ua.artcode.view.MenuConsole;

import java.util.Scanner;

/**
 * Created by serhii on 24.02.15.
 */
public class Run {

    public static void main(String[] args) {
        MenuConsole menuConsole = new MenuConsole();

        Scanner scanner = new Scanner(System.in);
        while (true){
            menuConsole.showMenu();
            int choice = scanner.nextInt();
            menuConsole.choose(choice);
        }
    }

}
