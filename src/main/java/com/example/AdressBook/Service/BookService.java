package com.example.AdressBook.Service;

import org.springframework.stereotype.Service;

import com.example.AdressBook.Dto.ResponseChildDto;
import com.example.AdressBook.Dto.ResponseParentDto;
import com.example.AdressBook.Entity.PhoneNumberBook;
import com.example.AdressBook.Entity.PhoneNumberHistory;
import com.example.AdressBook.Repositry.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;


// 컨트롤러와 레포지트를 연결하는 중간 클래스
@Service
@Slf4j
public class BookService {
    
      private RepositryChild    ChildRepo;
      private RepositryParent  ParentRepo;

      public BookService(  RepositryChild CRepo , RepositryParent PRepo)
      { 
          ChildRepo =CRepo;
          ParentRepo =PRepo;  
      }
      // db데이터 업데이트 
      @Transactional
      public void SavePhoneNumber(ResponseParentDto rp)
      {
          
      } 
      //db 데이터 수정
      @Transactional
      public void ModifyPhoneumber(ResponseParentDto rp)
      {
         
         
      } 
      // db데이터 읽어오기
      @Transactional(readOnly = true)
      public  ResponseParentDto GetResponseParentDtobyPhoneName(String Name)
      {
        PhoneNumberBook  PB = new PhoneNumberBook();
        
        return new ResponseParentDto();
      }

      @Transactional
      public void Called(ResponseChildDto rc)
      {
        
   
         log.info("called");
      }
      

}
