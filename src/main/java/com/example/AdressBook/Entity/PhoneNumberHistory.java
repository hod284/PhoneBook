package com.example.AdressBook.Entity;

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
// springbean 아님 착각하지 말자
// 폰주인 전화 건시간 전화를 걸었는지 받았느지를 맵핑한 DB TABLE 에니티 클래스
@Getter
@Setter
@Table(name ="PhoneNumberConnection")
@NoArgsConstructor(access =  AccessLevel.PUBLIC)
public class PhoneNumberHistory {
     //PK 자동 생성 때문데 이렇게 적음
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long index;     
     // 폰주인
     @ManyToOne
     @JoinColumn(name = "phonebook_id", nullable = false) // FK 컬럼
     private PhoneNumberBook phoneBook;  // 🔥 String 아니고 PhoneNumberBook
     // 통화한 시각
     @Column(nullable =  false)
     private LocalDateTime phone_datetime;
     // 전화를 걸었는지 안걸었는지
     @Column (nullable =  false)
     private int phone_callingorgettring;
}
