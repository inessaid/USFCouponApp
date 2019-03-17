package edu.usf.cse.labrador.usfcouponapp;

public class UserInfo {

     String firstName;
     String lastName;
     String DOB;
     String phoneNumber;
     int isBusiness;

    //Constructor
    public UserInfo(String fname, String lname, String DOB, String phoneNumber, int isbuis)
    {
        this.firstName = fname;
        this.lastName = lname;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
        this.isBusiness = isbuis;
    }
    //Default constructor
    public UserInfo()
    {

    }
        //Accessor Methods
    public String getFirstname()
    {
        return firstName;
    }

    public String getLastname()
    {
        return lastName;
    }

    public String getDOB()
    {
        return DOB;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public boolean isBusiness()
    {
        if(isBusiness == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
        //Mutator Methods

    public void setFirstName(String fname)
    {
        this.firstName = fname;
    }

    public void setLastName(String lname)
    {
        this.lastName = lname;
    }

    public void setDOB(String dob)
    {
        this.DOB = dob;
    }

    public void setPhoneNumber(String newphonenum)
    {
        this.phoneNumber = newphonenum;
    }
}
