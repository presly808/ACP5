package week4;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by dmartyuk on 09.02.2015.
 */
public class TestDOMParser {

    public static final String PATHNAME = "dmartyuk/tmp/users.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new File(PATHNAME));
        Element root = document.getDocumentElement();
        System.out.println(root.getParentNode().getNodeType());
        String result = showInfo(root);
        System.out.println(result);
//        NodeList nodeList = root.getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            if(node.getNodeType() == Node.ELEMENT_NODE) {
//                Element element = (Element) node;
//                String id = element.getAttribute("id");
//                System.out.printf("<%s id=%s> %n",element.getTagName(), id);
//            }
//
//        }
    }

    public static String showInfo(Node curr) {
        if (!curr.hasChildNodes()) {
            return "";
        } else {
            NodeList children = curr.getChildNodes();
            String retStr = "";
            if(curr.getParentNode().getNodeType() == Node.DOCUMENT_NODE){
                retStr += String.format("<%s>", ((Element) curr).getTagName());
            }
            for (int i = 0; i < children.getLength(); i++) {
                Node iter = children.item(i);
                if (iter.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) iter;

                    retStr += String.format("<%s>%s</%s>",
                            element.getTagName(), showInfo(element), element.getTagName());
                } else if (iter.getNodeType() == Node.TEXT_NODE) {
                    Text textNode = (Text) iter;
                    String text = textNode.getTextContent();
                    if (!text.isEmpty()) {
                        retStr += text;
                    }
                }
            }
            if(curr.getParentNode().getNodeType() == Node.DOCUMENT_NODE){
                retStr += String.format("</%s>", ((Element) curr).getTagName());
            }
            return retStr;
        }

    }
}
