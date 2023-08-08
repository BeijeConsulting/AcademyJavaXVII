package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Book;
import it.beije.suormary.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Integer id) {

        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            return book.get();
        }
        return null;
    }

    public Book insertBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);

    }

    public List<Book> findBookFromAuthorId(Integer authorId) {
        return bookRepository.findByAuthorId(authorId);
    }


    public Book updateBook(Book book) {

        Optional<Book> b = bookRepository.findById(book.getId());
        System.out.println(b.get());

        if (!b.isPresent()) throw new RuntimeException("ID ERRATO!!!");

        Book b2 = b.get();
        System.out.println("updated book : " + b2);


        BeanUtils.copyProperties(book, b2);


        bookRepository.save(b2);

        System.out.println("updated book : " + b2);

        return book;
    }

}