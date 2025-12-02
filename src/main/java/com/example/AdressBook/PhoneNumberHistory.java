package com.example.AdressBook;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name ="PhoneNumberConnection")
@NoArgsConstructor(access =  AccessLevel.PUBLIC)
public class PhoneNumberHistory {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long index;     
     @Column
     @ManyToOne
     @JoinColumn(name = "phonenumberbook_name")
     private String CallingName;
     @Column
     private LocalDateTime Time;
     @Column 
     private int CallingorGettring;
}
