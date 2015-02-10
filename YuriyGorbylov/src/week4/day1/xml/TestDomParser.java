package week4.day1.xml;

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
 * Created by Yuriy on 09.02.2015.
 */
public class TestDomParser {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new File("D:\\workspace\\ACP5\\YuriyGorbylov\\src\\week4\\day1\\xml\\users.xml"));
        Element root = document.getDocumentElement(); // Element = <root>

        System.out.printf("<%s>", root.getTagName());
        System.out.println();
        //System.out.println(root.getTextContent());
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                String id = element.getAttribute("id");
                System.out.printf("<%s id=%s>%n", element.getTagName(), id);
            }
        }
    }
}
