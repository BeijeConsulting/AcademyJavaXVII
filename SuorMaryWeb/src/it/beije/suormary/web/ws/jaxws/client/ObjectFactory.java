
package it.beije.suormary.web.ws.jaxws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.beije.suormary.web.ws.jaxws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAuthors_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "getAuthors");
    private final static QName _AddAuthorResponse_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "addAuthorResponse");
    private final static QName _AddBookResponse_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "addBookResponse");
    private final static QName _GetBooks_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "getBooks");
    private final static QName _LoginResponse_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "loginResponse");
    private final static QName _GetOrdersResponse_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "getOrdersResponse");
    private final static QName _InstantBuy_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "instantBuy");
    private final static QName _GetBooksResponse_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "getBooksResponse");
    private final static QName _Login_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "login");
    private final static QName _GetOrders_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "getOrders");
    private final static QName _InstantBuyResponse_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "instantBuyResponse");
    private final static QName _Signup_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "signup");
    private final static QName _GetAuthorsResponse_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "getAuthorsResponse");
    private final static QName _AddBook_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "addBook");
    private final static QName _AddAuthor_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "addAuthor");
    private final static QName _SignupResponse_QNAME = new QName("http://server.jaxws.ws.web.suormary.beije.it/", "signupResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.beije.suormary.web.ws.jaxws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOrdersResponse }
     * 
     */
    public GetOrdersResponse createGetOrdersResponse() {
        return new GetOrdersResponse();
    }

    /**
     * Create an instance of {@link GetBooks }
     * 
     */
    public GetBooks createGetBooks() {
        return new GetBooks();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link AddAuthorResponse }
     * 
     */
    public AddAuthorResponse createAddAuthorResponse() {
        return new AddAuthorResponse();
    }

    /**
     * Create an instance of {@link AddBookResponse }
     * 
     */
    public AddBookResponse createAddBookResponse() {
        return new AddBookResponse();
    }

    /**
     * Create an instance of {@link AddAuthor }
     * 
     */
    public AddAuthor createAddAuthor() {
        return new AddAuthor();
    }

    /**
     * Create an instance of {@link SignupResponse }
     * 
     */
    public SignupResponse createSignupResponse() {
        return new SignupResponse();
    }

    /**
     * Create an instance of {@link GetAuthorsResponse }
     * 
     */
    public GetAuthorsResponse createGetAuthorsResponse() {
        return new GetAuthorsResponse();
    }

    /**
     * Create an instance of {@link AddBook }
     * 
     */
    public AddBook createAddBook() {
        return new AddBook();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link GetOrders }
     * 
     */
    public GetOrders createGetOrders() {
        return new GetOrders();
    }

    /**
     * Create an instance of {@link InstantBuyResponse }
     * 
     */
    public InstantBuyResponse createInstantBuyResponse() {
        return new InstantBuyResponse();
    }

    /**
     * Create an instance of {@link Signup }
     * 
     */
    public Signup createSignup() {
        return new Signup();
    }

    /**
     * Create an instance of {@link InstantBuy }
     * 
     */
    public InstantBuy createInstantBuy() {
        return new InstantBuy();
    }

    /**
     * Create an instance of {@link GetBooksResponse }
     * 
     */
    public GetBooksResponse createGetBooksResponse() {
        return new GetBooksResponse();
    }

    /**
     * Create an instance of {@link GetAuthors }
     * 
     */
    public GetAuthors createGetAuthors() {
        return new GetAuthors();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "getAuthors")
    public JAXBElement<GetAuthors> createGetAuthors(GetAuthors value) {
        return new JAXBElement<GetAuthors>(_GetAuthors_QNAME, GetAuthors.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "addAuthorResponse")
    public JAXBElement<AddAuthorResponse> createAddAuthorResponse(AddAuthorResponse value) {
        return new JAXBElement<AddAuthorResponse>(_AddAuthorResponse_QNAME, AddAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "addBookResponse")
    public JAXBElement<AddBookResponse> createAddBookResponse(AddBookResponse value) {
        return new JAXBElement<AddBookResponse>(_AddBookResponse_QNAME, AddBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBooks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "getBooks")
    public JAXBElement<GetBooks> createGetBooks(GetBooks value) {
        return new JAXBElement<GetBooks>(_GetBooks_QNAME, GetBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "getOrdersResponse")
    public JAXBElement<GetOrdersResponse> createGetOrdersResponse(GetOrdersResponse value) {
        return new JAXBElement<GetOrdersResponse>(_GetOrdersResponse_QNAME, GetOrdersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstantBuy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "instantBuy")
    public JAXBElement<InstantBuy> createInstantBuy(InstantBuy value) {
        return new JAXBElement<InstantBuy>(_InstantBuy_QNAME, InstantBuy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBooksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "getBooksResponse")
    public JAXBElement<GetBooksResponse> createGetBooksResponse(GetBooksResponse value) {
        return new JAXBElement<GetBooksResponse>(_GetBooksResponse_QNAME, GetBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "getOrders")
    public JAXBElement<GetOrders> createGetOrders(GetOrders value) {
        return new JAXBElement<GetOrders>(_GetOrders_QNAME, GetOrders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstantBuyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "instantBuyResponse")
    public JAXBElement<InstantBuyResponse> createInstantBuyResponse(InstantBuyResponse value) {
        return new JAXBElement<InstantBuyResponse>(_InstantBuyResponse_QNAME, InstantBuyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Signup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "signup")
    public JAXBElement<Signup> createSignup(Signup value) {
        return new JAXBElement<Signup>(_Signup_QNAME, Signup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "getAuthorsResponse")
    public JAXBElement<GetAuthorsResponse> createGetAuthorsResponse(GetAuthorsResponse value) {
        return new JAXBElement<GetAuthorsResponse>(_GetAuthorsResponse_QNAME, GetAuthorsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "addBook")
    public JAXBElement<AddBook> createAddBook(AddBook value) {
        return new JAXBElement<AddBook>(_AddBook_QNAME, AddBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "addAuthor")
    public JAXBElement<AddAuthor> createAddAuthor(AddAuthor value) {
        return new JAXBElement<AddAuthor>(_AddAuthor_QNAME, AddAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.jaxws.ws.web.suormary.beije.it/", name = "signupResponse")
    public JAXBElement<SignupResponse> createSignupResponse(SignupResponse value) {
        return new JAXBElement<SignupResponse>(_SignupResponse_QNAME, SignupResponse.class, null, value);
    }

}
