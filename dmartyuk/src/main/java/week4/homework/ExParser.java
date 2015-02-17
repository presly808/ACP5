package week4.homework;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by dmartyuk on 15.02.2015.
 */
public class ExParser {
    private static final String TORRENT = "торрент";
    private static final String PLAY = "играть";
    private static final String XSPF = ".xspf";

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://www.ex.ua/85405281?r=1989,23775").get();
        Elements links = doc.select("a[href*=/get/]");
        System.out.printf("Links: (%d)\n", links.size());
        for (Element link : links) {
            if(!PLAY.equals(link.text())){
                System.out.printf(" * a: <%s>  (%s)\n", link.attr("abs:href"), link.text(), 35);
//                test(link.attr("abs:href"));
            } else {
//                System.out.printf(" * a: <%s>  (%s)\n", link.attr("abs:href"), link.text(), 35);

            }
        }
    }

    public static void test(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.getElementsByTag("trackList");//select("trackList");
        System.out.printf("Links: (%d)\n", links.size());
        int i = 0;
        for (Element link : links) {
            System.out.println("-"+link);
            Elements ch = link.children();
            for (Element l : ch) {
                System.out.println(l.tagName());

                System.out.println(i++);
            }
        }
    }
}
