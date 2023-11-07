package com.example.mvp_rxjava_retrofit.bean;

public class ArticleBean {

    private String name;

    public ArticleBean() {
    }

    public ArticleBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
