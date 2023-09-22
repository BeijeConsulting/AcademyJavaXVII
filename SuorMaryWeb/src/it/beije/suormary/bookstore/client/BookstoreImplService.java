
package it.beije.suormary.bookstore.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BookstoreImplService", targetNamespace = "http://server.bookstore.suormary.beije.it/", wsdlLocation = "http://localhost:9000/bookstoreServer?wsdl")
public class BookstoreImplService
    extends Service
{

    private final static URL BOOKSTOREIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException BOOKSTOREIMPLSERVICE_EXCEPTION;
    private final static QName BOOKSTOREIMPLSERVICE_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "BookstoreImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9000/bookstoreServer?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BOOKSTOREIMPLSERVICE_WSDL_LOCATION = url;
        BOOKSTOREIMPLSERVICE_EXCEPTION = e;
    }

    public BookstoreImplService() {
        super(__getWsdlLocation(), BOOKSTOREIMPLSERVICE_QNAME);
    }

    public BookstoreImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BOOKSTOREIMPLSERVICE_QNAME, features);
    }

    public BookstoreImplService(URL wsdlLocation) {
        super(wsdlLocation, BOOKSTOREIMPLSERVICE_QNAME);
    }

    public BookstoreImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BOOKSTOREIMPLSERVICE_QNAME, features);
    }

    public BookstoreImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BookstoreImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Bookstore
     */
    @WebEndpoint(name = "BookstoreImplPort")
    public Bookstore getBookstoreImplPort() {
        return super.getPort(new QName("http://server.bookstore.suormary.beije.it/", "BookstoreImplPort"), Bookstore.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Bookstore
     */
    @WebEndpoint(name = "BookstoreImplPort")
    public Bookstore getBookstoreImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.bookstore.suormary.beije.it/", "BookstoreImplPort"), Bookstore.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BOOKSTOREIMPLSERVICE_EXCEPTION!= null) {
            throw BOOKSTOREIMPLSERVICE_EXCEPTION;
        }
        return BOOKSTOREIMPLSERVICE_WSDL_LOCATION;
    }

}
