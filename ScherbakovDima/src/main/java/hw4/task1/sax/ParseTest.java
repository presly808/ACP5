package hw4.task1.sax;

import hw4.task1.sax.SaxParser;
import hw4.task1.sax.TestUser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Templer on 15.02.2015.
 */
public class ParseTest {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        List<TestUser> users;
        String path = "temp/user.xml";
        users = SaxParser.deserialize(path);
        System.out.println(users);
    }
}
