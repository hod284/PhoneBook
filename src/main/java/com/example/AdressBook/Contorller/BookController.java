package com.example.AdressBook.Contorller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.AdressBook.Dto.ResponseChildDto;
import com.example.AdressBook.Dto.ResponseDto;
import com.example.AdressBook.Dto.ResponseParentDto;
import com.example.AdressBook.Service.BookService;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




//웹과 백엔드 서버 통신을 위한 백엔드 초기 진입점 
@RequestMapping("/api")
@RestController
@Slf4j
public class BookController {
    private BookService BService;
    private JdbcTemplate jdbcTemplate;
     public BookController( BookService bService,JdbcTemplate JTemplate)
     {
       BService =bService; 
       jdbcTemplate = JTemplate;
     }
     @GetMapping("/Column_Owner")
     public ResponseEntity<List<String>> getOwnerList() 
     { 
        List<String> re = BService.AllPhoneOwner();
        log.info(re.toString());
        if(re.get(0) == null || re.get(0).isBlank())
        {
             return ResponseEntity.notFound().build();
         }
        return  ResponseEntity.ok(re); 
     }
     @GetMapping("/Column_Number")
     public  ResponseEntity<List<String>> getNumberList() 
     {
        List<String> re = BService.AllPhoneNumber();
         log.info(re.toString());
        if(re.get(0) == null || re.get(0).isBlank())
        {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(re);
     }
     @GetMapping("/Column_PhoneGroup")
     public  ResponseEntity<List<Integer>> getPhoneGroupList() 
     {
        List<Integer> re = BService.AllPhoneGroup();
         log.info(re.toString());
        if(re.get(0) == null || re.get(0)==0)
        {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(re);
     }
     @GetMapping("/Column_datetime")
     public ResponseEntity<List<LocalDate>> getdatetimeList() 
     {
        List<LocalDate> re = BService.Alltdatetime();
         log.info(re.toString());
        if(re.get(0) == null)
        {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(re);
     }
     @GetMapping("/Column_callingorgetting")
     public ResponseEntity<List<Integer>> getcallingorgettinglist() 
     {
        var re = BService.Allcallingorgetting();
       log.info(re.toString());
        if(re.get(0) == null || re.get(0)==0)
        {
             return ResponseEntity.notFound().build();
         }
          return ResponseEntity.ok(re);  
     }
     @GetMapping("/ow/{ownner}")
     public ResponseEntity<ResponseParentDto> getphoneBookbyowner(@PathVariable String ownner ) 
     {
        var re =  BService.GetResponseParentDtobyPhoneName(ownner);
         log.info(re.toString());
         if(re.Name() == null || re.Name().isBlank()){
             return ResponseEntity.notFound().build();
         }
          return ResponseEntity.ok(re);   
      }
      @GetMapping("/how/{ownner}")
     public ResponseEntity<List<ResponseDto>> getphonecallinghistory(@PathVariable String ownner ) 
     {
        List<ResponseDto> re = new ArrayList<ResponseDto>();
         var li =  BService.ListCallinghistorybyName(ownner);
          log.info(li.toString());
          if(li.size() ==0)
          {
             return ResponseEntity.notFound().build();
          }
         for(int i =0; i<li.size(); i++)
         {
              re.add(new ResponseDto
                (li.get(i).getPhonebook().getPhone_owner(),
                 li.get(i).getPhonebook().getPhone_number(),
                 li.get(i).getPhonebook().getPhone_group(),
                 li.get(i).getPhone_datetime(),
                 li.get(i).getPhone_callingorgetting()
                ));
         }
         return ResponseEntity.ok(re);   
      }
      @GetMapping("/da/{date}")
     public ResponseEntity<List<ResponseDto>> getphonecallinghistorybydate(@PathVariable String date ) 
     {
        List<ResponseDto> re = new ArrayList<ResponseDto>();
         var li =  BService.ListCallinghistoryByDate(date);
           log.info(li.toString());
          if(li.size() ==0)
          {
             return ResponseEntity.notFound().build();
          }
         for(int i =0; i<li.size(); i++)
         {
              re.add(new ResponseDto
                (li.get(i).getPhonebook().getPhone_owner(),
                 li.get(i).getPhonebook().getPhone_number(),
                 li.get(i).getPhonebook().getPhone_group(),
                 li.get(i).getPhone_datetime(),
                 li.get(i).getPhone_callingorgetting()
                ));
         }
         return ResponseEntity.ok(re);   
      }
      @GetMapping("/nu/{number}")
      public ResponseEntity<ResponseParentDto> getphoneBookbynumber(@PathVariable String number ) 
      {
        var re =  BService.GetResponseParentDtobyPhoneNumber(number);
        log.info(re.toString());
         if(re.Name() == null || re.Name().isBlank()){
             return ResponseEntity.notFound().build();
         }
          return ResponseEntity.ok(re);   
      }
      @PostMapping("/addthnumber")     
      public ResponseEntity<ResponseParentDto> AddthePhonNumber(@RequestBody ResponseParentDto  dto) 
      {
           log.info("AddthePhonNumber");
         BService.SavePhoneNumber(new ResponseParentDto(dto.Name(),dto.Number(),dto.Group()));
         return ResponseEntity.ok().build();
      }
      @PostMapping("/called")     
      public ResponseEntity<ResponseParentDto> Phoneacalled(@RequestBody ResponseChildDto  dto) 
      {
        String qurey = "SELECT EXISTS(SELECT 1 FROM PhoneNumberBook WHERE phone_owner =?)";
        Boolean exits =  jdbcTemplate.queryForObject(qurey, Boolean.class, dto.CallingName());
        log.info(exits.toString());
        if( exits)
        {
         BService.Called(new ResponseChildDto(dto.CallingName(),dto.CallingorGetting()));
         return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
      }
       @PatchMapping("/changephonbookinfo")     
      public ResponseEntity<ResponseParentDto> PhonebookModify(@RequestBody ResponseParentDto  dto) 
      {
          log.info("PhonebookModify");
          BService.ModifyPhoneumber(dto);
           return ResponseEntity.ok().build();
      }
        @DeleteMapping("/init")     
      public ResponseEntity<ResponseParentDto> PhonebookReset() 
      {
          log.info("PhonebookReset");
          BService.Deletall();
           return ResponseEntity.ok().build();
      }
        @PatchMapping("/onedelete")     
      public ResponseEntity<ResponseParentDto> PhonebookDeletOne(@RequestBody ResponseParentDto  dto) 
      {
          log.info("PhonebookModify");
          BService.Delet(dto);
           return ResponseEntity.ok().build();
      }
       @GetMapping("/gr/{group}")
      public ResponseEntity<List<ResponseParentDto>> getPhonebookbookbygroup(@PathVariable int group ) 
      {
          log.info("getPhonebookbookbygroup");
          var re = BService.GetResponseParentDtobyPhoneGroup(group);
           log.info(re.toString());
          if(re.size() ==0)
          {
             return ResponseEntity.notFound().build();
          }
           return ResponseEntity.ok(re);
      }


}