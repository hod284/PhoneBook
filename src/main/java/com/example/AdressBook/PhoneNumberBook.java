package com.example.AdressBook;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name =  "PhoneNumberBook")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PhoneNumberBook
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long index;
    @Column(nullable =  false, unique =  true)
    @OneToMany
    private String Name;
    @Column(nullable =  false)
    private  String Number;
    @Column (nullable = false)
    private   int  Group;
      
}