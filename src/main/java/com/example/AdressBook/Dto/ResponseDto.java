package com.example.AdressBook.Dto;

import java.time.LocalDateTime;

// DB에 있는 전체를 조회할때 쓰이는 클래스
public record ResponseDto(
      String Name,
      String Number,
      int  Group,
      String CallingName,
      LocalDateTime Time,
      int CallingorGettring
) {  }