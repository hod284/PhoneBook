package com.example.AddressBook.Dto;

// 폰넘버를 자식으로 삼은 자식 테이블에서 조회할 때 쓰이는 클래스
public record ResponseChildDto(
    String CallingName,
        Integer CallingorGetting
) { }
