package com.example.AdressBook.Repositry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AdressBook.EntityClass.PhoneNumberHistory;
// 레포지트리 폰 주인을  전화를 건시간 전화를 걸었는지 안걸었는지 담겨있는 DB커넥트 클래스
//  JpaRepository<enitity class,pk>
public interface RepositryChild  extends JpaRepository<PhoneNumberHistory,Long> {


      // jpa 프로젝션 이것도 transient dirty로 데이터 변화체크해서 db에 있는 데이터 수정할때 쓰기위해 db의 데이터를 얻을때 쓰는 프로젝션
     @Query("""
     SELECT p FROM PhoneNumberHistory p  
        INNER JOIN p.phonebook  b 
        WHERE b.phone_owner  = :pname
        AND  p.phone_callingorgetting = :callingorgetting 
        AND  FUNCTION('TO_CHAR',p.phone_datetime,'YYYY-MM-DD')  = :pdate 
    """)
    public PhoneNumberHistory GetDatabyNameasModify(@Param("pname") String pname,@Param("callingorgetting") int callingorgetting,@Param("pdate") String pdate);

    /* jpa 프로젝션 안쓰는거
    // on을 안쓰는 이유는 entity에서 이미 fk키로 연관관계를 만들어 놓았기 때문
     @Query("SELECT p FROM PhoneNumberHistory p  INNER JOIN p.phonebook  b WHERE b.phone_owner  = :pname")
    public List<PhoneNumberHistory> GetDatabyNameaslist(@Param("pname") String pname);

   
    @Query("SELECT p FROM PhoneNumberHistory p WHERE cast(function('TO_CHAR', p.phone_datetime, 'YYYY-MM-DD') as string) LIKE CONCAT(:pdate,'%')")
    public List<PhoneNumberHistory> GetDatabyYearandMonth(@Param("pdate") String pdate);

    @Query("SELECT p FROM PhoneNumberHistory p WHERE p.phone_callingorgetting = :callingorgetting")
    public List<PhoneNumberHistory> GetDatabyGroupaslist(@Param("callingorgetting") Integer  callingorgetting);
    
    @Query("SELECT p.phone_datetime FROM PhoneNumberHistory p")
    public List<LocalDate> Getalldatetime();

    @Query("SELECT p.phone_callingorgetting FROM PhoneNumberHistory p")
    public List<Integer> Getallcallingorgetting();
     */
}