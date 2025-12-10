package com.example.AdressBook.MapperClass;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.AdressBook.Dto.ResponseParentDto;

@Mapper
public interface ParentMapper {
     public  ResponseParentDto GetDatabyName(@Param("pname") String pname);

    public  ResponseParentDto GetDatabyNumber(@Param("pnumber") String pnumber);

    
    public List<ResponseParentDto> GetDatabyGroupaslist(@Param("phonegroup") Integer phonegroup);
    

    public List<String> GetallPhoneOwner();
        

    public List<String> GetallPhoneNumber();


    public List<Integer> GetallPhoneGroup();
}