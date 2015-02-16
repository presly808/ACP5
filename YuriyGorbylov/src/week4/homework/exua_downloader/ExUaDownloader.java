package week4.homework.exua_downloader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

/**
 * Created by yuriygorbylov on 15.02.15.
 */
public class ExUaDownloader extends DefaultHandler {

    ReadableByteChannel rbc;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (attributes != null){
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getValue(i).contains("/load/"));{
                    try {
                        System.out.println("File has been found - http://www.ex.ua" + attributes.getValue(i));
                        URL url = new URL("http://www.ex.ua" + attributes.getValue(i));
                        download(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public void download(URL url) throws IOException {

        rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(new File("/home/yuriygorbylov/Downloads"));
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        URL ex = new URL("http://www.ex.ua/86521907?r=16984,23777");


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(ex.openStream(), new ExUaDownloader());

    }
}
