package com.zyy.service;

public interface RegisterService {
    Boolean setCompanyInfo(String role, String companyName, String companyAddress, Integer userID);

    Boolean setCompanyIcon(String companyIcon, Integer userID);

    Boolean setPeopleInfo(String peopleName, String peopleTel, String peopleMail, Integer userID);
}
