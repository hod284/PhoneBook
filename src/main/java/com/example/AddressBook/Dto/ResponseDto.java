package com.example.AddressBook.Dto;

import java.time.LocalDate;

// DB에 있는 전체를 조회할때 쓰이는 클래스
public record ResponseDto(
      String Name,
      String Number,
      Integer  Group,
      LocalDate Time,
      Integer CallingorGetting
) {  }