package com.zyy.dao;

import com.zyy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

@Mapper
public interface UserMapper {

    @Select("SELECT UserID as userID, UserName as userName, Role as role, Status as Status, " +
            "CompanyName as companyName, CompanyIcon as CompanyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User where Status = 'Verified'")
    ArrayList<User> getUserList();

    @Select("SELECT UserID as userID, UserName as userName, Role as role, Status as Status, " +
            "CompanyName as companyName, CompanyIcon as CompanyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User where Status = 'Waiting'")
    ArrayList<User> getWaitingUserList();

    @Update("update T_User set Status = 'Verified' where UserID = #{userID} and Status = 'Waiting';")
    int verifyPass(@Param("userID") Integer userID);

    @Update("update T_User set Status = 'None' where UserID = #{userID} and Status = 'Waiting';")
    int verifyForbidden(@Param("userID") Integer userID);

    @Select("SELECT UserID as userID, " +
            "CompanyName as companyName, CompanyIcon as CompanyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User where UserID = #{userID}")
    User getUserInfoByID(@Param("userID") Integer userID);
}
