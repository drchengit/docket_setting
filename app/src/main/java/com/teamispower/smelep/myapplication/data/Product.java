package com.teamispower.smelep.myapplication.data;

import android.util.Log;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author DrChen
 * @Date 2019/7/22 0022.
 * qq:1414355045
 *  产品
 */
public class Product implements Cloneable,Serializable{


    BigDecimal num;//当前数量临时用,取单用（购物车和库存）

    private int pricingMode;//计价方式 0计数 1.计量

    private int specId;//	规格ID  是全局唯一  //大于 1713358 是快速收款
    private BigDecimal unitPrice;//单价
    private String coverImg;//封面图
    private int goodsId;//商品id  是展示的时候唯一
    private int multiSpec;//多规格 0否 1是
    private BigDecimal stock  ;//库存
    private String goodsName;//商品名字

    private String spec;//	规格
    private String specCode;



    public Product(int id, String name, int num, int maxNum) {
        this.goodsName = name;
        this.num =new BigDecimal(num+"");
        this.goodsId  = id;
        this.specId = id;
        this.stock = new BigDecimal(maxNum+"");
    }

    public Product(String name,String spec,BigDecimal unitPrice,BigDecimal num){
        this.goodsName =name ;
        this.spec = spec;
        this.unitPrice = unitPrice;
        this.num = num;
    }

    public BigDecimal getNum() {
        return num;
    }

    public int getPricingMode() {
        return pricingMode;
    }

    public int getSpecId() {
        return specId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getCoverImg() {
        return coverImg == null ? "" : coverImg;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public int getMultiSpec() {
        return multiSpec;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public String getGoodsName() {
        return goodsName == null ? "" : goodsName;
    }

    public String getSpec() {
        return spec == null ? "" : spec;
    }

    public String getSpecCode() {
        return specCode == null ? "" : specCode;
    }
    public BigDecimal getAllPrice() {
        return  getUnitPrice().multiply(getNum());
    }
}
