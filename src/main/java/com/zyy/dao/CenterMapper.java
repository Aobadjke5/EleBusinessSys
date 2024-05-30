package com.zyy.dao;

import com.zyy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CenterMapper {
    @Select("SELECT UserName as userName, Role as role, Status as Status, " +
            "CompanyName as companyName, CompanyIcon as companyIcon, CompanyAddress as companyAddress, " +
            "PeopleName as peopleName, PeopleTel as peopleTel, PeopleMail as PeopleMail " +
            "from T_User " +
            "where UserID = #{userID}")
    User getUserInfoByID(@Param("userID") Integer userID);

    @Update("UPDATE T_User set CompanyName = #{u.companyName}, CompanyIcon = #{u.companyIcon}, CompanyAddress = #{u.companyAddress}, " +
            "PeopleName = #{u.peopleName}, PeopleTel = #{u.peopleTel}, PeopleMail = #{u.peopleMail} " +
            "where UserID = #{userID} ")
    int editUserInfo(@Param("u") User user, @Param("userID") Integer userID);

    @Update("UPDATE T_User set CompanyName = #{u.companyName}, CompanyIcon = #{u.companyIcon} " +
            "where UserID = #{userID} ")
    int editAdminInfo(@Param("u") User user, @Param("userID") Integer userID);
}
