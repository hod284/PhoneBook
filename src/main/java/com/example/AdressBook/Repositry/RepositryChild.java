package com.example.AdressBook.Repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AdressBook.Entity.PhoneNumberBook;
import com.example.AdressBook.Entity.PhoneNumberHistory;
// 레포지트리 폰 주인을  전화를 건시간 전화를 걸었는지 안걸었는지 담겨있는 DB커넥트 클래스
//  JpaRepository<enitity class,pk>
public interface RepositryChild  extends JpaRepository<PhoneNumberHistory,Long> {
    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_owner  = :pname")
    public PhoneNumberBook GetDatabyName(@Param("pname") String pname);

    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_owner  = :pname")
    public List<PhoneNumberBook> GetDatabyNameaslist(@Param("pname") String pname);

    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_number  = :pnumber")
    public PhoneNumberBook GetDatabyNumber(@Param("pnumber") String pnumber);

    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_number  = :pnumber")
    public List<PhoneNumberBook> GetDatabyNumberaslist(@Param("pnumber") String pnumber);

    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_group  = : phonegroup")
    public PhoneNumberBook GetDatabyGroup(@Param("phonegroup") String phonegroup);

    @Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_group  = : phonegroup")
    public List<PhoneNumberBook> GetDatabyGroupaslist(@Param("phonegroup") String phonegroup);
    
    @Query("SELECT p.phone_owner FROM PhoneNumberBook p")
    public List<String> GetallPhoneOwner();
        
    @Query("SELECT p.phone_number FROM PhoneNumberBook p")
    public List<String> GetallPhoneNumber();

    @Query("SELECT p.phone_group FROM PhoneNumberBook p")
    public List<Integer> GetallPhoneGroup();
     

}