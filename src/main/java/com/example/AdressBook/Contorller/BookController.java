package com.example.AdressBook.Contorller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdressBook.Service.BookService;

//웹과 백엔드 서버 통신을 위한 백엔드 초기 진입점 
@RequestMapping("/api")
@RestController
public class BookController {
    private BookService BService;
     public BookController( BookService bService)
     {
       BService =bService;
     }
     

}