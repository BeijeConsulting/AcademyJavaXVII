package it.beije.suormary.web.ws.jaxws.server;

import javax.xml.ws.Endpoint;



//wsimport -keep -p it.beije.suormary.web.ws.jaxws.client http://localhost:9000/bookstore?wsdl

public class BookstorePublisher {
    public static void main(String[] args) {
    	System.out.println("BookstoretermPublisher...");
        Endpoint.publish("http://localhost:9000/bookstore", new BookstoreImpl());
    }
}