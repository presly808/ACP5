package hw4.task2;


import java.io.File;
import java.util.Scanner;

public class SearchFileTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Print file name - ");
        String fileName = scanner.next();
        File root = new File("C:\\Users\\Templer\\Desktop");
        System.out.println(SearchFile.search(fileName, root));
    }
}
