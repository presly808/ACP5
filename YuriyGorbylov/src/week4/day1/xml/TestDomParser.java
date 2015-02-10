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


        System.out.println(print(root));


    }

    public static String print(Node curr){
        if (!curr.hasChildNodes()){
            return "";
        }else{
            NodeList nodeList = curr.getChildNodes();
            String resStr = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    resStr += String.format("<%s>%s</%s>", element.getTagName(), print(element), element.getTagName());
                }else if (node.getNodeType() == Node.TEXT_NODE){
                    Text textNode = (Text) node;
                    String text = textNode.getTextContent();
                    if (!text.isEmpty()){
                        resStr += text;
                    }
                }
            }
            return resStr;
        }
    }
}
