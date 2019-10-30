package com.teamispower.smelep.myapplication.widget;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * @author DrChen
 * @Date 2019/10/25 0025.
 * qq:1414355045
 */
public class StampViewTest {
    BigDecimal bigDecimal;

    @Test
    public void onSizeChanged() {
        culate(0);
        culate(90);
        culate(180);
        culate(270);
        culate(360);
    }
    private void culate(int angle){
        System.out.println(angle+"度");
        DecimalFormat df = new DecimalFormat("0.00");
        double x = 100 * Math.cos(Math.toRadians(angle))+100;
        double y = 100 * Math.sin(Math.toRadians(angle));
        System.out.println("x: "+df.format(x));
        System.out.println("y: "+df.format(y));
    }
}