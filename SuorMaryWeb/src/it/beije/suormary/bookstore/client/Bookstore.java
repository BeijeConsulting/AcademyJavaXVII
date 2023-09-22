
package it.beije.suormary.bookstore.client;

import java.util.List;
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
@WebService(name = "Bookstore", targetNamespace = "http://server.bookstore.suormary.beije.it/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Bookstore {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<it.beije.suormary.bookstore.client.Order>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getOrders", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetOrders")
    @ResponseWrapper(localName = "getOrdersResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetOrdersResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/getOrdersRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/getOrdersResponse")
    public List<Order> getOrders(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "userExists", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.UserExists")
    @ResponseWrapper(localName = "userExistsResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.UserExistsResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/userExistsRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/userExistsResponse")
    public boolean userExists(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "editStatus", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.EditStatus")
    @ResponseWrapper(localName = "editStatusResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.EditStatusResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/editStatusRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/editStatusResponse")
    public boolean editStatus(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addAuthor", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.AddAuthor")
    @ResponseWrapper(localName = "addAuthorResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.AddAuthorResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/addAuthorRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/addAuthorResponse")
    public boolean addAuthor(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg0
     * @return
     *     returns it.beije.suormary.bookstore.client.Book
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBook", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetBook")
    @ResponseWrapper(localName = "getBookResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetBookResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/getBookRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/getBookResponse")
    public Book getBook(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns it.beije.suormary.bookstore.client.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkUser", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.CheckUser")
    @ResponseWrapper(localName = "checkUserResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.CheckUserResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/checkUserRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/checkUserResponse")
    public User checkUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUserId", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetUserId")
    @ResponseWrapper(localName = "getUserIdResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetUserIdResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/getUserIdRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/getUserIdResponse")
    public int getUserId(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addCartItem", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.AddCartItem")
    @ResponseWrapper(localName = "addCartItemResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.AddCartItemResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/addCartItemRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/addCartItemResponse")
    public boolean addCartItem(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Integer arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        Integer arg2);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteCart", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.DeleteCart")
    @ResponseWrapper(localName = "deleteCartResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.DeleteCartResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/deleteCartRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/deleteCartResponse")
    public boolean deleteCart(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns it.beije.suormary.bookstore.client.Order
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getOrder", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetOrder")
    @ResponseWrapper(localName = "getOrderResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetOrderResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/getOrderRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/getOrderResponse")
    public Order getOrder(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<it.beije.suormary.bookstore.client.CartItem>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCart", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetCart")
    @ResponseWrapper(localName = "getCartResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetCartResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/getCartRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/getCartResponse")
    public List<CartItem> getCart(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createUser", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.CreateUserResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/createUserRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/createUserResponse")
    public boolean createUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "removeCartItem", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.RemoveCartItem")
    @ResponseWrapper(localName = "removeCartItemResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.RemoveCartItemResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/removeCartItemRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/removeCartItemResponse")
    public boolean removeCartItem(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addNewBook", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.AddNewBook")
    @ResponseWrapper(localName = "addNewBookResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.AddNewBookResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/addNewBookRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/addNewBookResponse")
    public boolean addNewBook(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        double arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        int arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        int arg5);

    /**
     * 
     * @return
     *     returns java.util.List<it.beije.suormary.bookstore.client.Book>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllBooks", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetAllBooks")
    @ResponseWrapper(localName = "getAllBooksResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetAllBooksResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/getAllBooksRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/getAllBooksResponse")
    public List<Book> getAllBooks();

    /**
     * 
     * @return
     *     returns java.util.List<it.beije.suormary.bookstore.client.Author>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAuthorList", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetAuthorList")
    @ResponseWrapper(localName = "getAuthorListResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.GetAuthorListResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/getAuthorListRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/getAuthorListResponse")
    public List<Author> getAuthorList();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createOrder", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.CreateOrder")
    @ResponseWrapper(localName = "createOrderResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.CreateOrderResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/createOrderRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/createOrderResponse")
    public boolean createOrder(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteOrder", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.DeleteOrder")
    @ResponseWrapper(localName = "deleteOrderResponse", targetNamespace = "http://server.bookstore.suormary.beije.it/", className = "it.beije.suormary.bookstore.client.DeleteOrderResponse")
    @Action(input = "http://server.bookstore.suormary.beije.it/Bookstore/deleteOrderRequest", output = "http://server.bookstore.suormary.beije.it/Bookstore/deleteOrderResponse")
    public boolean deleteOrder(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}
