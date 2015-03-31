package ua.artcode.ws.server;

import ua.artcode.ws.LoggingSoapMessageHandler;
import ua.artcode.ws.endpoint.ProductService;

import javax.xml.ws.Endpoint;

/**
 * Created by serhii on 30.03.15.
 */
public class RunServer {

    public static void main(String[] args) {
        Endpoint endpoint =
                Endpoint.create(new ProductService());

        endpoint.getBinding().getHandlerChain()
                .add(new LoggingSoapMessageHandler());

        endpoint.publish("http://192.168.1.50:9999/ws/product");
    }
}
