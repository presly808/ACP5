package hw4.task3;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class DownloaderTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Print url - ");
        String url = scanner.next();
        URL page = new URL(url);
        String path = "C:/Users/Templer/Downloads/Новая папка";
        Downloader.load(page, path);
    }
}
