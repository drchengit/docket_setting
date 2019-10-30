package com.teamispower.smelep.myapplication.data;

/**
 * @author DrChen
 * @Date 2019/10/28 0028.
 * qq:1414355045
 * 控制小票头部展示的数据源
 */
public class DocketHead {
    /*自动打印*/
  private   boolean isAutoPrint;
    /*打印的规格 0,58mm 1,76mm ,2,80mm*/
    private    int printSize;
    /*打印的张数*/
    private  int printNumber;
    /*店铺名称是否显示*/
    private   boolean isNameShow;
    /*标题*/
    private  String title;
    /*条形码是否显示*/
    private boolean isBarCodeShow;
    private String picName;
    private String picPath;

    public DocketHead() {
        this.isAutoPrint = false;
        this.printSize = 0;
        this.isNameShow = true;
        this.printNumber = 1;
        this.title = "店铺标题";
        this.isBarCodeShow = true;
    }

    public boolean isAutoPrint() {
        return isAutoPrint;
    }

    public void setAutoPrint(boolean autoPrint) {
        isAutoPrint = autoPrint;
    }

    public int getPrintSize() {
        return printSize;
    }
    public String getPrintSizeStr(){
        if(printSize==0)return "58mm";
        if(printSize==1)return "76mm";
        if(printSize==2)return "80mm";
        return "";
    }

    public String getPicName() {
        return picName == null ? "" : picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicPath() {
        return picPath == null ? "" : picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setPrintSize(int printSize) {
        this.printSize = printSize;
    }

    public int getPrintNumber() {
        return printNumber;
    }

    public void setPrintNumber(int printNumber) {
        this.printNumber = printNumber;
    }

    public boolean isNameShow() {
        return isNameShow;
    }

    public void setNameShow(boolean nameShow) {
        isNameShow = nameShow;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isBarCodeShow() {
        return isBarCodeShow;
    }

    public void setBarCodeShow(boolean barCodeShow) {
        isBarCodeShow = barCodeShow;
    }
}
