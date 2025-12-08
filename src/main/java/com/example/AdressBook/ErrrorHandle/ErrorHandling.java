package com.example.AdressBook.ErrrorHandle;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TransactionRequiredException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandling
{
        @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNPE(NullPointerException e) {
        return ResponseEntity.badRequest().body("NPE!");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIAE(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("잘못된 값!");
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleeEntity(EntityNotFoundException e) 
    {
       e.printStackTrace();
        return ResponseEntity.badRequest().body("엔티티 없음");
    }
     @ExceptionHandler(TransactionRequiredException.class)
    public ResponseEntity<?> handleTransaction(TransactionRequiredException e) 
    {
        e.printStackTrace();  
        return ResponseEntity.badRequest().body("트랜젝션오류");
    }
     @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handlesql(SQLException e) 
    {
        e.printStackTrace();  
        return ResponseEntity.badRequest().body("sql오류");
    }

}
    