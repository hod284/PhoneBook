package com.example.AdressBook.MapperClass;

import java.time.LocalDate;
import java.util.List;
import com.example.AdressBook.Dto.ResponseDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChildMapper {
     public List<ResponseDto> GetDatabyNameaslist(@Param("pname") String pname);

    
    public ResponseDto GetDatabyNameasfinding(@Param("pname") String pname,@Param("callingorgetting") int callingorgetting,@Param("pdate") String pdate);

    public List<ResponseDto> GetDatabyYearandMonth(@Param("pdate") String pdate);


    public List<ResponseDto> GetDatabyGroupaslist(@Param("callingorgetting") Integer  callingorgetting);

    public List<LocalDate> Getalldatetime();

    public List<Integer> Getallcallingorgetting();
}