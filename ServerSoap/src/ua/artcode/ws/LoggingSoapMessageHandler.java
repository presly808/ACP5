package ua.artcode.ws;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.util.Set;

/**
 * Created by serhii on 30.03.15.
 */
public class LoggingSoapMessageHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean bool = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        String messageType = bool ? "****OUTPUT MESSAGE****" : "****INPUT MESSAGE****";
        System.out.println(messageType);

        SOAPMessage soapMessage = context.getMessage();
        try {
            soapMessage.writeTo(System.out);
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
