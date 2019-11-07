package com.teamispower.smelep.myapplication.data;




import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author DrChen
 * @Date 2019/7/22 0022.
 * qq:1414355045
 *  产品
 */
public class Product implements Cloneable,Serializable{


    BigDecimal num;//当前数量


    private BigDecimal unitPrice;//单价
    private String goodsName;//商品名字

    private String spec;//	规格



    public Product(String name,String spec,BigDecimal unitPrice,BigDecimal num){
        this.goodsName =name ;
        this.spec = spec;
        this.unitPrice = unitPrice;
        this.num = num;
    }

    public BigDecimal getNum() {
        return num;
    }



    public BigDecimal getUnitPrice() {
        return unitPrice;
    }




    public String getGoodsName() {
        return goodsName == null ? "" : goodsName;
    }

    public String getSpec() {
        return spec == null ? "" : spec;
    }

    public BigDecimal getAllPrice() {
        return  getUnitPrice().multiply(getNum());
    }
}
