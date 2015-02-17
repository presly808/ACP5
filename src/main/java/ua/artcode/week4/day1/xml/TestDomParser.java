package ua.artcode.week4.day1.xml;

import org.w3c.dom.*;
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

        String res = DomParsingUtils.showInfo(root);
        System.out.println(res);


    }



}
