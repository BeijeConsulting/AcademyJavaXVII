
package it.beije.suormary.web.ws.jaxws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Bookstore", targetNamespace = "http://server.jaxws.ws.web.suormary.beije.it/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Bookstore {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getOrders", targetNamespace = "http://server.jaxws.ws.web.suormary.beije.it/", className = "it.beije.suormary.web.ws.jaxws.client.GetOrders")
    @ResponseWrapper(localName = "getOrdersResponse", targetNamespace = "http://server.jaxws.ws.web.suormary.beije.it/", className = "it.beije.suormary.web.ws.jaxws.client.GetOrdersResponse")
    @Action(input = "http://server.jaxws.ws.web.suormary.beije.it/Bookstore/getOrdersRequest", output = "http://server.jaxws.ws.web.suormary.beije.it/Bookstore/getOrdersResponse")
    public String getOrders(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBooks", targetNamespace = "http://server.jaxws.ws.web.suormary.beije.it/", className = "it.beije.suormary.web.ws.jaxws.client.GetBooks")
    @ResponseWrapper(localName = "getBooksResponse", targetNamespace = "http://server.jaxws.ws.web.suormary.beije.it/", className = "it.beije.suormary.web.ws.jaxws.client.GetBooksResponse")
    @Action(input = "http://server.jaxws.ws.web.suormary.beije.it/Bookstore/getBooksRequest", output = "http://server.jaxws.ws.web.suormary.beije.it/Bookstore/getBooksResponse")
    public String getBooks();

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addBook", targetNamespace = "http://server.jaxws.ws.web.suormary.beije.it/", className = "it.beije.suormary.web.ws.jaxws.client.AddBook")
    @ResponseWrapper(localName = "addBookResponse", targetNamespace = "http://server.jaxws.ws.web.suormary.beije.it/", className = "it.beije.suormary.web.ws.jaxws.client.AddBookResponse")
    @Action(input = "http://server.jaxws.ws.web.suormary.beije.it/Bookstore/addBookRequest", output = "http://server.jaxws.ws.web.suormary.beije.it/Bookstore/addBookResponse")
    public String addBook(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        double arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        int arg5);

}
