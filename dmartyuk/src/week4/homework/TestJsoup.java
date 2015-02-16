package week4.homework;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by dmartyuk on 16.02.2015.
 */
public class TestJsoup {
    private static String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<playlist version=\"1\" xmlns=\"http://xspf.org/ns/0/\">\n" +
            "<title>Спецагент Арчер / Archer (2015) / WEB-DLRip / NewStudio.TV / (Сезон 6) Серия 1 - 5 [ru ,en]</title>\n" +
            "<location>http://www.ex.ua/view/85405281</location>\n" +
            "<trackList>\n" +
            "<track>\n" +
            "\t<title>Archer.s06e01.WEBDLRip.NewStudio.TV.avi</title>\n" +
            "\t<location>http://www.ex.ua/get/146761965</location>\n" +
            "</track>\n" +
            "<track>\n" +
            "\t<title>Archer.s06e02.WEBDLRip.NewStudio.TV.avi</title>\n" +
            "\t<location>http://www.ex.ua/get/147537124</location>\n" +
            "</track>\n" +
            "<track>\n" +
            "\t<title>Archer.s06e03.WEBDLRip.NewStudio.TV.avi</title>\n" +
            "\t<location>http://www.ex.ua/get/148743392</location>\n" +
            "</track>\n" +
            "<track>\n" +
            "\t<title>Archer.s06e04.WEBDLRip.NewStudio.TV.avi</title>\n" +
            "\t<location>http://www.ex.ua/get/149864144</location>\n" +
            "</track>\n" +
            "<track>\n" +
            "\t<title>Archer.s06e05.WEBDLRip.NewStudio.TV.avi</title>\n" +
            "\t<location>http://www.ex.ua/get/151107190</location>\n" +
            "</track>\n" +
            "</trackList>\n" +
            "</playlist>";

    public static final String PATHNAME = "dmartyuk/tmp/testJsoup.xml";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
//        Parser parser = Parser.xmlParser();
//        Document document = parser.parseInput(xml, "http://www.ex.ua/view/85405281");

//        Document document = Jsoup.parse(new File(PATHNAME), "UTF-8");
//        XmlDeclaration xml = new XmlDeclaration();
//        Elements elements = document.getAllElements();
//        System.out.println(elements.outerHtml());





    }

    private static void xmlDom() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.parse(new File(PATHNAME));
        NodeList nodeList = document.getElementsByTagName("track");
        System.out.println(nodeList.item(2).getTextContent());
        System.out.println(nodeList.item(2).getNodeType());
        System.out.println(nodeList.item(2).getChildNodes().getLength());
        for (int i = 0; i < nodeList.item(2).getChildNodes().getLength(); i++) {
            Node node = nodeList.item(2).getChildNodes().item(i);
            System.out.println("---" + i + "---");
            System.out.println(node.getNodeName());
            System.out.println(node.getNodeType());
            System.out.println(node.getTextContent());
        }
        System.out.println(nodeList.item(2).getChildNodes().item(1).getTextContent());
        System.out.println(nodeList.item(2).getChildNodes().item(3).getTextContent());
    }
}
