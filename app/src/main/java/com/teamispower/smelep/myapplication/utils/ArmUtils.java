package com.teamispower.smelep.myapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.Editable;

/**
 * @author DrChen
 * @Date 2019/10/25 0025.
 * qq:1414355045
 */
public class ArmUtils {
    /**
     * dp 转 px
     *
     * @param context {@link Context}
     * @param dpValue {@code dpValue}
     * @return {@code pxValue}
     */
    public static int dip2px(@NonNull Context context, float dpValue) {
        final float scale = getResources(context).getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获得资源
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }
    public static  boolean isEmpty(Editable s){
        return s == null || s.length() <= 0 ? true : false;
    }

}
