package com.example.AdressBook.Callback;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//자바콜백 메서드 실행 시키는 클래스
@Slf4j
@Component
public class CallBackClass  {
     public void CallbackMethod(CallbackInterface iface, String msg)
     {
         
         log.info(msg);
      }
}
