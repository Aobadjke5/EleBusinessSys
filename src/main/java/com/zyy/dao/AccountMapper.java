package com.zyy.dao;

import com.zyy.controller.AdminController;
import com.zyy.entity.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {
    @Select("select UserID as userID, UserName as username, UserPassword as password, " +
            "Role as role, Status as status from T_User where UserName = #{username}")
    Account getAccountByUsername(@Param("username") String username);

    @Select("select UserID as userID, UserName as username, UserPassword as password, " +
            "Role as role, Status as status from T_User where UserID = #{userID}")
    Account getAccountByUserID(@Param("userID") Integer userID);

    @Insert("INSERT INTO T_User(UserName, UserPassword, Role, Status) " +
            "VALUES(#{u.username}, #{u.password}, #{u.role}, #{u.status})")
    int createNewAccount(@Param("u") Account account);

    @Insert("INSERT INTO T_User(UserName, UserPassword, Role, Status, CompanyName, CompanyIcon) " +
            "VALUES(#{u.adminName}, #{password}, 'Admin', 'Verified', #{u.adminNickname}, #{u.adminImage})")
    int createNewAdminAccount(@Param("u")AdminController.AdminInfo adminInfo, @Param("password") String password);

    @Update("UPDATE T_User set UserPassword = #{password} where UserID = #{userID}")
    int changePassword(@Param("password") String password, @Param("userID") Integer userID);
}
