package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by serhii on 31.03.15.
 */
public class MarshallerTest {

    private static final String BODY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                    "<product>\n" +
                                    "    <id>1</id>\n" +
                                    "    <name>item</name>\n" +
                                    "    <price>2300.0</price>\n" +
                                    "</product>\n";
    public static final String PATHNAME = "/home/serhii/IdeaProjects/ACP5/RestServer/src/main/resources/body.xml";

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Product.class);
            Marshaller marshaller = context.createMarshaller();
            Unmarshaller unmarshaller = context.createUnmarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Product(1,"item",2300), System.out);

            Product product = (Product) unmarshaller.unmarshal(new File(PATHNAME));
            System.out.println(product.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
