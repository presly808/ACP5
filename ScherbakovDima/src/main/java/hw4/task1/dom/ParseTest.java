package hw4.task1.dom;

import hw4.task1.sax.TestUser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class ParseTest {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        List<TestUser> users = DomParser.deserializer("temp/user.xml");
        System.out.println(users);
        DomParser.serialize(users, "temp/users.xml");
    }
}
