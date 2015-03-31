package ua.artcode.week9.soap.endpoint;

import ua.artcode.week9.soap.ws.MyEndpointImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by serhii on 27.03.15.
 */
public class MyEndpointPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/product", new MyEndpointImpl());
    }

}
