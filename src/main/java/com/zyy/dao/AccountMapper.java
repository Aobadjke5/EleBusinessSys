package com.zyy.dao;

import com.zyy.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Select("select UserID as userID, UserName as username, UserPassword as password, " +
            "Role as role, Status as status from T_User where UserName = #{username}")
    Account getAccountByUsername(@Param("username") String username);

    @Insert("INSERT INTO T_User(UserName, UserPassword, Role, Status) " +
            "VALUES(#{u.username}, #{u.password}, #{u.role}, #{u.status})")
    int createNewAccount(@Param("u") Account account);
}
