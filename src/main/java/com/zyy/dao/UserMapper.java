package com.zyy.dao;

import com.zyy.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    // 获取状态为 verified 的用户列表
    @Select("SELECT " +
            "UserID as userID, " +
            "UserName as username, " +
            "UserPassword as password, " +
            "Role as role, " +
            "Status as status, " +
            "CompanyName as companyname, " +
            "CompanyIcon as companyicon, " +
            "CompanyAddress as companyaddress, " +
            "PeopleName as peoplename, " +
            "PeopleTel as peopletel, " +
            "PeopleMail as peoplemail " +
            "FROM T_User WHERE Status = 'verified'")
    List<User> getVerifiedUsers();

    // 获取待审核（状态为 None）的用户列表
    @Select("SELECT " +
            "UserID as userID, " +
            "UserName as username, " +
            "UserPassword as password, " +
            "Role as role, " +
            "Status as status, " +
            "CompanyName as companyname, " +
            "CompanyIcon as companyicon, " +
            "CompanyAddress as companyaddress, " +
            "PeopleName as peoplename, " +
            "PeopleTel as peopletel, " +
            "PeopleMail as peoplemail " +
            "FROM T_User WHERE Status = 'None'")
    List<User> getNoneUsers();
}

