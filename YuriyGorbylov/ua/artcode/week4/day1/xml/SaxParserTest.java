package ua.artcode.week4.day1.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by serhii on 10.02.15.
 */
public class SaxParserTest {

    public static final String PATHNAME = "../ACP5/temp/users.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(new File(PATHNAME), new MyHandler());
    }

}

class MyHandler extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.printf("<%s>", qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(String.valueOf(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.printf("</%s>", qName);
    }
}
