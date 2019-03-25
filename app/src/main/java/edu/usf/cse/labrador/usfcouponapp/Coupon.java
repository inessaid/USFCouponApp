package edu.usf.cse.labrador.usfcouponapp;

public class Coupon {

    private String businessAddress;
    private String businessName;
    private String description;
    private String category;
    private String dueDate;
    private String image;
    private String pid;
    private String time;
    private String couponName;

    public Coupon(String address, String businessname, String description, String category, String duedate, String image, String pid, String time, String couponName) {
        this.businessAddress = address;
        this.businessName = businessname;
        this.description = description;
        this.category = category;
        this.dueDate = duedate;
        this.image = image;
        this.pid = pid;
        this.time = time;
        this.couponName = couponName;
    }

    public Coupon()
    {

    }

    public String getAddress() {
        return businessAddress;
    }

    public void setAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessname() {
        return businessName;
    }

    public void setBusinessname(String businessName) {
        this.businessName = businessName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDuedate() {
        return dueDate;
    }

    public void setDuedate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
