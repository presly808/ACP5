package hw4.task1.dom;

import hw4.task1.sax.TestUser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    public static void serialize(List<TestUser> users, String path) throws ParserConfigurationException, TransformerException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();

        Element rootElement = document.createElement("users");
        Element userElement;
        Element nameElement;
        Element ageElement;
        Element salaryElement;
        Element addressElement;
        Element cityElement;
        Element buildingElement;
        Element roomElement;

        for (int i = 0; i < users.size(); i++) {

            userElement = document.createElement("user");
            nameElement = document.createElement("name");
            ageElement = document.createElement("age");
            salaryElement = document.createElement("salary");
            addressElement = document.createElement("address");
            cityElement = document.createElement("city");
            buildingElement = document.createElement("building");
            roomElement = document.createElement("room");

            rootElement.appendChild(userElement);
            userElement.appendChild(nameElement);
            userElement.appendChild(ageElement);
            userElement.appendChild(salaryElement);
            userElement.appendChild(addressElement);
            addressElement.appendChild(cityElement);
            addressElement.appendChild(buildingElement);
            addressElement.appendChild(roomElement);

            Attr userId = document.createAttribute("id");
            userId.setValue(String.valueOf(users.get(i).getId()));
            userElement.setAttributeNode(userId);

            nameElement.appendChild(document.createTextNode(users.get(i).getName()));
            ageElement.appendChild(document.createTextNode(String.valueOf(users.get(i).getAge())));
            salaryElement.appendChild(document.createTextNode(String.valueOf(users.get(i).getSalary())));
            cityElement.appendChild(document.createTextNode(users.get(i).getCity()));
            buildingElement.appendChild(document.createTextNode(users.get(i).getBuilding()));
            roomElement.appendChild(document.createTextNode(users.get(i).getRoom()));
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(source, streamResult);
    }

    public static List<TestUser> deserializer(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new File(path));
        List<TestUser> users = new ArrayList<TestUser>(3);

        NodeList userNodeList = document.getElementsByTagName("user");

        for (int i = 0; i < userNodeList.getLength(); i++) {
            Element element = (Element) userNodeList.item(i);

            int id  = new Integer(element.getAttribute("id"));

            String name = element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
            int age = new Integer(element.getElementsByTagName("age").item(0).getChildNodes().item(0).getNodeValue());
            int salary = new Integer(element.getElementsByTagName("salary").item(0).getChildNodes().item(0).getNodeValue());

            String city = element.getElementsByTagName("city").item(0).getChildNodes().item(0).getNodeValue();
            String building = element.getElementsByTagName("building").item(0).getChildNodes().item(0).getNodeValue();
            String room = element.getElementsByTagName("room").item(0).getChildNodes().item(0).getNodeValue();

            users.add(new TestUser(id, name, age, salary, city, building, room));
        }
        return users;
    }
}