package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transaction;



public class EcommerceManager {

    private EntityManager em;
    //private EntityTransaction transaction;
    
    public User isUser(String email, String password) {
    	em = JPAEntityFactory.openEntity();
       //transaction = em.getTransaction();
        //transaction.begin();
        
        //Query query = em.createQuery("SELECT u from User as u");
    	Query query = em.createQuery("SELECT u from User as u WHERE u.email = :email AND password = :password");

    	query.setParameter("email", email);
    	query.setParameter("password", password);

    	
    	List<User> users = query.getResultList();
    	
    	//for (User u : users) System.out.println(u);
    	if (users.size() == 0) return null;
    	
    	//transaction.commit();
    	em.close();
    	//System.out.println("USER:" + users.get(0));
    	return users.get(0);
    }
    
    public User insertUser(String name, String surname, String email, String password) {
    	em = JPAEntityFactory.openEntity();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
//        Query<User> query = session.createQuery("SELECT u FROM User as u "
//				  + "WHERE email=:email");
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
    	user.setPassword(password);
    	user.setCreationDate(LocalDateTime.now());
    	
    	try {
    		em.persist(user);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    		user = null;
    	} finally {
        	em.close();    		
    	}
    
    	return user;
    }
    
    public List<Book> listBook(){
    	em = JPAEntityFactory.openEntity();
    	Query query = em.createQuery("SELECT b from Book as b order by b.title");
    	List<Book> books = query.getResultList();
    	if (books.size() == 0) return null;
    	em.close();
    	return books;
    }
    
    public List<Author> listAuthor(){
	   	em = JPAEntityFactory.openEntity();
       //transaction = em.getTransaction();
        //transaction.begin();

    	Query query = em.createQuery("SELECT a from Author as a order by a.surname");
    	List<Author> authors = query.getResultList();
    	if (authors.size() == 0) return null;
    	
    	//transaction.commit();
    	em.close();
    	return authors;
	}
        
    public Author addAuthor(String name, String surname, String description) {
    	Author author = new Author();
    	
    	author.setName(name);
    	author.setSurname(surname);
    	author.setDescription(description);
    	
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	try {
    		em.persist(author);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    		author = null;
    	} finally {
        	em.close();    		
    	}
    	return author;
    	
    }
    
    public Book addBook(String title, String description, int idAuthor, String editor, double price, int quantity) {
    	Book book = new Book();
    	
    	book.setTitle(title);
    	
    	book.setAuthorId(idAuthor);
    	book.setDescription(description);
    	book.setEditor(editor);
    	book.setPrice(price);
    	book.setQuantity(quantity);
    	
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	try {
    		em.persist(book);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    		book = null;
    	} finally {
        	em.close();    		
    	}
    	return book;
    }

    public void updateBook(int bookId, double price, int quantity) {
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	Book book = em.find(Book.class, bookId);
   
    	book.setPrice(price);
    	book.setQuantity(quantity);
    	
    	try {
    		em.persist(book);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    	} finally {
        	em.close();    		
    	}
    }
    
    public void updateAuthor(int authorId, String description) {
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	Author author = em.find(Author.class, authorId);
   
    	author.setDescription(description);
    	
    	try {
    		em.persist(author);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    	} finally {
        	em.close();    		
    	}
    }

    public HashMap<Book, Integer> basket(int userId){
    	HashMap<Book, Integer> basket = new HashMap<Book, Integer>();
    	em = JPAEntityFactory.openEntity();
    	
//    	List<Object[]> results = em.createQuery("SELECT p.firstName, p.lastName, n.phoneNumber "
//    			+ "FROM Person p JOIN PhoneBookEntry n "
//    			+ "ON p.firstName = n.firstName AND p.lastName = n.lastName")
//    			.getResultList();
//    	 
//    	for (Object[] result : results) {
//    	    log.info(result[0] + " " + result[1] + " - " + result[2]);
//    	}
    	
     	Query query = em.createQuery("SELECT bi FROM BasketItem as bi WHERE bi.userId = :userId");
     	query.setParameter("userId", userId);

     	List<BasketItem> basketItems = query.getResultList();
     	
     	for (BasketItem bi : basketItems) {
     		Book book = em.find(Book.class, bi.getBookId());
     		int quantity = bi.getQuantity();
     		if (quantity <= book.getQuantity()) {
     			basket.put(book, quantity);
     		}
     		else {
     			basket.put(book, book.getQuantity());
     		}
     		
     	}

     	em.close();
     	return basket;
    }
    
    public void addToBasket(int bookId, int userId) {
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	Query query = em.createQuery("SELECT bi FROM BasketItem as bi "
    			+ "WHERE bi.userId = :userId "
    			+ "AND bi.bookId = :bookId");
    	query.setParameter("userId", userId);
    	query.setParameter("bookId", bookId);
    	
    	List<BasketItem> basketItems = query.getResultList();
    	BasketItem bi = new BasketItem();
    	
    	if (basketItems.size() == 0) {
    		bi = new BasketItem();
    		bi.setBookId(bookId);
    		bi.setUserId(userId);
    		bi.setQuantity(1);
    		em.persist(bi);
    		transaction.commit();
    		
    	}
    	else {
    		int itemId = basketItems.get(0).getId(); 
    		bi = em.find(BasketItem.class, itemId);
    		int quantity = bi.getQuantity();
    		Book book = em.find(Book.class, bookId);
    		if(quantity <book.getQuantity()) {
    			bi.setQuantity(quantity + 1);
    		}else {
    			quantity = book.getQuantity();
    			bi.setQuantity(quantity);
    		}
    		
    		em.persist(bi);
    		transaction.commit();
    			
    	}
    	
    }
    
    public double getBasketAmount(int userId) {
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	Query query = em.createQuery("SELECT SUM(book.price*bi.quantity) FROM Book as book "
    			+ "JOIN BasketItem as bi "
    			+ "ON book.id = bi.bookId "
    			+ "WHERE bi.userId = :userId");
    	query.setParameter("userId", userId);
    	
    	List<Double> amount = query.getResultList();
    	
    	if (amount.get(0) == null) return 0.0;
    	System.out.println("AMOUNT: " + amount.get(0));
    	double basketAmount = (Double) (amount.get(0));
    	return basketAmount;
    }
    
    public void buy(int userId, String shippingAddress, String paymentType) {
    	
    	Order order = new Order();
    	order.setDate(LocalDateTime.now());
    	order.setUserId(userId);
    	order.setStatus(paymentType.equals("cash") ? "I" : "P");
    	order.setAmount(getBasketAmount(userId));
    	order.setShippingAddress(shippingAddress);
    	order.setItems(new ArrayList<OrderItem>());
    	
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	em.persist(order);
    	transaction.commit();
    	em.close();
//    	Query query = em.createQuery("SELECT order from Order as order WHERE order.date= :date AND order.userId = :userId");
//    	query.setParameter("date", order.getDate());
//    	query.setParameter("userId", userId);
//    	int orderId = ((Order) query.getResultList().get(0)).getId();
    	
    	int orderId = order.getId();
    	System.out.println(orderId);
    	
    	HashMap<Book, Integer> basket =  basket(userId);
    	
    	// hashMap book and quantity into basket
    	for (HashMap.Entry<Book, Integer> set : basket.entrySet()){  
    		OrderItem oi = new OrderItem();
    		oi.setOrderId(orderId);  		
    		int bookId = set.getKey().getId();
    		oi.setBookId(bookId);
    		
    		double price = set.getKey().getPrice();
    		oi.setPrice(price);
    		
    		int bookQuantity = set.getValue();
    		oi.setQuantity(bookQuantity);
    		
    		em = JPAEntityFactory.openEntity();
        	transaction = em.getTransaction();
    		transaction.begin();
    		em.persist(oi);
    		transaction.commit();
    		em.close();
    		//insert into list of Items in Order
    		order.getItems().add(oi);
    		
    		//update db book quantity
    		em = JPAEntityFactory.openEntity();
    		Book book = em.find(Book.class, bookId);
    		int newQ = book.getQuantity() - bookQuantity;
    		book.setQuantity(newQ);
    		
    		transaction = em.getTransaction();
    		transaction.begin();
    		em.persist(book);
    		transaction.commit();
    		em.close();
    		
    		// delete all items into basket
    		em = JPAEntityFactory.openEntity();
        	transaction = em.getTransaction();
    		transaction.begin();
    		Query query = em.createQuery("DELETE from BasketItem as bi WHERE bi.userId= :userId");
        	query.setParameter("userId", userId);
        	query.executeUpdate();
        	transaction.commit();
        	em.close();
    	}
    
    }
    
    //return all orders of user
    public ArrayList<Order> userOrders(int userId) {
    	em = JPAEntityFactory.openEntity();
    	
    	Query query = em.createQuery("SELECT o from Order as o WHERE o.userId= :userId");
    	query.setParameter("userId", userId);
    	ArrayList<Order> listOrders = (ArrayList<Order>) query.getResultList();
    	
    	em.close();
    	return listOrders;
    	
    }
    
    
/*    public Order basket(int userId){
    	em = JPAEntityFactory.openEntity();
     	Query query = em.createQuery("SELECT o from Order as o WHERE o.userId = :userId AND o.status = :status");

     	query.setParameter("userId", userId);
     	query.setParameter("status", 'B');

     	List<Order> orders = query.getResultList();
     	if (orders.size() == 0) return null;
     	em.close();

     	return orders.get(0);
    }

   
   public void addToBasket(int bookId, int userId) {
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	Book book = em.find(Book.class, bookId);
    	transaction.begin();
    	
    	Order basket = basket(userId);

     	if (basket == null) {
     		
     	}
     	else {
     		int basketId = basket.getId();
         	Query query = em.createQuery("SELECT oi from OrderItem as oi"
         								+ "WHERE oi.orderId = :basketId "
         								+ "AND oi.bookId = :bookId "
         								+ "AND oi.price = :price");
         	query.setParameter("orderId", basketId);
         	query.setParameter("bookId", bookId);
         	query.setParameter("price", book.getPrice());
         	
         	List<OrderItem> orderItems = query.getResultList();
         	OrderItem orderItem = new OrderItem();
         	
         	if (orderItems.size() == 0) {
         		orderItem.setBookId(bookId);
         		orderItem.setOrderId(basketId);
         		orderItem.setPrice(book.getPrice());
         		orderItem.setQuantity(1);
         		
         		transaction.commit();
         		em.persist(orderItem);
         	}
         	else {
         		orderItem = orderItems.get(0);
         		int newQuantity = orderItems.get(0).getQuantity() + 1;
         		orderItem.setQuantity(newQuantity);
         		transaction.commit();
         		em.persist(orderItem);
         	}
     	}
     	em.close();

     	return ;
    }
    
//    public List<Book> itemToBook(List<OrderItem> orderItem){
//    	HashMap<Book, Integer> book = null;
//    	for(OrderItem oi : orderItem) {
//    		if(book.containsKey(oi))
//    	}
//    	
//    }
//    
//    
//    public Book itemToBook(OrderItem oi) {
//    	int idOrderItem = oi.getBookId();
//    }
    
*/
    
    
}















