package week4.homework.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by yuriygorbylov on 15.02.15.
 */
public class DOMtestXML {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        URL ex = new URL("http://www.ex.ua/86521907?r=16984,23777");
        File file = new File("YuriyGorbylov/src/week4/homework/xml/people_list.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(ex.openStream());
        process(doc);

        

    }

    public static void process(Node node){
        if (!node.hasChildNodes()){
            System.out.print("<" + node.getNodeName() + ">");
            return;
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node childNode = list.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) childNode;
                System.out.print("<" + element.getTagName() + ">");
                process(childNode);
                System.out.print("</" + element.getTagName() + ">");
            } else if (childNode.getNodeType() == Node.TEXT_NODE){
                Text textNode = (Text) childNode;
                String text = textNode.getTextContent();
                if (!text.isEmpty()){
                    System.out.print(text);
                }

            }
        }
    }

}