package week4.day1.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.events.Attribute;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yuriy on 09.02.2015.
 */
public class TestDomParser {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new File("YuriyGorbylov\\src\\week4\\day1\\xml\\users.xml"));
        Element root = document.getDocumentElement(); // Element = <root>

        //System.out.printf("<%s>", root.getTagName());
        //System.out.println();

        NodeList nodeList = root.getChildNodes();
        print(root);

        /*for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                String id = element.getAttribute("id");
                System.out.printf("<%s id=%s>%n", element.getTagName(), id);
            }
        }*/
    }

    public static void print(Element root){
        NodeList nodeList = root.getChildNodes();
        print(nodeList, "");

    }
    public static void print(NodeList nodeList, String deep){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                String id = element.getAttribute("id");
                System.out.printf("%s<%s id=%s>%n", deep, element.getTagName(), id);
            } else if (node.getNodeType() == Node.ATTRIBUTE_NODE){
                Attribute attribute = (Attribute) node;
                System.out.printf("%s<%s>%n", deep, attribute.getValue());
            }else if (node.getNodeType() == Node.TEXT_NODE){
                Text text = (Text) node;
                System.out.printf("%s<%s>%n", deep, text.getWholeText());
            }
            if (node.getChildNodes() == null){
                return;
            } else{
                deep = deep + "  ";
                print(node.getChildNodes(), deep);
            }

        }

    }
}
