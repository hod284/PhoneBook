package com.example.AdressBook.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.AdressBook.Mapperclass.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.example.AdressBook.Dto.ResponseChildDto;
import com.example.AdressBook.Dto.ResponseDto;
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
      private ParentMapper  PMapper; 
      private ChildMapper  CMapper; 

      public BookService(  RepositryChild CRepo , RepositryParent PRepo,ParentMapper pmapper,ChildMapper cmapper)
      { 
          ChildRepo =CRepo;
          ParentRepo =PRepo;  
          PMapper = pmapper;
          CMapper = cmapper;
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
         PhoneNumberBook PB =  ParentRepo.GetDatabyNameModify(rp.Name());
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
         PhoneNumberBook PB =  ParentRepo.GetDatabyNameModify(rp.Name());
          ParentRepo.delete(PB);
      } 
      // db데이터 읽어오기 PHONEBOOK 이름으로
      @Transactional(readOnly = true)
      public  ResponseParentDto GetResponseParentDtobyPhoneName(String Name)
      {
          log.info("GetResponseParentDtobyPhoneName");
          return PMapper.GetDatabyName(Name);
      }
       // db데이터 읽어오기 PHONEBOOK 넘버로
      @Transactional(readOnly = true)
      public  ResponseParentDto GetResponseParentDtobyPhoneNumber(String Number)
      {
         log.info("GetResponseParentDtobyPhoneName");
         return PMapper.GetDatabyNumber(Number); 
      }
       // db데이터 읽어오기 PHONEBOOK 그룹으로
      @Transactional(readOnly = true)
      public List<ResponseParentDto> GetResponseParentDtobyPhoneGroup(int group)
      {
             log.info("GetResponseParentDtobyPhoneGroup");
           return PMapper.GetDatabyGroupaslist(group);
      }
      // 오너 컬럼 다불러오기
      @Transactional(readOnly = true)
      public List<String> AllPhoneOwner()
      {
        log.info("AllPhoneOwner");
            return  PMapper.GetallPhoneOwner();
      }
      // 넘버 컬럼 다불러오기
       @Transactional(readOnly = true)
      public List<String> AllPhoneNumber()
      {
        log.info("AllPhoneNumber");
            return   PMapper.GetallPhoneNumber();
      } 
      // 그룹컬럼 다불러오기
       @Transactional(readOnly = true)
      public List<Integer> AllPhoneGroup()
      {
        log.info("AllPhoneGroup");
        return PMapper.GetallPhoneGroup();
      }
      // db데이터 업데이트  PhoneNumberHistory
      @Transactional
      public void Called(ResponseChildDto rc)
      {        
         log.info("called");
         PhoneNumberBook  PB = ParentRepo.GetDatabyNameModify(rc.CallingName());
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
      public List<ResponseDto> ListCallinghistorybyName(String name)
      {
        log.info("istCallinghistorybyName");
         return  CMapper.GetDatabyNameaslist(name);
      }
         // db데이터 읽어오기  PhoneNumberHistory date
      @Transactional(readOnly =  true)
      public  List<ResponseDto> ListCallinghistoryByDate(String pdate)
      {
        log.info("ListCallinghistoryByDate");
       return  CMapper.GetDatabyYearandMonth(pdate);
      }
         // db데이터 읽어오기  PhoneNumberHistory  callingorgettring
      @Transactional(readOnly =  true)
      public  List<ResponseDto> ListCallinghistoryBy( Integer callingorgetting)
      {
        log.info("ListCallinghistoryBy");
       return  CMapper.GetDatabyGroupaslist(callingorgetting);
      }
      // 통화한 시간 컬럼가져오기
     @Transactional(readOnly = true)
      public List<LocalDate> Alltdatetime()
      {
        log.info("Alldatetime");
            return   CMapper.Getalldatetime();
      }
      // 전화를 받았는지 안받았는지 확인 하는 컬럼 다들고오기
       @Transactional(readOnly = true)
      public List<Integer> Allcallingorgetting()
      {
        log.info("Allcallingorgetting");
            return CMapper.Getallcallingorgetting();
      } 
     

}
