package week4.homework.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by yuriygorbylov on 15.02.15.
 */
public class MySaxParser extends DefaultHandler {

    private int elements;
    private int attributes;
    private int characters;
    private int ignorableWhitespace;


    @Override
    public void startDocument() throws SAXException {
        System.out.println("START OF DOCUMENT");
        // Статистика
        elements            = 0;
        attributes          = 0;
        characters          = 0;
        ignorableWhitespace = 0;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\nEND OF DOCUMENT");
        System.out.println("elements = " + elements + "\nattributes = " + attributes + "\ncharacters = " + characters);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        elements++;
        if (attrs != null){
            attributes += attrs.getLength();
        }

        System.out.print("<" + qName);
        if (attrs != null){
            for (int i = 0; i < attrs.getLength(); i++) {
                System.out.print(" " + attrs.getQName(i) + "=\"" + attrs.getValue(i) + "\"");
            }
        }
        System.out.print(">");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("</" + qName + ">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        characters += length;
        System.out.print(String.valueOf(ch, start, length));
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        URL ex = new URL("http://www.ex.ua/86521907?r=16984,23777");
        File file = new File("YuriyGorbylov/src/week4/homework/xml/people_list.xml");

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(file, new MySaxParser());


    }
}
