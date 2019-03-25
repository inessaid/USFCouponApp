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

    public Coupon(String businessAddress, String businessName, String description, String category, String dueDate, String image, String pid, String time, String couponName) {
        this.businessAddress = businessAddress;
        this.businessName = businessName;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
        this.image = image;
        this.pid = pid;
        this.time = time;
        this.couponName = couponName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
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

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}