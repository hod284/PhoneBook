package com.example.AdressBook;

import org.springframework.data.jpa.repository.JpaRepository;
//  JpaRepository<enitity class,pk>
interface RepositryParent  extends JpaRepository<PhoneNumberBook,Long> {

     
}
interface RepositryChild  extends JpaRepository< PhoneNumberHistory,Long> {

     
}
