package com.example.AddressBook.Dto;

// 폰 넘버랑 사람이름 그룹으로 조회할때 쓰이는 클래스
public record ResponseParentDto(
      String Name,
      String Number,
      Integer Group
) { }
