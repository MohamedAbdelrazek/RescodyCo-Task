package com.rescode.task;


public class OrderModel {
    private String user_id;
    private String product_id;
    private String brunche_id;
    private String count;
    private String addition;
    private String subadd;
    private String totlePrice;
    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    @Override
    public String toString() {
        return "OrderModel{" +
                "user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", brunche_id='" + brunche_id + '\'' +
                ", count='" + count + '\'' +
                ", addition='" + addition + '\'' +
                ", subadd='" + subadd + '\'' +
                ", totlePrice='" + totlePrice + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBrunche_id() {
        return brunche_id;
    }

    public void setBrunche_id(String brunche_id) {
        this.brunche_id = brunche_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getSubadd() {
        return subadd;
    }

    public void setSubadd(String subadd) {
        this.subadd = subadd;
    }

    public String getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(String totlePrice) {
        this.totlePrice = totlePrice;
    }
}
