package com.example.AdressBook.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.example.AdressBook.Dto.ResponseChildDto;
import com.example.AdressBook.Dto.ResponseParentDto;
import com.example.AdressBook.EntityClass.*;
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
      // db데이터 업데이트 PHONEBOOK
      @Transactional
      public void SavePhoneNumber(ResponseParentDto rp)
      {
          log.info("SavePhoneNumber :" + rp.toString());
          PhoneNumberBook PB =  new PhoneNumberBook();
          PB.setPhone_owner(rp.Name());
          PB.setPhone_number(rp.Number());
          PB.setPhone_group(rp.Group());
          PB.setHistories(new ArrayList<PhoneNumberHistory>());
          ParentRepo.save(PB);
      } 
      //db 데이터 수정 PHONEBOOK 
      @Transactional
      public void ModifyPhoneumber(ResponseParentDto rp)
      {
         log.info("ModifyPhoneumber :" + rp.toString());
         PhoneNumberBook PB =  ParentRepo.GetDatabyName(rp.Name());
         PB.setPhone_owner(rp.Name());
         PB.setPhone_number(rp.Number());
         PB.setPhone_group(rp.Group());
      } 
        //db 데이터 리셋 PHONEBOOK 
      @Transactional
      public void Deletall()
      {
         log.info("Deletall");
          ParentRepo.deleteAll();
      } 
          //db 데이터 삭제 PHONEBOOK 
      @Transactional
      public void Delet(ResponseParentDto rp)
      {
         log.info("Delete");
         PhoneNumberBook PB =  ParentRepo.GetDatabyName(rp.Name());
          ParentRepo.delete(PB);
      } 
      // db데이터 읽어오기 PHONEBOOK 이름으로
      @Transactional(readOnly = true)
      public  ResponseParentDto GetResponseParentDtobyPhoneName(String Name)
      {
          log.info("GetResponseParentDtobyPhoneName");
          PhoneNumberBook  PB = ParentRepo.GetDatabyName(Name);  
          return new ResponseParentDto(PB.getPhone_owner(),PB.getPhone_number(),PB.getPhone_group());
      }
       // db데이터 읽어오기 PHONEBOOK 넘버로
      @Transactional(readOnly = true)
      public  ResponseParentDto GetResponseParentDtobyPhoneNumber(String Number)
      {
         log.info("GetResponseParentDtobyPhoneName");
        PhoneNumberBook  PB = ParentRepo.GetDatabyNumber(Number);  
         return new ResponseParentDto(PB.getPhone_owner(),PB.getPhone_number(),PB.getPhone_group());
      }
       // db데이터 읽어오기 PHONEBOOK 그룹으로
      @Transactional(readOnly = true)
      public List<ResponseParentDto> GetResponseParentDtobyPhoneGroup(int group)
      {
             log.info("GetResponseParentDtobyPhoneGroup");
        List<PhoneNumberBook>  PB =  ParentRepo.GetDatabyGroupaslist(group);  
        List<ResponseParentDto> dto = new ArrayList<ResponseParentDto>();  
        for(int i =0; i< PB.size(); i++)
        {
          dto.add(new ResponseParentDto
            (PB.get(i).getPhone_owner(),PB.get(i).getPhone_number(),PB.get(i).getPhone_group()));
        }
        return dto;
      }
      // 오너 컬럼 다불러오기
      @Transactional(readOnly = true)
      public List<String> AllPhoneOwner()
      {
        log.info("AllPhoneOwner");
            return  ParentRepo.GetallPhoneOwner();
      }
      // 넘버 컬럼 다불러오기
       @Transactional(readOnly = true)
      public List<String> AllPhoneNumber()
      {
        log.info("AllPhoneNumber");
            return  ParentRepo.GetallPhoneNumber();
      } 
      // 그룹컬럼 다불러오기
       @Transactional(readOnly = true)
      public List<Integer> AllPhoneGroup()
      {
        log.info("AllPhoneGroup");
        return ParentRepo.GetallPhoneGroup();
      }
      // db데이터 업데이트  PhoneNumberHistory
      @Transactional
      public void Called(ResponseChildDto rc)
      {        
         log.info("called");
         PhoneNumberBook  PB = ParentRepo.GetDatabyName(rc.CallingName());
         PhoneNumberHistory PC =  new PhoneNumberHistory();
         PC.setPhonebook(PB);
         PC.setPhone_datetime(LocalDate.now());
         PC.setPhone_callingorgetting(rc.CallingorGetting());
         var li = PB.getHistories();
         li.add(PC);
         PB.setHistories(li);
         ChildRepo.save(PC); 
      }
       // db데이터 수정  PhoneNumberHistory
      @Transactional
      public void ModifyCallingHistory(ResponseChildDto rc,String date, Integer modify_callingorgetter)
      {        
         log.info("ModifyCallingHistory");
         PhoneNumberHistory PC =ChildRepo.GetDatabyNameasModify(rc.CallingName(), rc.CallingorGetting(),date);
         PC.setPhone_callingorgetting(modify_callingorgetter);
         PC.setPhone_datetime(LocalDate.now());
      }
         // db데이터 읽어오기  PhoneNumberHistory name
      @Transactional(readOnly = true)
      public List<PhoneNumberHistory> ListCallinghistorybyName(String name)
      {
        log.info("istCallinghistorybyName");
         return  ChildRepo.GetDatabyNameaslist(name);
      }
         // db데이터 읽어오기  PhoneNumberHistory date
      @Transactional(readOnly =  true)
      public  List<PhoneNumberHistory> ListCallinghistoryByDate(String pdate)
      {
        log.info("ListCallinghistoryByDate");
       return  ChildRepo.GetDatabyYearandMonth(pdate);
      }
         // db데이터 읽어오기  PhoneNumberHistory  callingorgettring
      @Transactional(readOnly =  true)
      public  List<PhoneNumberHistory> ListCallinghistoryBy( Integer callingorgetting)
      {
        log.info("ListCallinghistoryBy");
       return  ChildRepo.GetDatabyGroupaslist(callingorgetting);
      }
      // 통화한 시간 컬럼가져오기
     @Transactional(readOnly = true)
      public List<LocalDate> Alltdatetime()
      {
        log.info("Alldatetime");
            return   ChildRepo.Getalldatetime();
      }
      // 전화를 받았는지 안받았는지 확인 하는 컬럼 다들고오기
       @Transactional(readOnly = true)
      public List<Integer> Allcallingorgetting()
      {
        log.info("Allcallingorgetting");
            return ChildRepo.Getallcallingorgetting();
      } 
     

}
