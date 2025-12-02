package com.example.AdressBook;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@NoArgsConstructor(access =  AccessLevel.PUBLIC)
public class BookService {
    
      private  RepositryChild ChildRepo;
      private  RepositryParent ParentRepo;
      
      public BookService(  RepositryChild CRepo , RepositryParent PRepo)
      { 
          ChildRepo =CRepo;
          ParentRepo =PRepo;  
      }

}
