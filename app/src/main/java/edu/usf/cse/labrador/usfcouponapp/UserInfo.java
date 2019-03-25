package edu.usf.cse.labrador.usfcouponapp;

public class UserInfo {

     private String firstName;
     private String lastName;
     private String DOB;
     private String phoneNumber;
     private int isBusiness;
     private String email;
    //Constructor
    public UserInfo(String firstName, String lastName, String DOB, String phoneNumber, int isBusiness, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
        this.isBusiness = isBusiness;
        this.email = email;
    }
    //Default constructor
    public UserInfo()
    {

    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return DOB;
    }

    public void setDob(String DOB) {
        this.DOB = DOB;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }

    public void setPhonenumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIsbusiness() {
        return isBusiness;
    }

    public void setIsbusiness(int isBusiness) {
        this.isBusiness = isBusiness;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
