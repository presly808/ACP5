package hw4.task1.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser {

    public static List<TestUser> deserialize(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        Handler handler = new Handler();
        File file = new File(path);
        parser.parse(file, handler);
        return handler.users;
    }
}

class Handler extends DefaultHandler {

    List<TestUser> users;
    String qName;

    @Override
    public void startDocument() throws SAXException {
        users = new ArrayList<TestUser>(5);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.qName = qName;
        if (qName.equals("user")) {
            users.add(new TestUser());
            String id = attributes.getValue(0);
            users.get(users.size() - 1).setId(new Integer(id));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);

        if (qName.equals("name")) {
            users.get(users.size() - 1).setName(value);

        } else if (qName.equals("age")) {
            users.get(users.size() - 1).setAge(new Integer(value));

        } else if (qName.equals("salary")) {
            users.get(users.size() - 1).setSalary(new Integer(value));

        } else if (qName.equals("city")) {
            users.get(users.size() - 1).setCity(value);

        } else if (qName.equals("building")) {
            users.get(users.size() - 1).setBuilding(value);

        } else if (qName.equals("room")) {
            users.get(users.size() - 1).setRoom(value);
        }
        qName = "";
    }
}