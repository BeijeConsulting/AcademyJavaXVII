
package it.beije.suormary.bookstore.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.beije.suormary.bookstore.client package. 
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

    private final static QName _CheckUser_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "checkUser");
    private final static QName _UserExists_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "userExists");
    private final static QName _AddNewBookResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "addNewBookResponse");
    private final static QName _GetOrder_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getOrder");
    private final static QName _UserExistsResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "userExistsResponse");
    private final static QName _DeleteCartResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "deleteCartResponse");
    private final static QName _AddNewBook_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "addNewBook");
    private final static QName _CreateUser_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "createUser");
    private final static QName _EditStatusResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "editStatusResponse");
    private final static QName _GetAuthorList_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getAuthorList");
    private final static QName _GetUserId_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getUserId");
    private final static QName _GetBookResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getBookResponse");
    private final static QName _GetUserIdResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getUserIdResponse");
    private final static QName _DeleteOrderResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "deleteOrderResponse");
    private final static QName _GetBook_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getBook");
    private final static QName _GetCartResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getCartResponse");
    private final static QName _RemoveCartItem_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "removeCartItem");
    private final static QName _AddCartItemResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "addCartItemResponse");
    private final static QName _AddAuthorResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "addAuthorResponse");
    private final static QName _CreateUserResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "createUserResponse");
    private final static QName _CreateOrderResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "createOrderResponse");
    private final static QName _AddAuthor_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "addAuthor");
    private final static QName _GetCart_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getCart");
    private final static QName _GetOrders_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getOrders");
    private final static QName _GetAllBooks_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getAllBooks");
    private final static QName _AddCartItem_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "addCartItem");
    private final static QName _GetAllBooksResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getAllBooksResponse");
    private final static QName _CheckUserResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "checkUserResponse");
    private final static QName _DeleteOrder_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "deleteOrder");
    private final static QName _GetOrderResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getOrderResponse");
    private final static QName _GetOrdersResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getOrdersResponse");
    private final static QName _CreateOrder_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "createOrder");
    private final static QName _GetAuthorListResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "getAuthorListResponse");
    private final static QName _RemoveCartItemResponse_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "removeCartItemResponse");
    private final static QName _EditStatus_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "editStatus");
    private final static QName _DeleteCart_QNAME = new QName("http://server.bookstore.suormary.beije.it/", "deleteCart");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.beije.suormary.bookstore.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateOrder }
     * 
     */
    public CreateOrder createCreateOrder() {
        return new CreateOrder();
    }

    /**
     * Create an instance of {@link GetAuthorListResponse }
     * 
     */
    public GetAuthorListResponse createGetAuthorListResponse() {
        return new GetAuthorListResponse();
    }

    /**
     * Create an instance of {@link GetOrdersResponse }
     * 
     */
    public GetOrdersResponse createGetOrdersResponse() {
        return new GetOrdersResponse();
    }

    /**
     * Create an instance of {@link RemoveCartItemResponse }
     * 
     */
    public RemoveCartItemResponse createRemoveCartItemResponse() {
        return new RemoveCartItemResponse();
    }

    /**
     * Create an instance of {@link EditStatus }
     * 
     */
    public EditStatus createEditStatus() {
        return new EditStatus();
    }

    /**
     * Create an instance of {@link DeleteCart }
     * 
     */
    public DeleteCart createDeleteCart() {
        return new DeleteCart();
    }

    /**
     * Create an instance of {@link GetAllBooks }
     * 
     */
    public GetAllBooks createGetAllBooks() {
        return new GetAllBooks();
    }

    /**
     * Create an instance of {@link AddCartItem }
     * 
     */
    public AddCartItem createAddCartItem() {
        return new AddCartItem();
    }

    /**
     * Create an instance of {@link GetAllBooksResponse }
     * 
     */
    public GetAllBooksResponse createGetAllBooksResponse() {
        return new GetAllBooksResponse();
    }

    /**
     * Create an instance of {@link DeleteOrder }
     * 
     */
    public DeleteOrder createDeleteOrder() {
        return new DeleteOrder();
    }

    /**
     * Create an instance of {@link GetOrderResponse }
     * 
     */
    public GetOrderResponse createGetOrderResponse() {
        return new GetOrderResponse();
    }

    /**
     * Create an instance of {@link CheckUserResponse }
     * 
     */
    public CheckUserResponse createCheckUserResponse() {
        return new CheckUserResponse();
    }

    /**
     * Create an instance of {@link DeleteOrderResponse }
     * 
     */
    public DeleteOrderResponse createDeleteOrderResponse() {
        return new DeleteOrderResponse();
    }

    /**
     * Create an instance of {@link GetBookResponse }
     * 
     */
    public GetBookResponse createGetBookResponse() {
        return new GetBookResponse();
    }

    /**
     * Create an instance of {@link GetUserIdResponse }
     * 
     */
    public GetUserIdResponse createGetUserIdResponse() {
        return new GetUserIdResponse();
    }

    /**
     * Create an instance of {@link GetBook }
     * 
     */
    public GetBook createGetBook() {
        return new GetBook();
    }

    /**
     * Create an instance of {@link GetCartResponse }
     * 
     */
    public GetCartResponse createGetCartResponse() {
        return new GetCartResponse();
    }

    /**
     * Create an instance of {@link RemoveCartItem }
     * 
     */
    public RemoveCartItem createRemoveCartItem() {
        return new RemoveCartItem();
    }

    /**
     * Create an instance of {@link AddAuthorResponse }
     * 
     */
    public AddAuthorResponse createAddAuthorResponse() {
        return new AddAuthorResponse();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link AddCartItemResponse }
     * 
     */
    public AddCartItemResponse createAddCartItemResponse() {
        return new AddCartItemResponse();
    }

    /**
     * Create an instance of {@link CreateOrderResponse }
     * 
     */
    public CreateOrderResponse createCreateOrderResponse() {
        return new CreateOrderResponse();
    }

    /**
     * Create an instance of {@link AddAuthor }
     * 
     */
    public AddAuthor createAddAuthor() {
        return new AddAuthor();
    }

    /**
     * Create an instance of {@link GetCart }
     * 
     */
    public GetCart createGetCart() {
        return new GetCart();
    }

    /**
     * Create an instance of {@link GetOrders }
     * 
     */
    public GetOrders createGetOrders() {
        return new GetOrders();
    }

    /**
     * Create an instance of {@link AddNewBookResponse }
     * 
     */
    public AddNewBookResponse createAddNewBookResponse() {
        return new AddNewBookResponse();
    }

    /**
     * Create an instance of {@link CheckUser }
     * 
     */
    public CheckUser createCheckUser() {
        return new CheckUser();
    }

    /**
     * Create an instance of {@link UserExists }
     * 
     */
    public UserExists createUserExists() {
        return new UserExists();
    }

    /**
     * Create an instance of {@link DeleteCartResponse }
     * 
     */
    public DeleteCartResponse createDeleteCartResponse() {
        return new DeleteCartResponse();
    }

    /**
     * Create an instance of {@link GetOrder }
     * 
     */
    public GetOrder createGetOrder() {
        return new GetOrder();
    }

    /**
     * Create an instance of {@link UserExistsResponse }
     * 
     */
    public UserExistsResponse createUserExistsResponse() {
        return new UserExistsResponse();
    }

    /**
     * Create an instance of {@link AddNewBook }
     * 
     */
    public AddNewBook createAddNewBook() {
        return new AddNewBook();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link EditStatusResponse }
     * 
     */
    public EditStatusResponse createEditStatusResponse() {
        return new EditStatusResponse();
    }

    /**
     * Create an instance of {@link GetUserId }
     * 
     */
    public GetUserId createGetUserId() {
        return new GetUserId();
    }

    /**
     * Create an instance of {@link GetAuthorList }
     * 
     */
    public GetAuthorList createGetAuthorList() {
        return new GetAuthorList();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link CartItem }
     * 
     */
    public CartItem createCartItem() {
        return new CartItem();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link OrderItem }
     * 
     */
    public OrderItem createOrderItem() {
        return new OrderItem();
    }

    /**
     * Create an instance of {@link Author }
     * 
     */
    public Author createAuthor() {
        return new Author();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "checkUser")
    public JAXBElement<CheckUser> createCheckUser(CheckUser value) {
        return new JAXBElement<CheckUser>(_CheckUser_QNAME, CheckUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "userExists")
    public JAXBElement<UserExists> createUserExists(UserExists value) {
        return new JAXBElement<UserExists>(_UserExists_QNAME, UserExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "addNewBookResponse")
    public JAXBElement<AddNewBookResponse> createAddNewBookResponse(AddNewBookResponse value) {
        return new JAXBElement<AddNewBookResponse>(_AddNewBookResponse_QNAME, AddNewBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getOrder")
    public JAXBElement<GetOrder> createGetOrder(GetOrder value) {
        return new JAXBElement<GetOrder>(_GetOrder_QNAME, GetOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserExistsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "userExistsResponse")
    public JAXBElement<UserExistsResponse> createUserExistsResponse(UserExistsResponse value) {
        return new JAXBElement<UserExistsResponse>(_UserExistsResponse_QNAME, UserExistsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "deleteCartResponse")
    public JAXBElement<DeleteCartResponse> createDeleteCartResponse(DeleteCartResponse value) {
        return new JAXBElement<DeleteCartResponse>(_DeleteCartResponse_QNAME, DeleteCartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "addNewBook")
    public JAXBElement<AddNewBook> createAddNewBook(AddNewBook value) {
        return new JAXBElement<AddNewBook>(_AddNewBook_QNAME, AddNewBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "editStatusResponse")
    public JAXBElement<EditStatusResponse> createEditStatusResponse(EditStatusResponse value) {
        return new JAXBElement<EditStatusResponse>(_EditStatusResponse_QNAME, EditStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getAuthorList")
    public JAXBElement<GetAuthorList> createGetAuthorList(GetAuthorList value) {
        return new JAXBElement<GetAuthorList>(_GetAuthorList_QNAME, GetAuthorList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getUserId")
    public JAXBElement<GetUserId> createGetUserId(GetUserId value) {
        return new JAXBElement<GetUserId>(_GetUserId_QNAME, GetUserId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getBookResponse")
    public JAXBElement<GetBookResponse> createGetBookResponse(GetBookResponse value) {
        return new JAXBElement<GetBookResponse>(_GetBookResponse_QNAME, GetBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getUserIdResponse")
    public JAXBElement<GetUserIdResponse> createGetUserIdResponse(GetUserIdResponse value) {
        return new JAXBElement<GetUserIdResponse>(_GetUserIdResponse_QNAME, GetUserIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "deleteOrderResponse")
    public JAXBElement<DeleteOrderResponse> createDeleteOrderResponse(DeleteOrderResponse value) {
        return new JAXBElement<DeleteOrderResponse>(_DeleteOrderResponse_QNAME, DeleteOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getBook")
    public JAXBElement<GetBook> createGetBook(GetBook value) {
        return new JAXBElement<GetBook>(_GetBook_QNAME, GetBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getCartResponse")
    public JAXBElement<GetCartResponse> createGetCartResponse(GetCartResponse value) {
        return new JAXBElement<GetCartResponse>(_GetCartResponse_QNAME, GetCartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveCartItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "removeCartItem")
    public JAXBElement<RemoveCartItem> createRemoveCartItem(RemoveCartItem value) {
        return new JAXBElement<RemoveCartItem>(_RemoveCartItem_QNAME, RemoveCartItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCartItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "addCartItemResponse")
    public JAXBElement<AddCartItemResponse> createAddCartItemResponse(AddCartItemResponse value) {
        return new JAXBElement<AddCartItemResponse>(_AddCartItemResponse_QNAME, AddCartItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "addAuthorResponse")
    public JAXBElement<AddAuthorResponse> createAddAuthorResponse(AddAuthorResponse value) {
        return new JAXBElement<AddAuthorResponse>(_AddAuthorResponse_QNAME, AddAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "createOrderResponse")
    public JAXBElement<CreateOrderResponse> createCreateOrderResponse(CreateOrderResponse value) {
        return new JAXBElement<CreateOrderResponse>(_CreateOrderResponse_QNAME, CreateOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "addAuthor")
    public JAXBElement<AddAuthor> createAddAuthor(AddAuthor value) {
        return new JAXBElement<AddAuthor>(_AddAuthor_QNAME, AddAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getCart")
    public JAXBElement<GetCart> createGetCart(GetCart value) {
        return new JAXBElement<GetCart>(_GetCart_QNAME, GetCart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getOrders")
    public JAXBElement<GetOrders> createGetOrders(GetOrders value) {
        return new JAXBElement<GetOrders>(_GetOrders_QNAME, GetOrders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBooks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getAllBooks")
    public JAXBElement<GetAllBooks> createGetAllBooks(GetAllBooks value) {
        return new JAXBElement<GetAllBooks>(_GetAllBooks_QNAME, GetAllBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCartItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "addCartItem")
    public JAXBElement<AddCartItem> createAddCartItem(AddCartItem value) {
        return new JAXBElement<AddCartItem>(_AddCartItem_QNAME, AddCartItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBooksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getAllBooksResponse")
    public JAXBElement<GetAllBooksResponse> createGetAllBooksResponse(GetAllBooksResponse value) {
        return new JAXBElement<GetAllBooksResponse>(_GetAllBooksResponse_QNAME, GetAllBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "checkUserResponse")
    public JAXBElement<CheckUserResponse> createCheckUserResponse(CheckUserResponse value) {
        return new JAXBElement<CheckUserResponse>(_CheckUserResponse_QNAME, CheckUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "deleteOrder")
    public JAXBElement<DeleteOrder> createDeleteOrder(DeleteOrder value) {
        return new JAXBElement<DeleteOrder>(_DeleteOrder_QNAME, DeleteOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getOrderResponse")
    public JAXBElement<GetOrderResponse> createGetOrderResponse(GetOrderResponse value) {
        return new JAXBElement<GetOrderResponse>(_GetOrderResponse_QNAME, GetOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getOrdersResponse")
    public JAXBElement<GetOrdersResponse> createGetOrdersResponse(GetOrdersResponse value) {
        return new JAXBElement<GetOrdersResponse>(_GetOrdersResponse_QNAME, GetOrdersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "createOrder")
    public JAXBElement<CreateOrder> createCreateOrder(CreateOrder value) {
        return new JAXBElement<CreateOrder>(_CreateOrder_QNAME, CreateOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "getAuthorListResponse")
    public JAXBElement<GetAuthorListResponse> createGetAuthorListResponse(GetAuthorListResponse value) {
        return new JAXBElement<GetAuthorListResponse>(_GetAuthorListResponse_QNAME, GetAuthorListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveCartItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "removeCartItemResponse")
    public JAXBElement<RemoveCartItemResponse> createRemoveCartItemResponse(RemoveCartItemResponse value) {
        return new JAXBElement<RemoveCartItemResponse>(_RemoveCartItemResponse_QNAME, RemoveCartItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "editStatus")
    public JAXBElement<EditStatus> createEditStatus(EditStatus value) {
        return new JAXBElement<EditStatus>(_EditStatus_QNAME, EditStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.bookstore.suormary.beije.it/", name = "deleteCart")
    public JAXBElement<DeleteCart> createDeleteCart(DeleteCart value) {
        return new JAXBElement<DeleteCart>(_DeleteCart_QNAME, DeleteCart.class, null, value);
    }

}
