package com.teamispower.smelep.myapplication.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DrChen
 * @Date 2019/10/29 0029.
 * qq:1414355045
 */
public class KeyData{
    String key;
    String value;
    boolean isShow;
    boolean isSpan;
    List<Product> products;

    public KeyData(String key, String value, boolean isShow) {
        this.key = key;
        this.value = value;
        this.isShow = isShow;
    }
    public KeyData(String key, String value, boolean isShow,List<Product> products) {
        this.key = key;
        this.value = value;
        this.isShow = isShow;
        this.products = products;
    }

    public KeyData(String key, String value, boolean isShow,boolean isSpan) {
        this.key = key;
        this.value = value;
        this.isShow = isShow;
        this.isSpan = isSpan;
    }

    public List<Product> getProducts() {
        if (products == null) {
            return new ArrayList<>();
        }
        return products;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public boolean isSpan() {
        return isSpan;
    }

    public void setSpan(boolean span) {
        isSpan = span;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public boolean isShow() {
        return isShow;
    }

    @Override
    public String toString() {
        return "KeyData{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", isShow=" + isShow +
                ", isSpan=" + isSpan +
                '}';
    }
}
