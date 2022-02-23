package com.devthread.devthreadadmin.Model;

public class UserInfoModel {
    String address, company, companyAddress, companyWeb, email, empId, mobile, name, profile, uid;

    public UserInfoModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserInfoModel(String address, String company, String companyAddress, String companyWeb, String email, String empId, String mobile, String name, String profile, String uid) {
        this.address = address;
        this.company = company;
        this.companyAddress = companyAddress;
        this.companyWeb = companyWeb;
        this.email = email;
        this.empId = empId;
        this.mobile = mobile;
        this.name = name;
        this.profile = profile;
        this.uid = uid;
    }
}
