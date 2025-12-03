package com.example.AdressBook.Entity;


import java.util.ArrayList;
import java.util.List;

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

// springbean 아님 착각하지 말자
//  폰 넘버 주인 폰넘버가 속한그룹을 맵핑한 DB TABLE 에니티 클래스
@Entity
@Getter
@Setter
@Table(name =  "PhoneNumberBook")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PhoneNumberBook
{
    // PK의미 없음 자동 생성 때문에 억지로 쓴거임
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long index;
    // 네임 이걸 기준으로 자식의 FK 지정
    // 폰 주인
    // 유니크라서 자동으로 인덱스 지정이 됨
    @Column(nullable =  false, unique =  true)
    private String phone_owner;
    // 폰넘버
    @Column(nullable =  false)
    private  String phone_number;
    // 폰 넘버 그룹
    @Column (nullable = false)
    private   int  phone_group;

    @OneToMany(mappedBy = "phoneBook")
    private List<PhoneNumberHistory> histories = new ArrayList<>();
      
}