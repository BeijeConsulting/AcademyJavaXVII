package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.beije.suormary.model.Author;
import it.beije.suormary.service.AuthorService;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(value="/authors")
    public List<Author> listAuthors() {
        return authorService.getAllAuthors();
    }


    @GetMapping(value="/authors/{id}")
    public Author author(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping(value ="/authors")
    public Author insertAuthor(@RequestBody Author author) {
        return authorService.insertAuthor(author);
    }

    @DeleteMapping(value="/authors/{id}") 
    public Map<String, String> deleteAuthor(@PathVariable Integer id) {
        Map<String, String> message = new HashMap<String, String>();

        try {
            authorService.deleteAuthorById(id);
            message.put("message", "autore rimosso correttamente");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }

        return message;
    }


    @PutMapping(value = "/authors/{id}")
    public Author updateauthor(@PathVariable Integer id, @RequestBody Author author) {

        if (id.compareTo(author.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");

        return authorService.updateAuthor(author);
    }



}
