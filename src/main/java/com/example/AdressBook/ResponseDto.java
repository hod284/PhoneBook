package com.example.AdressBook;

import java.time.LocalDateTime;
record ResponseALLDto(
      String Name,
      String Number,
      int  Group,
    String CallingName,
     LocalDateTime Time,
       int CallingorGettring
) {     
  
}
record ResponseParentsDto(
      String Name,
      String Number,
      int  Group
) {     
  
}
record ResponseChildDto(
    String CallingName,
     LocalDateTime Time,
       int CallingorGettring
) {     
  
}
