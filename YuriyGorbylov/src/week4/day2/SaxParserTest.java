package week4.day2;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yuriy on 10.02.2015.
 */
public class SaxParserTest {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(new File("YuriyGorbylov\\src\\week4\\day1\\xml\\users.xml"), new MyHandler());
    }
}

class MyHandler extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.printf("<%s>", qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.printf("</%s>", qName);

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(String.valueOf(ch, start, length));
    }
}