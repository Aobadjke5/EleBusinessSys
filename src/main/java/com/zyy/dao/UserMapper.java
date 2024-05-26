package com.zyy.dao;

import com.zyy.entity.Address;
import com.zyy.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

@Mapper
public interface UserMapper {

    @Select("SELECT UserID as userID, UserName as userName, Role as role, Status as Status, " +
            "CompanyName as companyName, CompanyIcon as CompanyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User")
    ArrayList<User> list();

    @Select("SELECT UserID as userID, UserName as userName, Role as role, Status as Status, " +
            "CompanyName as companyName, CompanyIcon as companyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User " +
            "where Status = 'None'")
    ArrayList<User> waitingList();

    @Update("update T_User set Status = #{option}" +
            "where UserID = #{userID};")
    int verify(@Param("userID") Integer userID , @Param("option") String option);

    @Select("SELECT UserID as userID, UserName as userName, Role as role, Status as Status, " +
            "CompanyName as companyName, CompanyIcon as companyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User " +
            "where UserID = #{userID}")
    ArrayList<User> personInfo(@Param("userID")Integer userID);

    @Update("UPDATE T_User set CompanyName = #{u.companyName}, CompanyIcon = #{u.companyIcon}, CompanyAddress = #{u.companyAddress}, " +
            "PeopleName = #{u.peopleName}, PeopleTel = #{u.peopleTel}, PeopleMail = #{u.PeopleMail} " +
            "where UserID = #{u.userID} ")
    int edit(@Param("u")User user);

    @Insert("INSERT INTO T_Address (userID, AddressDetail, PeopleName, PeopleTel) " +
            "VALUES (#{a.userID}, #{a.addressDetail}, #{a.peopleName}, #{a.peopleTel})")
    int addAddress(@Param("a") Address address);

    @Delete("Delete from T_address where AddressID = #{addressID} ")
    int delAddress(@Param("addressID")Integer addressID);

    @Update("UPDATE T_Address set AddressID = #{a.addressID} , AddressDetail = #{a.addressDetail}, " +
            "PeopleName = #{a.peopleName}, PeopleTel = #{a.peopleTel} " +
            "where UserID = #{a.userID} ")
    int editAddress(@Param("a")Address address);

    @Select("Select AddressID as addressID, AddressDetail as addressDetail, " +
            "PeopleName as peopleName , PeopleTel as peopleTel " +
            "where UserID = #{userID} ")
    ArrayList<Address> addressList(@Param("userID")Integer userID);
}
