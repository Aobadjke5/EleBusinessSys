package com.zyy.dao;

import com.zyy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Update("update T_User set Role = #{role}, CompanyName = #{companyName}, CompanyAddress = #{companyAddress} " +
            "where UserID = #{userID};")
    int updateCompanyInfo(@Param("role") String role, @Param("companyName") String companyName,
                          @Param("companyAddress") String companyAddress, @Param("userID") Integer userID);

    @Update("update T_User set CompanyIcon = #{companyIcon} " +
            "where UserID = #{userID};")
    int updateCompanyIcon(@Param("companyIcon") String companyICon, @Param("userID") Integer userID);

    @Update("update T_User set PeopleName = #{peopleName}, PeopleTel = #{peopleTel}, PeopleMail = #{peopleMail} " +
            "where UserID = #{userID};")
    int updatePeopleInfo(@Param("peopleName") String peopleName, @Param("peopleTel") String peopleTel,
                          @Param("peopleMail") String peopleMail, @Param("userID") Integer userID);

    @Select("SELECT UserID as userID, UserName as userName, Role as role, Status as Status, " +
            "CompanyName as companyName, CompanyIcon as CompanyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User where Status = 'Verified' " +
            "AND CompanyName LIKE CONCAT('%', #{kw}, '%') LIMIT #{size} OFFSET #{start}")
    ArrayList<User> getUserList(@Param("start") Integer start, @Param("size") Integer pageSize, @Param("kw") String keyWord);

    @Select("SELECT UserID as userID, UserName as userName, Role as role, Status as Status, " +
            "CompanyName as companyName, CompanyIcon as CompanyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User where Status = 'Waiting'")
    ArrayList<User> getWaitingUserList();

    @Update("update T_User set Status = 'Verified' where UserID = #{userID} and Status = 'Waiting';")
    int verifyPass(@Param("userID") Integer userID);

    @Update("update T_User set Status = 'None' where UserID = #{userID} and Status = 'Waiting';")
    int verifyForbidden(@Param("userID") Integer userID);

    @Update("update T_User set Status = 'Waiting' where UserID = #{userID};")
    int getVerify(@Param("userID") Integer userID);

    @Select("SELECT UserID as userID, " +
            "CompanyName as companyName, CompanyIcon as CompanyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User where UserID = #{userID}")
    User getUserInfoByID(@Param("userID") Integer userID);
}
