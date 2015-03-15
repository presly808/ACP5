package ua.artcode;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.view.MenuConsole;

import java.util.Scanner;

/**
 * Created by serhii on 24.02.15.
 */
public class Run {

    public static void main(String[] args) {
        ApplicationContext ap =
                new ClassPathXmlApplicationContext("classpath:app-context.xml");

        MenuConsole menuConsole = ap.getBean(MenuConsole.class);

        Scanner scanner = new Scanner(System.in);
        while (true){
            menuConsole.showMenu();
            int choice = scanner.nextInt();
            menuConsole.choose(choice);
        }
    }

}
