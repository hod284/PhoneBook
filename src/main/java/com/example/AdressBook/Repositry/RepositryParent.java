package com.example.AdressBook.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AdressBook.EntityClass.PhoneNumberBook;

// 레포지트리 폰넘버 폰주인 폰 그룹에 있는  DB 커넥트 클래스
//  JpaRepository<enitity class,pk>
public interface RepositryParent  extends JpaRepository<PhoneNumberBook,Long> {
    
    // jpa 프로젝션 이것도 transient dirty로 데이터 변화체크해서 db에 있는 데이터 수정할때 쓰기위해 db의 데이터를 얻을때 쓰는 프로젝션
    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_owner  = :pname")
    public PhoneNumberBook GetDatabyNameModify(@Param("pname") String pname);
/*  jpa 프로젝션 안쓰는거
    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_number  = :pnumber")
    public PhoneNumberBook GetDatabyNumber(@Param("pnumber") String pnumber);

    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_group  = : phonegroup")
    public List<PhoneNumberBook> GetDatabyGroupaslist(@Param("phonegroup") Integer phonegroup);
    
    @Query("SELECT p.phone_owner FROM PhoneNumberBook p")
    public List<String> GetallPhoneOwner();
        
    @Query("SELECT p.phone_number FROM PhoneNumberBook p")
    public List<String> GetallPhoneNumber();

    @Query("SELECT p.phone_group FROM PhoneNumberBook p")
    public List<Integer> GetallPhoneGroup();
    */
    
}