package com.example.AdressBook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/api")
@RestController
public class BookController {
    private BookService BService;
     public BookController( BookService bService)
     {
       BService =bService;
     }

}