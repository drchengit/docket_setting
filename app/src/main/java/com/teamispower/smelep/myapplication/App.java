package com.teamispower.smelep.myapplication;

import android.app.Application;

import me.jessyan.autosize.AutoSizeConfig;


/**
 * @author DrChen
 * @Date 2019/10/25 0025.
 * qq:1414355045
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       //适配的方案  :   https://github.com/JessYanCoding/AndroidAutoSize
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(true)
                .setSupportSP(true);
    }
}
