package com.rescode.task;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class NewOrderModel {
    @Expose
    @SerializedName("Product")
    private Product product;
    @Expose
    @SerializedName("Status")
    private String status;
    @Expose
    @SerializedName("code")
    private int code;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class Product {
        @Expose
        @SerializedName("tax")
        private String tax;
        @Expose
        @SerializedName("Additions")
        private List<Additions> additions;
        @Expose
        @SerializedName("img")
        private String img;
        @Expose
        @SerializedName("price")
        private String price;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("id")
        private int id;

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public List<Additions> getAdditions() {
            return additions;
        }

        public void setAdditions(List<Additions> additions) {
            this.additions = additions;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Additions {
        @Expose
        @SerializedName("subAdd")
        private List<Subadd> subadd;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("id")
        private int id;

        public List<Subadd> getSubadd() {
            return subadd;
        }

        public void setSubadd(List<Subadd> subadd) {
            this.subadd = subadd;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Subadd {
        @Expose
        @SerializedName("img")
        private String img;
        @Expose
        @SerializedName("subAdd")
        private int subadd;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("id")
        private int id;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getSubadd() {
            return subadd;
        }

        public void setSubadd(int subadd) {
            this.subadd = subadd;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}