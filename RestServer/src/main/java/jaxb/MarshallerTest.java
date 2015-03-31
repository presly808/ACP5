package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Created by serhii on 31.03.15.
 */
public class MarshallerTest {

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Product.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Product(1,"item",2300), System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
