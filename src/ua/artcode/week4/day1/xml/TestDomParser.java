package ua.artcode.week4.day1.xml;

import org.w3c.dom.Document;
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
 * Created by serhii on 09.02.15.
 */
public class TestDomParser {


    public static final String PATHNAME = "../ACP5/temp/users.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new File(PATHNAME));
        Element root = document.getDocumentElement();

        System.out.printf("<%s>\n", root.getTagName());
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                String id = element.getAttribute("id");
                System.out.printf("<%s id=%s>\n", element.getTagName(), id);
            }
        }
        System.out.printf("</%s>", root.getTagName());

    }
}
