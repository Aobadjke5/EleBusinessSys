package com.zyy.dao;

import com.zyy.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface AddressMapper {
    @Select("Select AddressID as addressID, AddressDetail as addressDetail, " +
            "PeopleName as peopleName, PeopleTel as peopleTel " +
            "from T_Address where UserID = #{userID} AND Status = 'Yes'")
    ArrayList<Address> getAddressList(@Param("userID") Integer userID);

    @Select("Select AddressID as addressID, AddressDetail as addressDetail, " +
            "PeopleName as peopleName, PeopleTel as peopleTel " +
            "from T_Address where UserID = #{userID} AND Status = 'Yes' LIMIT 1")
    Address getDefaultAddress(@Param("userID") Integer userID);

    @Insert("INSERT INTO T_Address (UserID, AddressDetail, PeopleName, PeopleTel, Status) " +
            "VALUES (#{userID}, #{a.addressDetail}, #{a.peopleName}, #{a.peopleTel}, 'Yes')")
    @Options(useGeneratedKeys = true, keyProperty = "a.addressID")
    int createNewAddress(@Param("a") Address address, @Param("userID") Integer userID);

    @Update("UPDATE T_Address set Status = 'No' where AddressID = #{addressID} ")
    int deleteAddress(@Param("addressID") Integer addressID);

    @Update("UPDATE T_Address set AddressDetail = #{a.addressDetail}, " +
            "PeopleName = #{a.peopleName}, PeopleTel = #{a.peopleTel} " +
            "where AddressID = #{a.addressID} AND Status = 'Yes';")
    int editAddress(@Param("a")Address address);

    @Select("SELECT count(*) from T_Order where AddressID = #{addressID};")
    int hadAddressUsed(@Param("addressID") Integer addressID);
}
